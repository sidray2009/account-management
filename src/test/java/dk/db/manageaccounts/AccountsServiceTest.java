package dk.db.manageaccounts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import dk.db.manageaccounts.exceptions.InsufficientFundsException;
import dk.db.manageaccounts.model.Account;
import dk.db.manageaccounts.model.Transaction;
import dk.db.manageaccounts.model.TransactionType;
import dk.db.manageaccounts.repository.AccountsRepository;
import dk.db.manageaccounts.services.AccountsService;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountsServiceTest {
    
    @Autowired
    AccountsService accountsService;    

    @MockBean
    private AccountsRepository accountsRepository;    

    @Test
    @DisplayName("Tests that an account is created correctly")
    void testCreateAccount() throws Exception {
    	String customerId = "1234";
    	when(accountsRepository.getNextAccountId()).thenReturn("1");
    	when(accountsRepository.saveAccount(any(Account.class))).thenReturn("1");
    	
    	String newAccountId = accountsService.createAccount(customerId);
    	
    	assertEquals("1", newAccountId);
    	
    	verify(accountsRepository, times(1)).getNextAccountId();
        verify(accountsRepository, times(1)).saveAccount(any(Account.class));
    }

    @Test
    @DisplayName("Tests that a deposit updates the balance of an account correctly")
    void testManageTransactionForDeposit() throws Exception {    	
		String accountId = "1";
    	Account account = new Account();
	    account.setAccountId(accountId);
		account.setCustomerId("1234");
		account.setCurrentBalance(BigDecimal.valueOf(1000));
		when(accountsRepository.findAccountById(accountId)).thenReturn(account);
		when(accountsRepository.getNextTransactionId()).thenReturn("10");
		when(accountsRepository.saveTransaction(any(Transaction.class))).thenReturn("10");
    	
		String transactionResult = accountsService.manageTransaction(BigDecimal.valueOf(200), TransactionType.DEPOSIT, accountId);
		
        assertEquals("Account transaction completely successfully with transaction id: 10", transactionResult);
        assertTrue(BigDecimal.valueOf(1200).compareTo(account.getCurrentBalance()) == 0);
        assertNotNull(account.getLatestTransactionDateTime());
       
    	
    	verify(accountsRepository, times(1)).findAccountById(accountId);
        verify(accountsRepository, times(1)).getNextTransactionId();
        verify(accountsRepository, times(1)).saveTransaction(any(Transaction.class));    	
        
    }
    
    @Test
    @DisplayName("Tests that a withdrawal updates the balance of an account correctly")
    void testManageTransactionForWithdrawal() throws Exception {    	
		String accountId = "1";
    	Account account = new Account();
	    account.setAccountId(accountId);
		account.setCustomerId("1234");
		account.setCurrentBalance(BigDecimal.valueOf(1000));
		when(accountsRepository.findAccountById(accountId)).thenReturn(account);
		when(accountsRepository.getNextTransactionId()).thenReturn("10");
		when(accountsRepository.saveTransaction(any(Transaction.class))).thenReturn("10");
    	
		String transactionResult = accountsService.manageTransaction(BigDecimal.valueOf(200), TransactionType.WITHDRAWAL, accountId);
		
        assertEquals("Account transaction completely successfully with transaction id: 10", transactionResult);
        assertTrue(BigDecimal.valueOf(800).compareTo(account.getCurrentBalance()) == 0);
        assertNotNull(account.getLatestTransactionDateTime());
       
    	
    	verify(accountsRepository, times(1)).findAccountById(accountId);
        verify(accountsRepository, times(1)).getNextTransactionId();
        verify(accountsRepository, times(1)).saveTransaction(any(Transaction.class));    	
        
    }
    
    @Test
    @DisplayName("Tests that the balance of an account is not updated when the transaction fails with an exception")
    void testManageTransactionUnchangedBalanceForException() throws Exception {    	
		String accountId = "1";
    	Account account = new Account();
	    account.setAccountId(accountId);
		account.setCustomerId("1234");
		account.setCurrentBalance(BigDecimal.valueOf(1000));
		when(accountsRepository.findAccountById(accountId)).thenReturn(account);
		when(accountsRepository.getNextTransactionId()).thenReturn("10");
		when(accountsRepository.saveTransaction(any(Transaction.class))).thenThrow(new RuntimeException("Cannot save transaction"));		
		
		Throwable throwable = assertThrows(RuntimeException.class, () -> accountsService
				.manageTransaction(BigDecimal.valueOf(200), TransactionType.WITHDRAWAL, accountId));
		
		assertEquals("Cannot save transaction", throwable.getMessage());    	
		
        assertTrue(BigDecimal.valueOf(1000).compareTo(account.getCurrentBalance()) == 0);
        assertNull(account.getLatestTransactionDateTime());       
    	
    	verify(accountsRepository, times(1)).findAccountById(accountId);
        verify(accountsRepository, times(1)).getNextTransactionId();
        verify(accountsRepository, times(1)).saveTransaction(any(Transaction.class));    	
        
    }
    
    @Test
    @DisplayName("Tests that the balance of an account is not updated and exception is thrown when there is insufficient funds in the account")
    void testManageTransactionUnchangedBalanceForInsufficientFunds() throws Exception {    	
		String accountId = "1";
    	Account account = new Account();
	    account.setAccountId(accountId);
		account.setCustomerId("1234");
		account.setCurrentBalance(BigDecimal.valueOf(200));
		when(accountsRepository.findAccountById(accountId)).thenReturn(account);
		when(accountsRepository.getNextTransactionId()).thenReturn("10");		
		when(accountsRepository.saveTransaction(any(Transaction.class))).thenReturn("10");
		
		Throwable throwable = assertThrows(InsufficientFundsException.class, () -> accountsService
				.manageTransaction(BigDecimal.valueOf(300), TransactionType.WITHDRAWAL, accountId));
		
		assertEquals("Insufficient funds in account " + accountId, throwable.getMessage());    	
		
        assertTrue(BigDecimal.valueOf(200).compareTo(account.getCurrentBalance()) == 0);
        assertNull(account.getLatestTransactionDateTime());       
    	
    	verify(accountsRepository, times(1)).findAccountById(accountId);
        verify(accountsRepository, times(0)).getNextTransactionId();
        verify(accountsRepository, times(0)).saveTransaction(any(Transaction.class));    	
        
    }

    
    
}
