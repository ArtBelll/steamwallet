package ru.steamwallet.swcommon.dao.core;

import lombok.NonNull;
import ru.steamwallet.swcommon.domain.User;

import java.util.List;

/**
 * Created by Artur Belogur on 13.04.17.
 */
public interface UserDao<T extends User> {
    long add(@NonNull T user);

    T get(@NonNull Long id);

    T getByName(@NonNull String name);

    T getByEmail(@NonNull String email);

    void update(@NonNull T user);

    void delete(@NonNull T user);
}
