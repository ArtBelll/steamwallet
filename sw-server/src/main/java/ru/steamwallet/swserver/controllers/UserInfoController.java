package ru.steamwallet.swserver.controllers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.steamwallet.swcommon.domain.Buyer;
import ru.steamwallet.swcommon.domain.Seller;
import ru.steamwallet.swcommon.domain.User;
import ru.steamwallet.swserver.controllers.core.SessionController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Artur Belogur on 31.03.17.
 */
@Slf4j
@Transactional
@RestController
public class UserInfoController extends SessionController {

    @RequestMapping(value = "user/info", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(final HttpServletRequest request) {
        final User user = getSessionUser(request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "user/role", method = RequestMethod.GET)
    public ResponseEntity<?> getUserRole(final HttpServletRequest request) {
        final User user = getSessionUser(request);
        ResponseRole role = new ResponseRole();
        if (user instanceof Seller) role.role = 0;
        if (user instanceof Buyer) role.role = 1;
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    private class ResponseRole {
        @Getter int role;
    }
}
