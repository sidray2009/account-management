package dk.db.manageaccounts.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.db.manageaccounts.model.Transaction;

@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionsController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping()
	public ResponseEntity<Boolean> saveTransaction(@RequestBody Transaction transaction){
		return null; 
	}
	
	@GetMapping("/{account-id}")
	public ResponseEntity<List<Transaction>> getLatestTransactions(@PathVariable("account-id") String accountId){
		return null; 
	}
	
	

}
