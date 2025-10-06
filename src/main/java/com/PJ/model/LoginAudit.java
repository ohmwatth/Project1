package com.PJ.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_audit")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoginAudit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    private String username;
    private LocalDateTime loginAt;
    private String ipAddress;
    private boolean success;
    private String failureReason;
    
    
	public LoginAudit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginAudit(Long id, String username, LocalDateTime loginAt, String ipAddress, boolean success,
			String failureReason) {
		super();
		this.id = id;
		this.username = username;
		this.loginAt = loginAt;
		this.ipAddress = ipAddress;
		this.success = success;
		this.failureReason = failureReason;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDateTime getLoginAt() {
		return loginAt;
	}
	public void setLoginAt(LocalDateTime loginAt) {
		this.loginAt = loginAt;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
    
    
}
