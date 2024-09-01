package telran.java53.accounting.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PasswordExpiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PasswordExpiredException(String message) {
		super(message);
	}


}
