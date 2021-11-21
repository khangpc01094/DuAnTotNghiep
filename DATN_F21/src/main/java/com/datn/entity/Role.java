package com.datn.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String role;

	@JsonIgnore
	@OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	public List<Authorization> authorization;
}
