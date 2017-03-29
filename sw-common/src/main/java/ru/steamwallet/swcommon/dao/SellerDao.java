package ru.steamwallet.swcommon.dao;

import lombok.NonNull;
import ru.steamwallet.swcommon.domain.Seller;

/**
 * Created by Artur Belogur on 29.03.17.
 */
public interface SellerDao {

    long add(@NonNull Seller seller);

    Seller get(@NonNull Long id);

    Seller get(@NonNull String name);

    void update(@NonNull Seller seller);

    void delete(@NonNull Seller seller);
}
