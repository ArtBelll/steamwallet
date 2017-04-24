package ru.steamwallet.swcommon.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sergey Ignatov on 04/08/16.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyRegistered extends RuntimeException {

    @Getter
    private int index = -1;

    public AlreadyRegistered(String message) { super(message); }
    public AlreadyRegistered(String message, int index) { super(message); this.index = index; }
    public static final long serialVersionUID = 0;
}
