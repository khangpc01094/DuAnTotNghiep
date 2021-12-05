package com.datn.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.entity.Address;
import com.datn.entity.Users;
import com.datn.service.AddressService;
import com.datn.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/address")
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

	@GetMapping("/us")
	public List<Address> demo2() {
		String user = "user2";
		return svAddress.findByUserid(user);
	}

	@GetMapping("/{id}")
	public Address getAddByid(@PathVariable("id") Integer id){
		return svAddress.findByIdd(id);
	}

	@GetMapping("/us/{id}")
	public List<Address> demo2(@PathVariable("id") String id) {
		return svAddress.findByUserid(id);
	}

	@PutMapping("/buyer/edit_address")
	public Address edit(@RequestBody Address address) {
		return svAddress.create(address);
    }

	@DeleteMapping("/buyer/del_address/{id}")
	public Address delete(@PathVariable("id") Integer id) {	
		Address ad = svAddress.findByIdd(id);
		ad.setStatus(false);
		return svAddress.create(ad);
    }

}
