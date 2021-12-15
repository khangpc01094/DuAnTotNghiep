package com.datn.rest.controller;

import java.util.List;
import java.util.Optional;

import com.datn.entity.Authorization;
import com.datn.entity.Role;
import com.datn.entity.Users;
import com.datn.model.entity.ChangePasswordModel;
import com.datn.service.AuthorizationService;
import com.datn.service.RoleService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired 
    UserService svUserService;
    

    @Autowired
    AuthorizationService svAuth;

    @Autowired
    RoleService svRole;

	
	@GetMapping("/information")
	public Users getInformation(Model model) {
		return svUserService.getInformation();
	}
	
	@PutMapping("/information/update")
	public ResponseEntity<Users> putInformation(@RequestBody Users user){	
		return svUserService.postInformation(user);
	}

	@GetMapping("/findall")
	public List<Users> getAllUser(){
		return svUserService.getAllUser();
	}
	
	@GetMapping("/findbyname/{name}")
	public List<Users> getFindUserByName(@PathVariable("name") Optional<String> name){
		return svUserService.getFindUserByName(name.get());
	}

    @PostMapping("/buyer/regis")
	public Users create(@RequestBody Users user) {
        Users usera = svUserService.create(user);
        Authorization auth = new Authorization();
       Role rol = svRole.findById(1);
       auth.setRole(rol);
       auth.setUser(usera);
       svAuth.Create(auth);
       return usera;
	}

	@GetMapping("{id}")
	public Users getById(@PathVariable String id) {
		return svUserService.findById(id);
	}

	
//	admin them user
	@PostMapping("/admin/create_user")
	public ResponseEntity<Users> addUserByAdmin(@RequestBody Users user){		   
       return svUserService.addUserByAdmin(user);
	}
	
	//Đổi mật khẩu
	@PutMapping("/change_password")
	public ResponseEntity<Users> changePassword(@RequestBody ChangePasswordModel changePasswordModel) {
		return svUserService.changePassword(changePasswordModel);
	}

}
