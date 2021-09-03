package dk.db.manageaccounts.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.money.CurrencyUnit;
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
    private CurrencyUnit currency;  
    
    @ApiModelProperty(value = "Date-time at which the transaction happened.",
            example = "2021-09-06T10:15:30")
    private LocalDate transactionDateTime;    

    @ApiModelProperty(value = "Description of the transaction.")
    private String description;    

    @ApiModelProperty(value = "Balance on the sender's account after the transaction",
            example = "1000.00")
    private BigDecimal balanceAfter;       

    @ApiModelProperty(value = "Sender's account number",
            example = "1840936677")
    private String fromAccountNumber; 
    
    @ApiModelProperty(value = "Receiver’s account number",
            example = "1840931234")
    private String toAccountNumber; 

    @ApiModelProperty(value = "The type of the transaction")
    private TransactionType transactionType;

    
}
