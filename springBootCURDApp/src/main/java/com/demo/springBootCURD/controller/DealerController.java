package com.demo.springBootCURD.controller;

import java.util.List;


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
import com.demo.springBootCURD.exception.BadRequestException;
import com.demo.springBootCURD.exception.ResourceNotFoundException;
import com.demo.springBootCURD.service.DealerServiceIf;

import static com.demo.springBootCURD.assembler.DealerAssembler.addDealerLinks;

@RestController
@RequestMapping(value = "/dealerManagement", produces = "application/json", consumes = "application/json")
public class DealerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DealerController.class);
	
	@Autowired
	private DealerServiceIf dealerService; 
	
	@RequestMapping(value = "/app/dealers/add", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody(required = true) Dealer dealer) throws BadRequestException {
        LOGGER.debug("Adding a new Dealer details: {}", dealer);
        dealerService.add(dealer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/app/dealers", method = RequestMethod.GET)
    public ResponseEntity<List<Dealer>> findAll() throws ResourceNotFoundException{
        LOGGER.debug("All Dealers");
        List<Dealer> dealers = dealerService.findAll();
        for(Dealer dealer : dealers){
            addDealerLinks(dealer);
        }
        return new ResponseEntity<List<Dealer>>(dealers, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/app/dealers/dealer/{dealerId}", method = RequestMethod.GET)
    public ResponseEntity<Dealer> findById(@PathVariable("dealerId") String dealerId) throws ResourceNotFoundException {
        LOGGER.debug("Finding Dealer with id: {}", dealerId);

        Dealer dealer = dealerService.findDealerById(dealerId);
        addDealerLinks(dealer);
        LOGGER.debug("Found Client entry with information: {}", dealer);

        return new ResponseEntity<Dealer>(dealer, HttpStatus.OK);
    }

}
