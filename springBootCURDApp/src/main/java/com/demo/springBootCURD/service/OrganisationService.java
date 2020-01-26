package com.demo.springBootCURD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.domain.Organisation;
import com.demo.springBootCURD.exception.OrgNotFoundException;
import com.demo.springBootCURD.persistance.DealerDAOIf;

@Service("organisationService")
public class OrganisationService implements OrganisationServiceIf {
	
	@Autowired
	DealerDAOIf dealerDAO;
	
	public Organisation findOrgById(String orgId) throws OrgNotFoundException {

        Organisation org = dealerDAO.findOrgById(orgId);
        if (org == null) {
            throw new OrgNotFoundException("No Organisation found with id: " + orgId);
        }
        return org;
    }
	
	public List<Dealer> findDealersByOrgId(String orgId) throws OrgNotFoundException {
		
        return dealerDAO.findDealersByOrgId(orgId);
    }

}