package dk.db.manageaccounts.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.money.CurrencyUnit;
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
    private BigDecimal currentBalance;    

    @ApiModelProperty(value = "Currency of the account as currency code (ISO 4217)",
            example = "DKK")
    private CurrencyUnit currency;    

    @ApiModelProperty(value = "Date-time of the latest transaction booked on the account",
            example = "2007-12-03T10:15:30")
    private LocalDateTime latestTransactionDateTime;

    
}
