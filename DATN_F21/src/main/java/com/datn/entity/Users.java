package com.datn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Users")
public class Users implements Serializable {
	@Id
//	@GeneratedValue
	@Column(name = "Userid")
	String userId;
	@Column(name = "Username")
	String userName;
	@Column(name = "Password")
	String password;
	@Column(name = "Fullname")
	String fullName;
	@Column(name = "Picture")
	String picture;
	@Column(name = "Email")
	String email;
	@Column(name = "Gender")
	Boolean gender;
	@Column(name = "Birthday")
	String birthday;
	@Column(name = "Phone")
	String phone;
}
