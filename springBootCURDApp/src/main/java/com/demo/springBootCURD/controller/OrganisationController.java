package com.demo.springBootCURD.controller;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.domain.Organisation;
import com.demo.springBootCURD.exception.BadRequestException;
import com.demo.springBootCURD.exception.ResourceNotFoundException;
import com.demo.springBootCURD.service.OrganisationServiceIf;
import static com.demo.springBootCURD.assembler.OrganisationAssembler.addOrganisationLinks;
import static com.demo.springBootCURD.assembler.DealerAssembler.addDealerLinks;

@RestController
@RequestMapping(value = "/dealerManagement", produces = "application/json", consumes = "application/json")

public class OrganisationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganisationController.class);

    @Autowired
    private OrganisationServiceIf organisationService;
    
    @RequestMapping(value = "/app/org/{orgId}", method = RequestMethod.GET)
    public ResponseEntity<Organisation> findById(@PathVariable("orgId") String orgId) throws ResourceNotFoundException {

        Organisation org = organisationService.findOrgById(orgId);
        addOrganisationLinks(org);

        return new ResponseEntity<>(org, HttpStatus.OK);
    }

    @RequestMapping(value = "/app/org/{orgId}/dealers", method = RequestMethod.GET)
    public ResponseEntity<List<Dealer>> findDealersByOrg(@PathVariable("orgId") String orgId)
            throws ResourceNotFoundException {

        List<Dealer> dealers = organisationService.findDealersByOrgId(orgId);
        for(Dealer dealer : dealers){
        	addDealerLinks(dealer);
        }
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

}
