package com.datn.rest.controller;

import java.util.List;

import com.datn.entity.Product;
import com.datn.entity.ShoppingCart;
import com.datn.entity.Users;
import com.datn.service.ProductService;
import com.datn.service.ShoppingCartService;
import com.datn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class ShoppingCartRestController {

	@Autowired
    UserService svUser;
	
	@Autowired
	ProductService svPro;

    @Autowired
    ShoppingCartService svCartService;

    @GetMapping("getcart")
    public List<ShoppingCart> getAll() {
        return svCartService.GetAll();
    }

    @GetMapping("createCart/{idp}")
    public ShoppingCart createCart(@PathVariable("idp") Integer idp) {
    	 ShoppingCart shoppingCart = new ShoppingCart();
        //Users u = svUser.findByid(req.getRemoteUser());
    	Users u = svUser.findByid("user1");
    	Product a = svPro.getById(idp);
    	shoppingCart.setUser(u);
    	shoppingCart.setProduct(a);
    	shoppingCart.setQuantity(1);
    	shoppingCart.setStoreid(a.getStore().id);
        return svCartService.create(shoppingCart);
    }

    @GetMapping("putcart/{id}")
    public ShoppingCart updateCart(@PathVariable("id") Integer id) {
        //Users u = svUser.findByid(req.getRemoteUser());
        ShoppingCart shoppingCart = svCartService.getCartPr("user1", id);;
        shoppingCart.setQuantity(shoppingCart.quantity+=1);
        return svCartService.update(shoppingCart);
    }

    @DeleteMapping("/cart/{id}")
    public void deleteCart(@PathVariable("id") Integer id) {
        svCartService.delete(id);
    }

    @GetMapping("/cartUser/{id}")
    public List<ShoppingCart> getCartUser(@PathVariable("id") String userid) {
        return svCartService.findByUser(userid);
    }

    @GetMapping("/cart/{id}")
    public List<ShoppingCart> getStore(@PathVariable("id") String id) {
        return svCartService.findByStore(id);
    }

    @GetMapping("/addquantity/{id}")
    public ShoppingCart addquantity(@PathVariable("id") Integer id){
        ShoppingCart a = svCartService.getById(id);
        a.setQuantity(a.quantity+=1);
        return svCartService.update(a);
    }

    @GetMapping("/apartquantity/{id}")
    public ShoppingCart apartquantity(@PathVariable("id") Integer id){
        ShoppingCart a = svCartService.getById(id);
        if(a.getQuantity() == 1){
            svCartService.delete(id);
            return null;
        }else{
        a.setQuantity(a.quantity-1);
        return svCartService.update(a);
        }
    }

    @GetMapping("/checkStatus/{id}")
    public ShoppingCart checkStatus(@PathVariable("id") Integer id){
        ShoppingCart a = svCartService.getById(id);
        if(a.isStatus() == true){
            a.setStatus(false);
        }else{
            a.setStatus(true);
        }
        return svCartService.update(a);
    }

    @GetMapping("/cartTrue/{id}")
    public List<ShoppingCart> getCartTrue(@PathVariable("id") String id){
        return svCartService.getCartTrue(id);
    }

}
