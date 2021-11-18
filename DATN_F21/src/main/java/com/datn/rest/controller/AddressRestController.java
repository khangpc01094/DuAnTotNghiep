package com.datn.rest.controller;

import com.datn.entity.Address;
import com.datn.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AddressRestController {

    @Autowired
	AddressService svAddress;

    @PostMapping("/buyer/add_address")
	public Address create(@RequestBody Address address) {
		return svAddress.create(address);
	}
}
