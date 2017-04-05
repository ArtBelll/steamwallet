package ru.steamwallet.swserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.steamwallet.swcommon.domain.Buyer;
import ru.steamwallet.swcommon.exceptions.AlreadyRegistered;
import ru.steamwallet.swcommon.exceptions.BadRequest;
import ru.steamwallet.swcommon.exceptions.UserNotExists;
import ru.steamwallet.swcommon.utility.EmailValidator;
import ru.steamwallet.swcommon.utility.PasswordHasher;
import ru.steamwallet.swserver.controllers.core.SessionController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Artur Belogur on 05.04.17.
 */
@Slf4j
@Transactional
@RestController
public class BuyerAuthController extends SessionController {

    @RequestMapping(value = "auth/buyer/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerBuyer(final HttpServletRequest request,
                                            @RequestBody final UserRequest userRequest) {
        final String ip = getRequestIp(request);

        log.info("Buyer register procedure from {}", ip);

        final String email = userRequest.getEmail();
        final EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(email)) {
            log.error("Registration with invalid email: {}", email);
            throw new BadRequest("Invalid email " + email);
        }

        if(buyerDao.getByEmail(email) != null) {
            log.error("Email registration with already used email: {}", email);
            throw new AlreadyRegistered("Email " + email + " is already used in system");
        }

        final String name = userRequest.getLogin();
        if(buyerDao.getByName(name) != null) {
            log.error("Email registration with already used login: {}", name);
            throw new AlreadyRegistered("Name " + name + " is already used in system");
        }

        final String password = PasswordHasher.hashPassword(userRequest.getPassword());
        final Buyer buyer = new Buyer(name, email, password);

        buyer.setIpAddress(getRequestIp(request));
        buyerDao.add(buyer);

        final String jwt = setSessionBuyer(buyer);

        return createdResponse(buyer, true, jwt, isSslRequest(request));
    }

    @RequestMapping(value = "auth/buyer/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginSeller(final HttpServletRequest request,
                                         @RequestBody final UserRequest userRequest) {
        final String ip = getRequestIp(request);

        log.info("Login userRequest procedure from {}:{}", ip, userRequest.getLogin());

        if (StringUtils.isEmpty(userRequest.getEmail()) || StringUtils.isEmpty(userRequest.getPassword())) {
            throw new BadRequest("Missed one or more request fields");
        }

        final String email = userRequest.getEmail();
        final Buyer buyer = buyerDao.getByEmail(email);
        if(buyer == null) {
            throw new UserNotExists(email);
        }
        final String password = userRequest.getPassword();
        if (!PasswordHasher.checkPassword(password, buyer.getPassword())) {
            log.error("No password hash in db");
            throw new BadRequest("Invalid password");
        }

        buyer.setIpAddress(ip);

        final String jwt = setSessionBuyer(buyer);

        buyerDao.update(buyer);

        return createdResponse(buyer, false, jwt, isSslRequest(request));
    }

    @RequestMapping(value = "auth/buyer/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logoutSeller(final HttpServletRequest request) {
        final String ip = getRequestIp(request);

        log.info("Logout userRequest procedure from {}", ip);
        getSessionBuyer(request);

        return closeSession();
    }

}
