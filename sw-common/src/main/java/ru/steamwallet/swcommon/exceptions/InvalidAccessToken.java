package ru.steamwallet.swcommon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 16/06/16.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidAccessToken extends RuntimeException {
    public InvalidAccessToken(String message) { super(message); }
    public static final long serialVersionUID = 0;
}
