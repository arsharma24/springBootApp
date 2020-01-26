package com.demo.springBootCURD.persistance;

import java.util.List;

import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.domain.Organisation;
import com.demo.springBootCURD.exception.BadRequestException;
import com.demo.springBootCURD.exception.DealerNotFoundException;
import com.demo.springBootCURD.exception.OrgNotFoundException;

public interface DealerDAOIf {
	
	int add(Dealer dealer) throws BadRequestException;
	
	List<Dealer> findAll();

	Dealer findDealerById(String dealerId) throws DealerNotFoundException;
	
	Organisation findOrgById(String orgId) throws OrgNotFoundException;
	
	List<Dealer> findDealersByOrgId(String orgId) throws OrgNotFoundException;
}
