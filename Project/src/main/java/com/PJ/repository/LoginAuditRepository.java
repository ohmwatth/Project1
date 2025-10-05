package com.PJ.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PJ.model.LoginAudit;

public interface LoginAuditRepository extends JpaRepository<LoginAudit, Long> {
}
