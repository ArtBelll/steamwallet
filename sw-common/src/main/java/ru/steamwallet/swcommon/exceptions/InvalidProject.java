package ru.steamwallet.swcommon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 01/03/16.
 */
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
@SuppressWarnings("serial")
public class InvalidProject extends RuntimeException {
}
