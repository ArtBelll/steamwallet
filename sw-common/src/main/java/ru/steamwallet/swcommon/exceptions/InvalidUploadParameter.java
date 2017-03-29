package ru.steamwallet.swcommon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 25/02/16.
 */
@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
@SuppressWarnings("serial")
public class InvalidUploadParameter extends RuntimeException {
}
