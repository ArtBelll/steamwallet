package ru.steamwallet.swcommon.exceptions;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 08/04/16.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
@SuppressWarnings("serial")
public class NoAccessException extends RuntimeException {
    public NoAccessException() {
        super("No access");
    }

    public NoAccessException(final @NonNull String message) {
        super(message);
    }
}
