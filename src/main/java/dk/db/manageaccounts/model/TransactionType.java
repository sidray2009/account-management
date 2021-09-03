package dk.db.manageaccounts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "The tranfer type name for transactions")
public enum TransactionType {
	DEPOSIT,
	WITHDRAWAL;

    @JsonCreator
    public TransactionType fromString(String name) {
        return TransactionType.valueOf(name.toUpperCase());
    }
    @JsonValue
    public String getValue() {
        return name().toLowerCase();
    }

}
