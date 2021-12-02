package com.datn.rest.controller;

import java.util.List;
import java.util.Optional;

import com.datn.entity.Store;

import com.datn.service.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/store")
public class StoreRestController {

    @Autowired StoreService svStoreService;
	
	@GetMapping("/findall")
	public List<Store> getAllStore(){
		return svStoreService.getAllStore();
	}
	
	@GetMapping("/findbyname/{name}")
	public List<Store> getFindStoreByName(@PathVariable("name") Optional<String> name){
		return svStoreService.getFindStoreByName(name.get());
	}
}
