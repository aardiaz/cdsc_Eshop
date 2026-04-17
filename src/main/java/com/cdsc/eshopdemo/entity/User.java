package com.cdsc.eshopdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user_tbl")
public class User {
	@Id
	private String userId;
	@Column(name = "user_name",length = 100, nullable = false)
	private String name;
	@Column(name = "user_email",length = 100, nullable = false, unique = true)
	private String email;
	@Column(name = "user_username", length = 100, nullable = false, unique = true)
	private String username;
	@Column(name = "user_password", length = 100, nullable = false)
	private String password;
	@Column(name = "user_gender", length = 50, nullable = false)
	private String gender;
	@Column(name = "user_phone", length = 20, nullable = false)
	private String phone;

}
