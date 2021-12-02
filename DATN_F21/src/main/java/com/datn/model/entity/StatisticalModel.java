package com.datn.model.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticalModel {
	private Integer id;
	private Date bookingdate;
	private Double moneyorder;
	private Integer discount;
	private Double intomoney;
}
