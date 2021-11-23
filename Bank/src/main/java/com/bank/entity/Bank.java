package com.bank.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "bank")
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
	@Id
	private Integer cardnumber;
	@ManyToOne()
	@JoinColumn(name = "cardbrandid")
	private CardBrand cardbrand;
	
	private String holdername;
	private Integer cvv;
	@Temporal(TemporalType.DATE)
	private Date cardexpiry;
	private double money;
}
