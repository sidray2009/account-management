package dk.db.manageaccounts.controllers;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.db.manageaccounts.model.Transaction;
import dk.db.manageaccounts.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Transactions api")
@RestController
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionsController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TransactionService transactionService;	
	
	@ApiOperation(value = "Fetches the details of a transaction")
	@GetMapping("/{transaction-id}")
	ResponseEntity<Transaction> getTransaction(@PathVariable("transaction-id") @NotBlank(message="transactionId cannot be blank") String transactionId) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		
		logger.debug("Transaction with Id {0} details: {1}.", transactionId, transaction);
		
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CACHE_CONTROL, "max-age=0")
				.header(HttpHeaders.CACHE_CONTROL, "no-cache").body(transaction);

	}
	
	
	
	

}
