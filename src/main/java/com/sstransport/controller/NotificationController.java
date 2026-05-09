package com.sstransport.controller;

import com.sstransport.dto.ChatNotificationRequest;
import com.sstransport.service.ChatNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final ChatNotificationService chatNotificationService;

    public NotificationController(ChatNotificationService chatNotificationService) {
        this.chatNotificationService = chatNotificationService;
    }

    @PostMapping("/chat")
    public ResponseEntity<Void> sendChatNotification(
            @RequestBody ChatNotificationRequest request,
            Authentication authentication
    ) {
        String senderId = authentication.getName();

        chatNotificationService.sendChatNotification(senderId, request);

        return ResponseEntity.ok().build();
    }
}
