package dk.db.manageaccounts.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.db.manageaccounts.exceptions.InsufficientFundsException;
import dk.db.manageaccounts.model.Account;
import dk.db.manageaccounts.model.Transaction;
import dk.db.manageaccounts.model.TransactionType;
import dk.db.manageaccounts.repository.AccountsRepository;

@Service
public class AccountsService {
	
	 @Autowired
	 private AccountsRepository accountsRepository;
	 
	 public String createAccount(String customerId) {		 
		 Account account = new Account();
	     account.setAccountId(accountsRepository.getNextAccountId());
		 account.setCustomerId(customerId);
		 return accountsRepository.saveAccount(account);
	 }
	 
	 public List<String> getCustomerAccountIds(String customerId){
		 return accountsRepository.findAllAccountIdsByCustomerId(customerId);
	 }
	 
	 public Account getAccount(String accountId) {
		 return accountsRepository.findAccountById(accountId);
	 }
	 
	 public BigDecimal getAvailableBalance(String accountId) {
		 return accountsRepository.findAccountById(accountId).getCurrentBalance();
	 }
	 
	 public synchronized String manageTransaction(BigDecimal amount, TransactionType transactionType,
			 String accountId) {
		 Account account = accountsRepository.findAccountById(accountId);
		 BigDecimal currentBalance = account.getCurrentBalance();		 

		 BigDecimal newBalance;
		 if (transactionType.equals(TransactionType.DEPOSIT)) {
			 newBalance = currentBalance.add(amount, MathContext.DECIMAL64);
		 } else { //Withdrawal
			 if (amount.compareTo(currentBalance) > 0)
				 throw new InsufficientFundsException("Insufficient funds in account " + accountId);
			 newBalance = currentBalance.subtract(amount, MathContext.DECIMAL64);
		 }

		 Transaction transaction = new Transaction();
		 transaction.setTransactionId(accountsRepository.getNextTransactionId());
		 transaction.setAmount(amount);
		 transaction.setTransactionDateTime(LocalDateTime.now());
		 transaction.setAccountId(accountId);
		 transaction.setTransactionType(transactionType);
		 transaction.setBalanceAfter(newBalance);
		 String savedTransactionId = accountsRepository.saveTransaction(transaction);

		 account.setCurrentBalance(newBalance);
		 account.setLatestTransactionDateTime(transaction.getTransactionDateTime());

		 return "Account transaction completely successfully with transaction id: " + savedTransactionId;

	 }

}
