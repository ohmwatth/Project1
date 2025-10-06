package com.PJ.dtos;

import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthResponse {
    private String token;

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}