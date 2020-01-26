package com.demo.springBootCURD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.exception.BadRequestException;
import com.demo.springBootCURD.exception.DealerNotFoundException;
import com.demo.springBootCURD.persistance.DealerDAOIf;

@Service("dealerService")
public class DealerService implements DealerServiceIf {
	
	@Autowired
	DealerDAOIf dealerDAO;
	
	public int add(Dealer dealer) throws BadRequestException {
        try {
            return dealerDAO.add(dealer);
        }
        catch (Exception ex){
            throw new BadRequestException(ex.getCause());
        }
    }
	
	public List<Dealer> findAll() {
        return dealerDAO.findAll();
    }
	
	public Dealer findDealerById(String dealerId) throws DealerNotFoundException {
		Dealer dealer = dealerDAO.findDealerById(dealerId);
        if (dealer == null) {
            throw new DealerNotFoundException("No Client found with id: " + dealerId);
        }
        return dealer;
	}
}
