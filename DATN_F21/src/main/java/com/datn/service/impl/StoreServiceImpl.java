package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.DAO.StoreDAO;
import com.datn.entity.Store;
import com.datn.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService{
	@Autowired StoreDAO daoStoreDAO;

	@Override
	public List<Store> getAllStore() {
		return daoStoreDAO.findAll();
	}

	@Override
	public List<Store> getFindStoreByName(String nameStore) {
		return daoStoreDAO.getFindStoreByName(nameStore);
	}

}
