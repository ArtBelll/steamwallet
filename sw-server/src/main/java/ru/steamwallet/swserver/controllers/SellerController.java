package ru.steamwallet.swserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.steamwallet.swcommon.dao.SellerDao;
import ru.steamwallet.swcommon.domain.Seller;

import java.util.List;

/**
 * Created by Artur Belogur on 06.04.17.
 */
@Slf4j
@Transactional
@RestController
public class SellerController {

    private SellerDao sellerDao;

    @Autowired
    public SellerController(SellerDao sellerDao) {
        this.sellerDao = sellerDao;
    }

    @RequestMapping(value = "seller/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getSellerById(@PathVariable("id") Long id) {
        Seller seller = sellerDao.get(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @RequestMapping(value = "seller/all", method = RequestMethod.GET)
    private ResponseEntity<?> getAllSellers() {
        List<Seller> sellers = sellerDao.getAll();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }
}
