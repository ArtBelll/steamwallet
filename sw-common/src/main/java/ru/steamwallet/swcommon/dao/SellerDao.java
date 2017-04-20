package ru.steamwallet.swcommon.dao;

import lombok.NonNull;
import ru.steamwallet.swcommon.dao.core.UserDao;
import ru.steamwallet.swcommon.domain.Seller;

import java.util.List;

/**
 * Created by Artur Belogur on 29.03.17.
 */
public interface SellerDao extends UserDao<Seller> {
    List<Seller> getAll();
}
