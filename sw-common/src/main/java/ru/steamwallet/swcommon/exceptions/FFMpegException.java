package ru.steamwallet.swcommon.exceptions;

/**
 * Created by Sergey Ignatov on 12/07/16.
 */
public class FFMpegException extends RuntimeException {
    public FFMpegException(int errorCode) {
        super("ffmpeg execution failed with error: " + Integer.toString(errorCode));
    }
}
