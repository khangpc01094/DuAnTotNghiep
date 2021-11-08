package com.bank.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String cardbrand;
	private String holdername;
	private int cvv;
	private String cardexpiry;
	private double money;
}
