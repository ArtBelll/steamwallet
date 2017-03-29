package ru.steamwallet.swcommon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 25/02/16.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@SuppressWarnings("serial")
public class UploadException extends RuntimeException {
    private Exception originException;

    public UploadException(Exception ex) {
        this.originException = ex;
    }

    public Exception getOriginException() {
        return originException;
    }
}
