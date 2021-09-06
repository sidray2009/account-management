package dk.db.manageaccounts.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.db.manageaccounts.model.Transaction;
import dk.db.manageaccounts.repository.AccountsRepository;

@Service
public class TransactionService {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	public List<Transaction> getLatestTransactions(String accountId, int limit){
		List<Transaction> transactions = accountsRepository.getAllTransactions();

		Map<String, List<Transaction>> transactionsByAccount = transactions.stream()
				.collect(Collectors.groupingBy(Transaction::getAccountId));

		return transactionsByAccount.get(accountId).stream()
				.sorted(Comparator.comparing(Transaction::getTransactionDateTime).reversed())
				.limit(limit)
				.collect(Collectors.toList());
	}
	
	public Transaction getTransaction( String transactionId) {
		return accountsRepository.findTransactionById(transactionId);
	}

}
