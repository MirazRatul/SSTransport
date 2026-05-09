package com.sstransport.controller;

import com.sstransport.dto.FcmTokenRequest;
import com.sstransport.model.AdminFcmToken;
import com.sstransport.repository.AdminFcmTokenRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final AdminFcmTokenRepository tokenRepository;

    public DeviceController(AdminFcmTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/fcm-token")
    public ResponseEntity<Void> saveFcmToken(
            @RequestBody FcmTokenRequest request,
            Authentication authentication
    ) {
        String adminId = authentication.getName();

        AdminFcmToken token = tokenRepository.findByToken(request.token())
                .orElse(new AdminFcmToken());

        token.setAdminId(adminId);
        token.setToken(request.token());
        token.setPlatform(request.platform());

        tokenRepository.save(token);

        return ResponseEntity.ok().build();
    }
}
