package de.ato.urlservice.backend.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Url not valid")
public class UrlNotValidException extends Exception{
    public UrlNotValidException(String message) {
        super(message);
    }

    public UrlNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
