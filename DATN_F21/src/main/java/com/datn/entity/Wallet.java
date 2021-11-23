package com.datn.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "wallet")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "id" })
public class Wallet implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne()
	@JoinColumn(name = "userid")
	private Users user;
	private String cardbrand;
	private String holdername;
	private Integer cvv;
	private Double money;	
	private Integer cardnumber;
}
