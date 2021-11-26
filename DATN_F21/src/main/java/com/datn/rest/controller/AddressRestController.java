package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.Address;
import com.datn.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/address")
public class AddressRestController {

    @Autowired
    AddressService svAddress;

    @GetMapping("/getall")
    public List<Address> getAlla(){
        return svAddress.getAllAr();
    }
}
