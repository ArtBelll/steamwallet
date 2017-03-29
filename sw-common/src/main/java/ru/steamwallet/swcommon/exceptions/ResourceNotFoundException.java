package ru.steamwallet.swcommon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

/**
 * Created by Sergey Ignatov on 24/02/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
    private String name;
    private long id = 0;
    private UUID uuid = null;

    public ResourceNotFoundException(final String name, long id) {
        this.name = name;
        this.id = id;
    }

    public ResourceNotFoundException(final String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDescription() {
        return "The requested URL was not found on the server. " +
                "If you entered the URL manually please check your spelling and try again.";
    }
}
