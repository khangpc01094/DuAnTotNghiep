package com.datn.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "order")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@ManyToOne
	@JoinColumn(name = "storeid")
	public Store store;

	public String address;

	@Temporal(TemporalType.DATE)
	public Date bookingdate = new Date();

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	// @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializar" })
	public Users user;

	public int status;

	public Double totalamount;

	@JsonIgnore
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	public List<OrderDetail> orderDetails;

}
