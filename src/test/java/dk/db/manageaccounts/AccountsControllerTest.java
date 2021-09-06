package dk.db.manageaccounts;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import dk.db.manageaccounts.model.Account;
import dk.db.manageaccounts.services.AccountsService;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountsControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AccountsService accountsService;

    @Test
    void testGetAccount() throws Exception {
    	
    	String accountId = "1";
    	Account account = new Account();
	    account.setAccountId(accountId);
		account.setCustomerId("2");		
    	
		when(accountsService.getAccount(accountId)).thenReturn(account);
		
		
		mvc.perform(get("/accounts/{account-id}", "1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))		
		.andExpect(jsonPath("$.accountId", is("1")))
		.andExpect(jsonPath("$.customerId", is("2")))
		;

        
    }

	
}
