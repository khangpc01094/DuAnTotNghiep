package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.datn.DAO.StoreDAO;
import com.datn.DAO.UsersDAO;
import com.datn.entity.Product;
import com.datn.entity.Store;
import com.datn.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired 
    StoreDAO daoStoreDAO;
    
    @Autowired
    UsersDAO daoUser;

	@Autowired
	HttpServletRequest req;

	@Override
	public List<Store> getAllStore() {
		return daoStoreDAO.findAll();
	}

	@Override
	public List<Store> getFindStoreByName(String nameStore) {
		return daoStoreDAO.getFindStoreByName(nameStore);
	}

	@Override
    public Store getByIdStore(Integer id) {
        return daoStoreDAO.findById(id).get();
    }

    @Override
    public Store create(Store store) {
        String user = req.getRemoteUser();
        store.setUser(daoUser.findById(user).get());
        store.setStatus(true);
       return daoStoreDAO.save(store);
    }

    @Override
    public Store getStoreByUserid(String id) {
        return daoStoreDAO.getStoreByUser(id);
    }
  
	@Override
	public List<Store> findAllStores() {
		return daoStoreDAO.findAll();
	}

	@Override
	public Store findStoreByUserId(String userid) {
		return daoStoreDAO.findStoreByUserId(userid);
	}

	@Override
	public List<Product> findByAllProduct(Integer sid) {
		return daoStoreDAO.findByAllProduct(sid);
	}
}
