package ru.steamwallet.swcommon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 11/08/16.
 */
@ResponseStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
@SuppressWarnings("serial")
public class UnSupportedClientVersion extends RuntimeException {
    public UnSupportedClientVersion(final String version) {
        super("Unsupported client version: " + version);
    }
}
