package ru.steamwallet.swcommon;

/**
 * Created by Artur Belogur on 16.04.17.
 */
public enum StatusBuy {

    NON_STATUS(-1),
    PAYMENT(1),
    SEND_GIFT(2),
    SUCCESSE(3),
    FAIL(4);

    private final int status;

    StatusBuy(int status) {
        this.status = status;

    }

    public int getStatus() {
        return status;
    }
}
