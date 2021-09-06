package dk.db.manageaccounts.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The transaction object that contains information regarding a transaction.")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

	@NotNull
	@ApiModelProperty(value = "The unique ID of the transaction.",
            example = "8211903244")
    private String transactionId;    

    @ApiModelProperty(value = "The amount of the transaction in the currency of the account.",
            example = "100.00")
    private BigDecimal amount;

    @ApiModelProperty(value = "Currency of the transaction as currency code (ISO 4217).",
            example = "DKK")
    private String currency = "DKK";  
    
    @ApiModelProperty(value = "Date-time at which the transaction happened.",
            example = "2021-09-06T10:15:30")
    private LocalDateTime transactionDateTime;       

    @ApiModelProperty(value = "Balance on the account after the transaction",
            example = "1000.00")
    private BigDecimal balanceAfter;       

    @ApiModelProperty(value = "Account Id",
            example = "1840936677")
    private String accountId;     

    @ApiModelProperty(value = "The type of the transaction")
    private TransactionType transactionType;
    
    public Transaction() {
    	super();
    	this.currency = "DKK";		
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public BigDecimal getBalanceAfter() {
		return balanceAfter;
	}

	public void setBalanceAfter(BigDecimal balanceAfter) {
		this.balanceAfter = balanceAfter;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((balanceAfter == null) ? 0 : balanceAfter.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((transactionDateTime == null) ? 0 : transactionDateTime.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		Transaction other = (Transaction) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (balanceAfter == null) {
			if (other.balanceAfter != null)
				return false;
		} else if (!balanceAfter.equals(other.balanceAfter))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (transactionDateTime == null) {
			if (other.transactionDateTime != null)
				return false;
		} else if (!transactionDateTime.equals(other.transactionDateTime))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionType != other.transactionType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", currency=" + currency
				+ ", transactionDateTime=" + transactionDateTime + ", balanceAfter=" + balanceAfter + ", accountId="
				+ accountId + ", transactionType=" + transactionType + "]";
	}
    
    

	
    
    
    

    
}
