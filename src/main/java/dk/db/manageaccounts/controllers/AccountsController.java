package dk.db.manageaccounts.controllers;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dk.db.manageaccounts.model.Account;

@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/create/{customer-id}")
	ResponseEntity<Account> createAccount(@PathVariable("customer-id") String customerId) {
		return null;

	}

	@GetMapping("/{account-id}")
	ResponseEntity<Account> getAccount(@PathVariable("account-id") String accountId) {
		return null;

	}

	@PostMapping("/{account-id}/deposit")
	ResponseEntity<String> depositAmount(@PathVariable("account-id") String accountId,
			@RequestParam("amount") BigDecimal amount) {
		return null;

	}

	@PostMapping("/{account-id}/withdraw")
	ResponseEntity<String> withdrawAmount(@PathVariable("account-id") String accountId,
			@RequestParam("amount") BigDecimal amount) {
		return null;

	}
	
	@GetMapping("/{account-id}/balance")
	ResponseEntity<BigDecimal> getAvailableBalance(@PathVariable("account-id") String accountId) {
		return null;

	}
	

}
