package ru.steamwallet.swserver.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.steamwallet.swcommon.domain.Seller;
import ru.steamwallet.swcommon.exceptions.AlreadyRegistered;
import ru.steamwallet.swcommon.exceptions.BadRequest;
import ru.steamwallet.swcommon.utility.EmailValidator;
import ru.steamwallet.swserver.controllers.core.SessionController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Artur Belogur on 29.03.17.
 */
@Slf4j
@RestController
public class SellerController extends SessionController {

    @Transactional
    @RequestMapping(value = "sellers/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerSeller(HttpServletRequest request,
                                       @RequestBody Seller seller) {
        final String ip = getRequestIp(request);

        log.info("Seller register procedure from {}", ip);

        String email = seller.getEmail();
        final EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(email)) {
            log.error("Registration with invalid email: {}", email);
            throw new BadRequest("Invalid email " + email);
        }

        if(sellerDao.getByEmail(email) != null) {
            log.error("Email registration with already used email: {}", email);
            throw new AlreadyRegistered("Email " + email + " is already used in system");
        }

        if(sellerDao.getByName(seller.getName()) != null) {
            log.error("Email registration with already used name: {}", seller.getName());
            throw new AlreadyRegistered("Name " + seller.getName() + " is already used in system");
        }

        seller.setIpAddress(getRequestIp(request));
        sellerDao.add(seller);

        return new ResponseEntity<>(seller, HttpStatus.CREATED);
    }
}
