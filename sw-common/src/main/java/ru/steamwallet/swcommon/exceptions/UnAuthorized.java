package ru.steamwallet.swcommon.exceptions;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 14/03/16.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
@SuppressWarnings("serial")
public class UnAuthorized extends RuntimeException {
    public UnAuthorized() {
        super("");
    }

    public UnAuthorized(final @NonNull String message) {
        super(message);
    }
}
