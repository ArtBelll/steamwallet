package ru.steamwallet.swcommon.dao;

import lombok.NonNull;
import ru.steamwallet.swcommon.domain.Buyer;

/**
 * Created by Artur Belogur on 29.03.17.
 */
public interface BuyerDao {

    long add(@NonNull Buyer buyer);

    Buyer get(@NonNull Long id);

    Buyer get(@NonNull String name);

    void update(@NonNull Buyer buyer);

    void delete(@NonNull Buyer buyer);
}
