package com.bank.entity.Model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankModel {
	
	private String cardnumber;
	private String cardbrand;
	private String holdername;
	private Integer cvv;
	public Date cardexpiry;
}
