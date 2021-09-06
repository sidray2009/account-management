package dk.db.manageaccounts.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.db.manageaccounts.services.AccountsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Customer api handling all customer related operations")
@RestController
@RequestMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountsService accountsService;

	@ApiOperation(value = "Creates a new savings account for a customer")
	@PostMapping("/{customer-id}/new-account")
	ResponseEntity<String> createAccount(@PathVariable("customer-id") @NotBlank(message="customerId cannot be blank") String customerId) {
		
		String accountId = accountsService.createAccount(customerId);
		logger.debug("Account with Id {0} created for customer {1}.", accountId, customerId);

		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CACHE_CONTROL, "max-age=0")
				.header(HttpHeaders.CACHE_CONTROL, "no-cache").body(accountId);

	}

	@ApiOperation(value = "Fetches the ids of all the accounts belonging to a customer")
	@GetMapping("/{customer-id}/customer-accounts")
	ResponseEntity<List<String>> getCustomerAccountIds(@PathVariable("customer-id") @NotBlank(message="customerId cannot be blank") String customerId) {
		
		List<String> accountIds = accountsService.getCustomerAccountIds(customerId);
		logger.debug("Accounts for customer {1}: {0}", customerId, accountIds);

		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CACHE_CONTROL, "max-age=0")
				.header(HttpHeaders.CACHE_CONTROL, "no-cache").body(accountIds);

	}
	
}
