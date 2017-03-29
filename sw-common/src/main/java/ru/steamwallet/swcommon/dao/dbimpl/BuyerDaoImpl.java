package ru.steamwallet.swcommon.dao.dbimpl;

import lombok.NonNull;
import org.hibernate.Query;
import ru.steamwallet.swcommon.dao.BuyerDao;
import ru.steamwallet.swcommon.domain.Buyer;

import java.util.List;

/**
 * Created by Artur Belogur on 29.03.17.
 */
public class BuyerDaoImpl extends SessionFactoryHolder implements BuyerDao {

    @Override
    public long add(@NonNull Buyer buyer) {
        super.save(buyer);
        return buyer.getId();
    }

    @Override
    public Buyer get(@NonNull Long id) {
        Query query = getSession().createQuery("from Buyer u where u.id=:id");
        query.setParameter("id", id);
        List results = query.list();
        return !results.isEmpty() ? (Buyer) results.get(0) : null;
    }

    @Override
    public Buyer get(@NonNull String name) {
        Query query = getSession().createQuery("from Buyer u where u.name=:name");
        query.setParameter("name", name);
        List results = query.list();
        return !results.isEmpty() ? (Buyer) results.get(0) : null;
    }

    @Override
    public void update(@NonNull Buyer buyer) {
        super.update(buyer);
    }

    @Override
    public void delete(@NonNull Buyer buyer) {
        super.delete(buyer);
    }
}
