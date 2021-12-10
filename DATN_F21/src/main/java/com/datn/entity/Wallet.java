package com.datn.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "wallet")
public class Wallet implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	@OneToOne
	@JoinColumn(name = "userid")
	public Users user;
	public String cardnumber;
	public String cardbrand;
	public String holdername;
	public Integer cvv;
	public Date cardexpiry;
	public Double money;
	
}
