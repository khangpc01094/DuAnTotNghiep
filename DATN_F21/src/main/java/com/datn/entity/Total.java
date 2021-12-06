package com.datn.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Total {
    
	@Id
    public Integer storeid;
	public String name;
    public String userid;
    public Double total;
    public Double reduce;
    public Double pay;

}
