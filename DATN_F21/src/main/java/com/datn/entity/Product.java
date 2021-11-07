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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "caregoryid")
	private Category category;
	
	private Double price;
	private String description;
	private Boolean status;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "storeid")
	private Store store;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = { CascadeType.ALL })
	private List<OrderDetail> orderDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ProductImage> productImages;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<ShoppingCart> shoppingCarts;
	
}
