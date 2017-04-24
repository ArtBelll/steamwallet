package ru.steamwallet.swcommon.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 16/06/16.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {

    @Getter
    private int index = -1;

    public BadRequest(String message) { super(message); }
    public BadRequest(String message, int index) { super(message); this.index = index; }
    public static final long serialVersionUID = 0;
}
