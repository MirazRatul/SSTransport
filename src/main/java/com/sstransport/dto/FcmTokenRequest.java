package com.sstransport.dto;

public record FcmTokenRequest(
        String token,
        String platform
) {}
