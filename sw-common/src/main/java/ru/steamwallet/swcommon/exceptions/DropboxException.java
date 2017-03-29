package ru.steamwallet.swcommon.exceptions;

/**
 * Created by Sergey Ignatov on 14/11/16.
 */
public class DropboxException extends UploadException {
    public DropboxException(Exception ex) {
        super(ex);
    }
}
