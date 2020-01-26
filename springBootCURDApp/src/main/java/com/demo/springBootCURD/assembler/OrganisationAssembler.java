package com.demo.springBootCURD.assembler;

import org.springframework.hateoas.Link;

import com.demo.springBootCURD.controller.OrganisationController;
import com.demo.springBootCURD.domain.Organisation;
import com.demo.springBootCURD.exception.ResourceNotFoundException;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class OrganisationAssembler {
	
	 public static Organisation addOrganisationLinks(Organisation organisation) throws ResourceNotFoundException {

	        Link selfLink = linkTo(methodOn(OrganisationController.class).findById(organisation.getOrgId())).withSelfRel();
	        Link clientsLink = linkTo(methodOn(OrganisationController.class).findDealersByOrg(organisation.getOrgId())).withRel("dealers");
	       
	        organisation.add(selfLink);
	        organisation.add(clientsLink);

	        return organisation;
	    }

}
