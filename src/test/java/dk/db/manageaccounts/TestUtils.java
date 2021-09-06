package dk.db.manageaccounts;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dk.db.manageaccounts.model.Transaction;
import dk.db.manageaccounts.model.TransactionType;

public class TestUtils {
	
	public static List<Transaction> generateTestTransactions(String accountId) {
    	List<Transaction> transactions = new ArrayList<>();
		for (int i = 1; i < 12; i++) { // 11 transactions are created
			Transaction transaction = new Transaction();
			transaction.setTransactionId(i + "");
			transaction.setAmount(BigDecimal.valueOf(10 * i));
			transaction.setTransactionDateTime(LocalDateTime.now());
			transaction.setAccountId(accountId);
			transaction.setTransactionType(TransactionType.DEPOSIT);
			transactions.add(transaction);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		return transactions;
    	
    }

}
