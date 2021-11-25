package com.datn.rest.controller;


import java.util.List;

import com.datn.entity.Address;
import com.datn.entity.Users;
import com.datn.service.AddressService;
import com.datn.service.UserService;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AddressRestController {

    @Autowired
	AddressService svAddress;

	@Autowired
	UserService svUser;

    @PostMapping("/buyer/add_address")
	public Address create(@RequestBody Address address) {
		Users a = svUser.getByid("user2");
		address.setUser(a);
		return svAddress.create(address);
    }

	@GetMapping("/address/us/{id}")
	public List<Address> demo2(@PathVariable("id") String id) {
		return svAddress.findByUserid(id);
	}

	@GetMapping("/address/{id}")
	public Address getAddByid(@PathVariable("id") Integer id){
		return svAddress.findByIdd(id);
	}
}
