package com.datn.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "address")
public class Address implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "userid")
	public Users user;

	public String address;

	public String fullname;

	public String phone;

	public boolean status;

	@JsonIgnore
	@OneToMany(mappedBy = "address")
	List<Order> order;

}
