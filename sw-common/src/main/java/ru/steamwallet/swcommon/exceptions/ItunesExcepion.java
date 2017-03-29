package ru.steamwallet.swcommon.exceptions;

/**
 * Created by Sergey Ignatov on 16/11/16.
 */
public class ItunesExcepion extends RuntimeException {

    public ItunesExcepion(String message) { super(message); }
    public ItunesExcepion(String message, Throwable cause) { super(message, cause); }
    public static final long serialVersionUID = 0;

    public static final class ServerError extends ItunesExcepion {
        public ServerError(String message) { super(message); }
        public ServerError(String message, Throwable cause) { super(message, cause); }
        public static final long serialVersionUID = 0;
    }

    public static final class MalformedReceipt extends ItunesExcepion {
        public MalformedReceipt(String message) { super(message); }
        public MalformedReceipt(String message, Throwable cause) { super(message, cause); }
        public static final long serialVersionUID = 0;
    }
}
