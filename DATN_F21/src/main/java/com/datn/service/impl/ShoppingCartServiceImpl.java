package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datn.DAO.ProductDAO;
import com.datn.DAO.ShoppingCartDAO;
import com.datn.DAO.UsersDAO;
import com.datn.entity.Product;
import com.datn.entity.ShoppingCart;
import com.datn.entity.Total;
import com.datn.entity.Users;
import com.datn.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    ShoppingCartDAO daoCart;

    @Autowired
    UsersDAO daoUser;

    @Autowired
    ProductDAO daoProduct;

    @Autowired
    HttpServletRequest req;
    
    @Override
    public ShoppingCart create(Integer id) {
        String usera = req.getRemoteUser();
        if(usera != null){
        ShoppingCart shoppingCart = new ShoppingCart();
        Users user = daoUser.findById(req.getRemoteUser()).get();
        Product a = daoProduct.findById(id).get();
        shoppingCart.setUser(user);
        shoppingCart.setProduct(a);
        shoppingCart.setQuantity(1);
        shoppingCart.setStoreid(a.getStore().id);
        return daoCart.save(shoppingCart);
        } else{
             return null;
        }
    }

    @Override
    public List<ShoppingCart> GetAll() {
        return daoCart.findAll();
    }

    @Override
    public List<ShoppingCart> findByUser(String userid) {
        return daoCart.findByIdUser(userid);
    }

    @Override
    public List<ShoppingCart> findByStore() {
        String user = req.getRemoteUser();
        return daoCart.findByStore(user);
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return daoCart.save(shoppingCart);
    }

    @Override
    public void delete(Integer id) {
        daoCart.deleteById(id);
    }

    @Override
    public ShoppingCart getCartPr(String idu, Integer idp) {
        return daoCart.getCartUser(idu, idp);
    }

    @Override
    public ShoppingCart getById(Integer id) {
        return daoCart.findById(id).get();
    }
 
    @Override
    public List<ShoppingCart> getCartTrue() {
        return daoCart.getCartTrue(req.getRemoteUser());
    }

	@Override
	public List<Total> getAllTotal() {
        String user = req.getRemoteUser();
        if(user != null){
        List<Total> list = daoCart.getAllPrice(user);
        for (Total s : list) {
            if (s.getReduce() > 300000) {
                s.setReduce(10.0);
                s.setPay((s.pay - (s.total * 10 / 100)) + 15000);
            } else if (s.getReduce() > 99000) {
                s.setReduce(5.0);
                s.setPay((s.pay - (s.total * 5 / 100)) + 15000);
            } else {
                s.setReduce(0.0);
                s.setPay(s.pay + 15000);
            }
        }
		return list;
    } else {
        return null;
    }
	}

    @Override
	public Double getTotaldd() {
        String user = req.getRemoteUser();
        if(user != null){
        List<Total> list = daoCart.getAllPrice(user);
            Double totaltam = 0.0;
        for (Total s : list) {
            if (s.getReduce() > 300000) {
                s.setReduce(10.0);
                s.setPay((s.pay - (s.total * 10 / 100)) + 15000);
            } else if (s.getReduce() > 99000) {
                s.setReduce(5.0);
                s.setPay((s.pay - (s.total * 5 / 100)) + 15000);
            } else {
                s.setReduce(0.0);
                s.setPay(s.pay + 15000);
            }
            totaltam += s.getPay();
        }
		return totaltam;
    } else {
        return null;
    }
	}


    @Override
    public void deleteByUser(String id) {

    }

    @Override
    public List<ShoppingCart> getBySandU(String idu, Integer ids) {
        return daoCart.getByStoreandByUser(idu, ids);
    }

    @Override
    public Integer getSumQuantity() {
        String user = req.getRemoteUser();
        return daoCart.getSumQuantity(user);
    }

}
