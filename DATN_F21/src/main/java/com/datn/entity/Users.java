package com.datn.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {
	@Id
	private String userid;
	private String username;
	private String password;
	private String fullname;
	private String picture;
	private String email;
	private Boolean gender;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private String phone;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Address> addresss;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Authorization> authorization;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Order> orders;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<ShoppingCart> shoppingCart;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Store> store;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Wallet> wallet;
}
