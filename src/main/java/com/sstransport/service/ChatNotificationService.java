package com.sstransport.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MessagingErrorCode;
import com.google.firebase.messaging.Notification;
import com.sstransport.dto.ChatNotificationRequest;
import com.sstransport.model.Admin;
import com.sstransport.model.AdminFcmToken;
import com.sstransport.repository.AdminFcmTokenRepository;
import com.sstransport.repository.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ChatNotificationService {

    private final AdminFcmTokenRepository tokenRepository;
    private final AdminRepository adminRepository;

    public ChatNotificationService(
            AdminFcmTokenRepository tokenRepository,
            AdminRepository adminRepository
    ) {
        this.tokenRepository = tokenRepository;
        this.adminRepository = adminRepository;
    }

    public void sendChatNotification(String senderId, ChatNotificationRequest request) {
        if (senderId.equals(request.receiverId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot send notification to yourself"
            );
        }

        Admin sender = adminRepository.findById(senderId).orElse(null);

        String senderName = sender != null
                && sender.getName() != null
                && !sender.getName().isBlank()
                ? sender.getName()
                : "New message";

        String senderImage = sender != null
                && sender.getImageUrl() != null
                ? sender.getImageUrl()
                : "";

        String body = request.text() == null || request.text().isBlank()
                ? "New message"
                : request.text();

        List<AdminFcmToken> receiverTokens =
                tokenRepository.findByAdminId(request.receiverId());

        for (AdminFcmToken receiverToken : receiverTokens) {
            Message message = Message.builder()
                    .setToken(receiverToken.getToken())
                    .setNotification(Notification.builder()
                            .setTitle(senderName)
                            .setBody(body)
                            .build())
                    .putData("type", "chat")
                    .putData("senderId", senderId)
                    .putData("senderName", senderName)
                    .putData("senderImage", senderImage)
                    .putData("senderRole", "Admin")
                    .putData("receiverId", request.receiverId())
                    .putData("conversationId", request.conversationId())
                    .putData("messageId", request.messageId())
                    .putData("text", body)
                    .build();

            try {
                FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException error) {
                if (error.getMessagingErrorCode() == MessagingErrorCode.UNREGISTERED) {
                    tokenRepository.deleteByToken(receiverToken.getToken());
                }
            }
        }
    }
}
