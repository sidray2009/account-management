package dk.db.manageaccounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AccountManagementExceptionResponseMapper {

	@ResponseBody
	@ExceptionHandler(InsufficientFundsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String insufficientFundsExceptionHandler(InsufficientFundsException ife) {
		String message = "Insufficient funds in the account. So transfer cannot be made.";
		return message;
	}

}
