package com.sstransport.dto;

public record ChatNotificationRequest(
        String receiverId,
        String conversationId,
        String messageId,
        String text
) {}
