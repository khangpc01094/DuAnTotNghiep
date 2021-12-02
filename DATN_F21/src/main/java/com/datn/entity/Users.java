package com.datn.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {
	@Id
	public String userid;
	public String username;
	public String password;
	public String fullname;
	public String picture;
	public String email;
	public Boolean gender;
	@Temporal(TemporalType.DATE)
	public Date birthday;
	public String phone;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	public List<Address> addresss;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	public List<Authorization> authorization;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<Order> orders;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	public Set<ShoppingCart> shoppingCart;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	public List<Store> store;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Wallet> wallet;
	
	@JsonIgnore
	@OneToOne(mappedBy = "user", cascade = { CascadeType.ALL })
	private Transaction transaction;

}