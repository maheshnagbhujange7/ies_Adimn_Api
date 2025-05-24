package com.spider.ADMIN_API.bindings;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class UserAccForm {

	private String fullName;
	private String email;
	private Long mobileNo;
	private String gender;
	private LocalDate dob;
	private Long ssn;
	private String activeSw;
	private Integer roleId;
	
	
}
