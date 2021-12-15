package com.datn.model.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.datn.entity.Address;
import com.datn.entity.Authorization;
import com.datn.entity.Order;
import com.datn.entity.ShoppingCart;
import com.datn.entity.Store;
import com.datn.entity.Transaction;
import com.datn.entity.Users;
import com.datn.entity.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordModel {
	public String pw_present;
	public String pw_new;
	public String pw_confirm;
}
