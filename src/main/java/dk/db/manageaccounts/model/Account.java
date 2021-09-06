package dk.db.manageaccounts.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Main account object")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

    @NotNull
    @ApiModelProperty(value = "The unique ID of the account",
    required = true,
    example = "3500047428")
    private String accountId;    

    @ApiModelProperty(value = "Current balance booked on the account",
            example = "1000.00")
    private BigDecimal currentBalance = BigDecimal.ZERO;    

    @ApiModelProperty(value = "Currency of the account as currency code (ISO 4217)",
            example = "DKK")
    private final String currency;    

    @ApiModelProperty(value = "Date-time of the latest transaction booked on the account",
            example = "2007-12-03T10:15:30")
    private LocalDateTime latestTransactionDateTime;
    
    @NotNull
    @ApiModelProperty(value = "The customer ID of the account",
    required = true,
    example = "1234")
    private String customerId;

	
    
    public Account() {
    	super();
    	this.currency = "DKK";		
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getCurrency() {
		return currency;
	}	

	public LocalDateTime getLatestTransactionDateTime() {
		return latestTransactionDateTime;
	}

	public void setLatestTransactionDateTime(LocalDateTime latestTransactionDateTime) {
		this.latestTransactionDateTime = latestTransactionDateTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((currentBalance == null) ? 0 : currentBalance.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((latestTransactionDateTime == null) ? 0 : latestTransactionDateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (currentBalance == null) {
			if (other.currentBalance != null)
				return false;
		} else if (!currentBalance.equals(other.currentBalance))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (latestTransactionDateTime == null) {
			if (other.latestTransactionDateTime != null)
				return false;
		} else if (!latestTransactionDateTime.equals(other.latestTransactionDateTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", currentBalance=" + currentBalance + ", currency=" + currency
				+ ", latestTransactionDateTime=" + latestTransactionDateTime + ", customerId=" + customerId + "]";
	}

	
    
    

    
}
