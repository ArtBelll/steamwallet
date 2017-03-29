package ru.steamwallet.swcommon.dao.dbimpl;

import lombok.NonNull;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ru.steamwallet.swcommon.dao.PurchaseDao;
import ru.steamwallet.swcommon.domain.Purchase;

import java.util.List;

/**
 * Created by Artur Belogur on 29.03.17.
 */
@Repository(value = "purchaseDao")
public class PurchaseDaoImpl extends SessionFactoryHolder implements PurchaseDao {
    @Override
    public long add(@NonNull Purchase purchase) {
        super.save(purchase);
        return purchase.getId();
    }

    @Override
    public Purchase get(@NonNull Long id) {
        Query query = getSession().createQuery("from Purchase u where u.id=:id");
        query.setParameter("id", id);
        List results = query.list();
        return !results.isEmpty() ? (Purchase) results.get(0) : null;
    }

    @Override
    public void update(@NonNull Purchase purchase) {
        super.update(purchase);
    }

    @Override
    public void delete(@NonNull Purchase purchase) {
        super.delete(purchase);
    }
}
