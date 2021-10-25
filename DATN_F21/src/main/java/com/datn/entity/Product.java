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
@Table(name = "Product")
public class Product implements Serializable {
	@Id
//	@GeneratedValue
	@Column(name = "Id")
	Integer id;
	@Column(name = "Name")
	String name;
	@Column(name = "Categoryid")
	String categoryid;
	@Column(name = "Storeid")
	Integer storeid;
	@Column(name = "Price")
	Double price;
	@Column(name = "Description")
	String description;
	@Column(name = "Status")
	Boolean status;
}
