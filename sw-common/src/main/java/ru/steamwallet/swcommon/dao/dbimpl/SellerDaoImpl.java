package ru.steamwallet.swcommon.dao.dbimpl;

import lombok.NonNull;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ru.steamwallet.swcommon.dao.SellerDao;
import ru.steamwallet.swcommon.domain.Seller;

import java.util.List;

/**
 * Created by Artur Belogur on 29.03.17.
 */
@Repository(value = "sellerDao")
public class SellerDaoImpl extends SessionFactoryHolder implements SellerDao {
    @Override
    public long add(@NonNull Seller seller) {
        super.save(seller);
        return seller.getId();
    }

    @Override
    public Seller get(@NonNull Long id) {
        Query query = getSession().createQuery("from Seller u where u.id=:id");
        query.setParameter("id", id);
        List results = query.list();
        return !results.isEmpty() ? (Seller) results.get(0) : null;
    }

    @Override
    public Seller getByName(@NonNull String name) {
        Query query = getSession().createQuery("from Seller u where u.name=:name");
        query.setParameter("name", name);
        List results = query.list();
        return !results.isEmpty() ? (Seller) results.get(0) : null;
    }

    @Override
    public Seller getByEmail(@NonNull String email) {
        Query query = getSession().createQuery("from Seller u where u.email=:email");
        query.setParameter("email", email);
        List results = query.list();
        return !results.isEmpty() ? (Seller) results.get(0) : null;
    }

    @Override
    public void update(@NonNull Seller seller) {
        super.update(seller);
    }

    @Override
    public void delete(@NonNull Seller seller) {
        super.delete(seller);
    }
}
