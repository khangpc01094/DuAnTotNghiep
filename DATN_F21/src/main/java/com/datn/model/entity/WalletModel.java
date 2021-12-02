package com.datn.model.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletModel {
	
	private String cardnumber;
	private String cardbrand;
	private String holdername;
	private Integer cvv;
	public Date cardexpiry;
}
