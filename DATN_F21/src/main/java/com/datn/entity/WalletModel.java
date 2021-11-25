package com.datn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletModel {
	private Integer cardnumber;
	private String cardbrand;
	private String holdername;
	private Integer cvv;
}
