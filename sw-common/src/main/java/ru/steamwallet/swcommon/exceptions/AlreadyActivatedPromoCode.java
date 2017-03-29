package ru.steamwallet.swcommon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Artur Belogur on 25.01.17.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyActivatedPromoCode extends RuntimeException {
    public AlreadyActivatedPromoCode(String message) { super(message); }
    public static final long serialVersionUID = 0;
}