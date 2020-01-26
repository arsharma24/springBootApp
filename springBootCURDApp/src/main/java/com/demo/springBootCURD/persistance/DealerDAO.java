package com.demo.springBootCURD.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.domain.Organisation;
import com.demo.springBootCURD.exception.BadRequestException;
import com.demo.springBootCURD.exception.DealerNotFoundException;
import com.demo.springBootCURD.exception.OrgNotFoundException;

@Repository
public class DealerDAO implements DealerDAOIf {
	
	static Map<String, Dealer> dealerMap = new HashMap<>();
	
	public static Map<String, Dealer> getDealerData() {
		dealerMap.put("XABUS12345", new Dealer("XABUS12345", "XABUS", new Organisation("OR123", "OR_ABUS", "US_ORG")));
		dealerMap.put("XABNZ12345", new Dealer("XABNZ12345", "XABNZ", new Organisation("OR123", "OR_ABNZ", "US_ORG")));
		dealerMap.put("XABDE85432", new Dealer("XABDE85432", "XABDE", new Organisation("OR854", "OR_ABDE", "DE_ORG")));
		dealerMap.put("XABRU07595", new Dealer("XABRU07595", "XABRU", new Organisation("OR075", "OR_ABRU", "RU_ORG")));
		dealerMap.put("XABCH02584", new Dealer("XABCH02584", "XABCH", new Organisation("OR025", "OR_ABCH", "CH_ORG")));
		return dealerMap;
	}
	
	public int add(Dealer dealer) throws BadRequestException {
		
		if(!dealerMap.containsKey(dealer.getDealerId())){
			Organisation newOrg = new Organisation(dealer.getOrganisation().getOrgId(), dealer.getOrganisation().getOrgName(), dealer.getOrganisation().getDescription());
			Dealer newDealer = new Dealer(dealer.getDealerId(), dealer.getDealerName(), newOrg);
			dealerMap.put(dealer.getDealerId(), newDealer);
		} else {
			throw new BadRequestException("Duplicate dealer");
		}
		return 1;
	}
	
	public List<Dealer> findAll() {
		return new ArrayList(getDealerData().values());
	}
	
	public Dealer findDealerById(String dealerId) throws DealerNotFoundException {
		if(dealerMap.containsKey(dealerId)){
			return dealerMap.get(dealerId);
		}
		return null;
	}
	
	public Organisation findOrgById(String orgId) throws OrgNotFoundException {
		List<Dealer> dealerList = new ArrayList(dealerMap.values());
		for(Dealer dealer : dealerList){
			dealer.getOrganisation().getId().equals(orgId);
			return dealer.getOrganisation();
		}
		return null;
	}
	
	public List<Dealer> findDealersByOrgId(String orgId) throws OrgNotFoundException {
		List<Dealer> dealerList = new ArrayList(dealerMap.values());
		List<Dealer> dealers = new ArrayList<>();
		for(Dealer dealer : dealerList){
			dealer.getOrganisation().getId().equals(orgId);
			dealers.add(dealer);
		}
		return !dealers.isEmpty() ? dealers : null ;
	}
}
