package com.datn.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class statiscate implements Serializable{

	@Id
	String namecate;
	String images;
	Long sumquantity;
	Double total;
}