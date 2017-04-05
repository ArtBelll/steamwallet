package ru.steamwallet.swserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.steamwallet.swcommon.domain.Buyer;
import ru.steamwallet.swcommon.domain.Seller;
import ru.steamwallet.swcommon.exceptions.UnAuthorized;
import ru.steamwallet.swserver.controllers.core.SessionController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Artur Belogur on 31.03.17.
 */
@Slf4j
@Transactional
@RestController
public class UserInfoController extends SessionController {

    @RequestMapping(value = "seller/info", method = RequestMethod.GET)
    public ResponseEntity<?> getSellerInfo(final HttpServletRequest request) {
        final Seller seller = getSessionSeller(request);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @RequestMapping(value = "buyer/info", method = RequestMethod.GET)
    public ResponseEntity<?> getBuyerInfo(final HttpServletRequest request) {
        final Buyer buyer= getSessionBuyer(request);
        return new ResponseEntity<>(buyer, HttpStatus.OK);
    }

    @RequestMapping(value = "user/session", method = RequestMethod.GET)
    public ResponseEntity<?> checkSession(final HttpServletRequest request) {
        try {
            final Seller seller = getSessionSeller(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (UnAuthorized e) {
            log.debug("Not seller session");
        }

        try {
            final Buyer buyer = getSessionBuyer(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (UnAuthorized e) {
            log.debug("Not buyer session");
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
