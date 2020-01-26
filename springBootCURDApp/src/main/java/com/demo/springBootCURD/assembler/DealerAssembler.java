package com.demo.springBootCURD.assembler;

import com.demo.springBootCURD.controller.DealerController;
import com.demo.springBootCURD.controller.OrganisationController;
import com.demo.springBootCURD.domain.Dealer;
import com.demo.springBootCURD.exception.ResourceNotFoundException;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;

public class DealerAssembler {
	
	 public static Dealer addDealerLinks(Dealer dealer) throws ResourceNotFoundException {

	        Link selfLink = linkTo(methodOn(DealerController.class).findById(dealer.getDealerId())).withSelfRel();
	        Link organisationLink = linkTo(methodOn(OrganisationController.class).findById(dealer.getOrganisation().getOrgId())).withRel("organisation");
	       
	        dealer.add(selfLink);
	        dealer.add(organisationLink);

	        return dealer;
	    }
}
