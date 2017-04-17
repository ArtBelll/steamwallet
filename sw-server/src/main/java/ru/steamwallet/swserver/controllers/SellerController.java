package ru.steamwallet.swserver.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.steamwallet.swcommon.dao.SellerDao;
import ru.steamwallet.swcommon.domain.Seller;
import ru.steamwallet.swserver.controllers.core.SessionController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Artur Belogur on 06.04.17.
 */
@Slf4j
@Transactional
@RestController
public class SellerController extends SessionController {

    @Autowired
    public SellerController(SellerDao sellerDao) {
        this.sellerDao = sellerDao;
    }

    @RequestMapping(value = "seller/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getSellerById(@PathVariable("id") Long id) {
        Seller seller = sellerDao.get(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @RequestMapping(value = "seller/update/add-info", method = RequestMethod.POST)
    private ResponseEntity<?> updateAddInfo(HttpServletRequest request,
                                           @RequestBody RequestAddInfo addInfo) {
        Seller seller = (Seller) getSessionUser(request);
        seller.setComfortTime(addInfo.getComfortTime());
        seller.setDescription(addInfo.getDescription());
        sellerDao.update(seller);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "seller/all", method = RequestMethod.GET)
    private ResponseEntity<?> getAllSellers() {
        List<Seller> sellers = sellerDao.getAll();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    private static class RequestAddInfo implements Serializable {
        @Getter String comfortTime;
        @Getter String description;

        @JsonCreator
        RequestAddInfo(@JsonProperty("comfortTime") String comfortTime,
                       @JsonProperty("description") String description) {
            this.comfortTime = comfortTime;
            this.description = description;
        }
    }
}
