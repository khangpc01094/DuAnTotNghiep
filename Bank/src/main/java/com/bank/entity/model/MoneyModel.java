package com.bank.entity.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoneyModel {
	private Integer cardnumber;
	private Double money;
}
