package com.demo.springBootCURD.service;

import java.util.List;

import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.domain.Organisation;
import com.demo.springBootCURD.exception.OrgNotFoundException;

public interface OrganisationServiceIf {
	
	Organisation findOrgById(String orgId) throws OrgNotFoundException;
	
	List<Dealer> findDealersByOrgId(String orgId) throws OrgNotFoundException;
}
