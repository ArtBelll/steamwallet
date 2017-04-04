package ru.steamwallet.swserver.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.steamwallet.swcommon.domain.Seller;
import ru.steamwallet.swcommon.exceptions.AlreadyRegistered;
import ru.steamwallet.swcommon.exceptions.BadRequest;
import ru.steamwallet.swcommon.exceptions.UserNotExists;
import ru.steamwallet.swcommon.utility.EmailValidator;
import ru.steamwallet.swcommon.utility.PasswordHasher;
import ru.steamwallet.swserver.controllers.core.SessionController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Artur Belogur on 29.03.17.
 */
@Slf4j
@Transactional
@RestController
public class SellerAuthController extends SessionController {

    @RequestMapping(value = "auth/seller/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerSeller(final HttpServletRequest request,
                                            @RequestBody final SellerRequest sellerRequest) {
        final String ip = getRequestIp(request);

        log.info("Seller register procedure from {}", ip);

        final String email = sellerRequest.getEmail();
        final EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(email)) {
            log.error("Registration with invalid email: {}", email);
            throw new BadRequest("Invalid email " + email);
        }

        if(sellerDao.getByEmail(email) != null) {
            log.error("Email registration with already used email: {}", email);
            throw new AlreadyRegistered("Email " + email + " is already used in system");
        }

        final String name = sellerRequest.getLogin();
        if(sellerDao.getByName(name) != null) {
            log.error("Email registration with already used login: {}", name);
            throw new AlreadyRegistered("Name " + name + " is already used in system");
        }

        final String password = PasswordHasher.hashPassword(sellerRequest.getPassword());
        final Seller seller = new Seller(name, email, password);

        seller.setIpAddress(getRequestIp(request));
        sellerDao.add(seller);

        final String jwt = setSessionSeller(seller);

        return createdResponse(seller, true, jwt, isSslRequest(request));
    }

    @RequestMapping(value = "auth/seller/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginSeller(final HttpServletRequest request,
                                         @RequestBody final SellerRequest sellerRequest) {
        final String ip = getRequestIp(request);

        log.info("Login sellerRequest procedure from {}:{}", ip, sellerRequest.getLogin());

        if (StringUtils.isEmpty(sellerRequest.getEmail()) || StringUtils.isEmpty(sellerRequest.getPassword())) {
            throw new BadRequest("Missed one or more request fields");
        }

        final String email = sellerRequest.getEmail();
        final Seller seller = sellerDao.getByEmail(email);
        if(seller == null) {
            throw new UserNotExists(email);
        }
        final String password = sellerRequest.getPassword();
        if (!PasswordHasher.checkPassword(password, seller.getPassword())) {
            log.error("No password hash in db");
            throw new BadRequest("Invalid password");
        }

        seller.setIpAddress(ip);
        seller.setLastAction(new Date());

        final String jwt = setSessionSeller(seller);

        sellerDao.update(seller);

        return createdResponse(seller, false, jwt, isSslRequest(request));
    }

    @RequestMapping(value = "auth/seller/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logoutSeller(final HttpServletRequest request) {
        final String ip = getRequestIp(request);

        log.info("Logout sellerRequest procedure from {}", ip);
        final Seller seller = getSessionSeller(request);

        return closeSession();
    }

    static class SellerRequest implements Serializable {
        @Getter
        private String login;

        @Getter
        private String email;

        @Getter
        private String password;

        @JsonCreator
        public SellerRequest(@JsonProperty("name") String login,
                             @JsonProperty("email") String email,
                             @JsonProperty("password") String password) {
            this.login = login;
            this.email = email;
            this.password = password;
        }
    }
}
