package com.datn.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	public String name;

	@ManyToOne()
	@JoinColumn(name = "categoryid")
	public Category category;

	public Double price;
	public String description;
	public Boolean status;
	public String images;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "storeid")
	public Store store;

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = { CascadeType.ALL })
	public List<OrderDetail> orderDetails;

	@JsonIgnore
	@OneToMany(mappedBy = "productid")
	public List<ProductImage> productImages;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	public List<ShoppingCart> shoppingCarts;
	
}
