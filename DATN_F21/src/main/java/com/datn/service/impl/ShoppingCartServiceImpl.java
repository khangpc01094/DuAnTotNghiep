package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.datn.DAO.ShoppingCartDAO;
import com.datn.entity.ShoppingCart;
import com.datn.entity.Total;
import com.datn.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    @Autowired
    ShoppingCartDAO daoCart;
    
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return daoCart.save(shoppingCart);
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
    public List<ShoppingCart> findByStore(String id) {
        return daoCart.findByStore(id);
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
    public List<ShoppingCart> getCartTrue(String id) {
        return daoCart.getCartTrue(id);
    }

	@Override
	public List<Total> getAllTotal(String id) {
		return daoCart.getAllPrice(id);
	}

    @Override
    public void deleteByUser(String id) {

    }

    @Override
    public List<ShoppingCart> getBySandU(String idu, Integer ids) {
        return daoCart.getByStoreandByUser(idu, ids);
    }

    @Override
    public Integer getSumQuantity(String id) {
        return daoCart.getSumQuantity(id);
    }

}
