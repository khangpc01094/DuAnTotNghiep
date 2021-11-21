package com.datn.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "shoppingcarts")
public class ShoppingCart implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "userid")
	public Users user;

	@ManyToOne
	@JoinColumn(name = "productid")
	public Product product;

	public Integer quantity;
	
	public Integer storeid;

}
