package com.lendico.challenge.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lendico.challenge.domain.Loan;
import com.lendico.challenge.domain.Payment;
import com.lendico.challenge.service.ChallengeService;

@RestController
@RequestMapping("/generate-plan")
public class ChallengeController {
	
    private final ChallengeService service;

    public ChallengeController(ChallengeService service) {
        this.service = service;
    }
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<Payment>> generate(@Valid @RequestBody Loan loan) {

    	List<Payment> plan = service.generatePlan(loan);

        return new ResponseEntity<List<Payment>>(plan, HttpStatus.OK);
    }	
}
