package com.cdsc.eshopdemo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	
	private String userId;
	private String name;
	private String email;
	private String username;
	private String password;
	private String gender;
	private String phone;
}
