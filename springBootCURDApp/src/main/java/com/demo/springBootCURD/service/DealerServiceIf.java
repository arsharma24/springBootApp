package com.demo.springBootCURD.service;

import java.util.List;

import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.exception.BadRequestException;
import com.demo.springBootCURD.exception.DealerNotFoundException;

public interface DealerServiceIf {
	
	int add(Dealer dealer) throws BadRequestException;
	
	List<Dealer> findAll();
	
	Dealer findDealerById(String dealerId) throws DealerNotFoundException;

}
