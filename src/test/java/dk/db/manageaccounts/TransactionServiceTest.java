package dk.db.manageaccounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import dk.db.manageaccounts.model.Transaction;
import dk.db.manageaccounts.repository.AccountsRepository;
import dk.db.manageaccounts.services.TransactionService;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionServiceTest {
	
	@Autowired
	TransactionService transactionService;    

    @MockBean
    private AccountsRepository accountsRepository;
    
    
    @Test
    @DisplayName("Tests that the last 10 transactions for an account are fetched correctly with the latest one appearing first")
    void testGetLatestTransactions() throws Exception {
    	String accountId = "1";
    	List<Transaction> transactions = TestUtils.generateTestTransactions(accountId);
    	
		when(accountsRepository.getAllTransactions()).thenReturn(transactions);
		
		List<Transaction> latest10Transactions = transactionService.getLatestTransactions(accountId, 10);
		
		System.out.println("original transactions: " + transactions);
		System.out.println("latest10Transactions: " + latest10Transactions);
        
        assertEquals(10, latest10Transactions.size());
        assertEquals(transactions.get(10), latest10Transactions.get(0));
        assertEquals(transactions.get(1), latest10Transactions.get(9));
        //the timestamp for the 10th transaction is greater than the timestamp of the 1st of the 11 transactions in total.
        assertTrue(latest10Transactions.get(9).getTransactionDateTime().compareTo(transactions.get(0).getTransactionDateTime()) > 0);
    	
    	verify(accountsRepository, times(1)).getAllTransactions();            	
        
    }
    
    

}
