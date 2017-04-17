package ru.steamwallet.swserver.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.steamwallet.swcommon.dao.PurchaseDao;
import ru.steamwallet.swcommon.domain.Buyer;
import ru.steamwallet.swcommon.domain.Purchase;
import ru.steamwallet.swcommon.domain.Seller;
import ru.steamwallet.swcommon.exceptions.BadRequest;
import ru.steamwallet.swcommon.exceptions.UserNotExists;
import ru.steamwallet.swserver.controllers.core.SessionController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by Artur Belogur on 16.04.17.
 */
@Slf4j
@Transactional
@RestController
public class PurchaseController extends SessionController {

    private PurchaseDao purchaseDao;

    @Autowired
    public PurchaseController(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    @RequestMapping(value = "purchase/new", method = RequestMethod.POST)
    public ResponseEntity<?> createPurchase(final HttpServletRequest request,
                                            @RequestBody PurchaseRequest purchaseRequest) {

        if (purchaseRequest.getSellerId() == null
                || StringUtils.isEmpty(purchaseRequest.getUrl())
                || purchaseRequest.getSum() == null) {
            throw new BadRequest("Missed one or more request fields");
        }

        Buyer buyer = (Buyer) getSessionUser(request);
        Seller seller = sellerDao.get(purchaseRequest.getSellerId());
        if (seller == null) {
            throw new UserNotExists(purchaseRequest.getSellerId().toString());
        }
        Purchase purchase =
                new Purchase(purchaseRequest.getSum(), purchaseRequest.getGame(), purchaseRequest.getUrl(), seller, buyer);
        purchaseDao.add(purchase);

        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    private static class PurchaseRequest implements Serializable {
        @Getter Long sellerId;
        @Getter Integer sum;
        @Getter String game;
        @Getter String url;

        @JsonCreator
        PurchaseRequest(@JsonProperty("sellerId") Long sellerId,
                        @JsonProperty("sum") Integer sum,
                        @JsonProperty("game") String game,
                        @JsonProperty("url") String url) {
            this.sellerId = sellerId;
            this.sum = sum;
            this.game = game;
            this.url = url;
        }
    }
}
