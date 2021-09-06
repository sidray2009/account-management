package dk.db.manageaccounts.controllers;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dk.db.manageaccounts.model.Account;
import dk.db.manageaccounts.model.Transaction;
import dk.db.manageaccounts.model.TransactionType;
import dk.db.manageaccounts.services.AccountsService;
import dk.db.manageaccounts.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Accounts api")
@RestController
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountsService accountsService;
	@Autowired
	private TransactionService transactionService;
	
	@ApiOperation(value = "Fetches the details of an account")
	@GetMapping("/{account-id}")
	ResponseEntity<Account> getAccount(@PathVariable("account-id") @NotBlank(message="accountId cannot be blank") String accountId) {
		Account account = accountsService.getAccount(accountId);
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CACHE_CONTROL, "max-age=0")
				.header(HttpHeaders.CACHE_CONTROL, "no-cache").body(account);

	}

	@ApiOperation(value = "Deposits money into an account")
	@PostMapping("/{account-id}/deposit")
	ResponseEntity<String> depositToAccount(@PathVariable("account-id") String accountId, @RequestParam @Positive double amount) {
		
		String result = accountsService.manageTransaction(BigDecimal.valueOf(amount), TransactionType.DEPOSIT,accountId);
		
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CACHE_CONTROL, "max-age=0")
				.header(HttpHeaders.CACHE_CONTROL, "no-cache").body(result);
	}

	@ApiOperation(value = "Withdraws money from an account")
	@PostMapping("/{account-id}/withdraw")
	ResponseEntity<String> withdrawFromAccount(@PathVariable("account-id") @NotBlank(message="accountId cannot be blank") String accountId, @RequestParam @Positive double amount) {
        
		String result = accountsService.manageTransaction(BigDecimal.valueOf(amount), TransactionType.WITHDRAWAL,accountId);
		
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CACHE_CONTROL, "max-age=0")
				.header(HttpHeaders.CACHE_CONTROL, "no-cache").body(result);

	}

	@ApiOperation(value = "Fetches the latest balance for an account")
	@GetMapping("/{account-id}/check-balance")
	ResponseEntity<String> getAvailableBalance(@PathVariable("account-id") @NotBlank(message="accountId cannot be blank") String accountId) {
		BigDecimal balance = accountsService.getAvailableBalance(accountId);
		NumberFormat cf = NumberFormat.getCurrencyInstance();
		double balanceD = balance.doubleValue();		
		
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CACHE_CONTROL, "max-age=0")
				.header(HttpHeaders.CACHE_CONTROL, "no-cache").body(cf.format(balanceD));

	}
	
	@ApiOperation(value = "Fetches the latest transactions for an account. The limit variable defines the number of transactions fetched with the latest first")
	@GetMapping("/{account-id}/transactions")
	public ResponseEntity<List<Transaction>> getLatestTransactions(@PathVariable("account-id") @NotBlank(message="accountId cannot be blank") String accountId, @RequestParam @Positive int limit){
		List<Transaction> transactions = transactionService.getLatestTransactions(accountId, limit);
		
		logger.debug("Transactions for account {1}: {0}", accountId, transactions);
		
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CACHE_CONTROL, "max-age=0")
				.header(HttpHeaders.CACHE_CONTROL, "no-cache").body(transactions);
	}
	

}
