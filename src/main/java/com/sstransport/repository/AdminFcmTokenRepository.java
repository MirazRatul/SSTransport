package com.sstransport.repository;

import com.sstransport.model.AdminFcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminFcmTokenRepository extends JpaRepository<AdminFcmToken, Long> {

    List<AdminFcmToken> findByAdminId(String adminId);

    Optional<AdminFcmToken> findByToken(String token);

    void deleteByToken(String token);
}

