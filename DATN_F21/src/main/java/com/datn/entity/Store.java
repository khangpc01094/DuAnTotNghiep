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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
public class Store implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private Users user;
	
	private String name;
	private String description;
	private Boolean status;
	
	@JsonIgnore
	@OneToMany(mappedBy = "store", cascade = { CascadeType.ALL })
	private List<Product> product;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "store", cascade = { CascadeType.ALL })
	private List<Order> order;
}
