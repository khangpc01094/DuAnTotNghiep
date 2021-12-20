package com.datn.model.entity;

import java.util.Date;


import com.datn.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletConfigModel {
	public Integer id;
	public Users user;
	public String cardnumber;
	public String cardbrand;
	public String holdername;
	public Integer cvv;
	public Date cardexpiry;
	public Double money;
	public String verification;
}
