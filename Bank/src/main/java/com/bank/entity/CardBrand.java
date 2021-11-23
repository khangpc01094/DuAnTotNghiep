package com.bank.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "cardbrand")
@NoArgsConstructor
@AllArgsConstructor
public class CardBrand {
	@Id
	private String id;
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cardbrand")
	public List<Bank> bank;
}
