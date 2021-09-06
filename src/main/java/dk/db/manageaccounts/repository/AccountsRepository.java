package dk.db.manageaccounts.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dk.db.manageaccounts.model.Account;
import dk.db.manageaccounts.model.Transaction;

@Service
public class AccountsRepository {
	
	private static Map<String, Account> accounts = new ConcurrentHashMap<>();
	private static Map<String, Transaction> transactions = new ConcurrentHashMap<>();
	
	private static AtomicLong accountIdGenerator = new AtomicLong();
	private static AtomicLong transactionIdGenerator = new AtomicLong();

	public String getNextAccountId() {
		return accountIdGenerator.incrementAndGet() + "";
	}

	public String getNextTransactionId() {
		return transactionIdGenerator.incrementAndGet() + "";
	}

	public String saveAccount(Account account) {
		accounts.put(account.getAccountId(), account);
		return account.getAccountId();
	}

	public Account findAccountById(String accountId) {
		return accounts.get(accountId);
	}	

	public List<String> findAllAccountIdsByCustomerId(String customerId) {
		return accounts.values().stream().filter(acc -> acc.getCustomerId().equals(customerId))
				.map(s -> s.getAccountId()).collect(Collectors.toList());	
	}
	
	public Transaction findTransactionById(String transactionId) {
		return transactions.get(transactionId);
	}
	
	public String saveTransaction(Transaction transaction) {
		transactions.put(transaction.getTransactionId(), transaction);
		return transaction.getTransactionId();
	}
	
	public List<Transaction> getAllTransactions(){
		return transactions.values().stream().collect(Collectors.toList());
		
	}

	

}
