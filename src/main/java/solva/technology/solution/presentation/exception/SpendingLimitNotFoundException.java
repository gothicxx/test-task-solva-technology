package solva.technology.solution.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpendingLimitNotFoundException extends RuntimeException {
    public SpendingLimitNotFoundException(String message) {super(message);}
}
