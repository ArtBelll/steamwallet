package ru.steamwallet.swcommon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 04/08/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotExists extends RuntimeException {
    public UserNotExists(final String email) { super("Account with this email doesn't exist:" + email); }
    public static final long serialVersionUID = 0;
}
