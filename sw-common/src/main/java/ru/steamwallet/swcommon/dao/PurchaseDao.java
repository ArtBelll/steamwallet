package ru.steamwallet.swcommon.dao;

import lombok.NonNull;
import ru.steamwallet.swcommon.domain.Purchase;

/**
 * Created by Artur Belogur on 29.03.17.
 */
public interface PurchaseDao {

    long add(@NonNull Purchase purchase);

    Purchase get(@NonNull Long id);

    void update(@NonNull Purchase purchase);

    void delete(@NonNull Purchase purchase);
}
