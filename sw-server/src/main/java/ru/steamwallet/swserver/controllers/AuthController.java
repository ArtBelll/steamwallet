package ru.steamwallet.swserver.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.steamwallet.swcommon.dao.core.UserDao;
import ru.steamwallet.swcommon.domain.Seller;
import ru.steamwallet.swcommon.domain.User;
import ru.steamwallet.swcommon.exceptions.AlreadyRegistered;
import ru.steamwallet.swcommon.exceptions.BadRequest;
import ru.steamwallet.swcommon.utility.EmailValidator;
import ru.steamwallet.swcommon.utility.PasswordHasher;
import ru.steamwallet.swserver.controllers.core.SessionController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Artur Belogur on 13.04.17.
 */
@Slf4j
@Transactional
@RestController
public class AuthController extends SessionController {

    @RequestMapping(value = "auth/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerSeller(final HttpServletRequest request,
                                            @RequestBody final UserRequest userRequest,
                                            @RequestParam final int role) {
        final String ip = getRequestIp(request);

        log.info("User register procedure from {}", ip);

        final String email = userRequest.getEmail();
        final EmailValidator emailValidator = new EmailValidator();
        if (!emailValidator.validate(email)) {
            log.error("Registration with invalid email: {}", email);
            throw new BadRequest("Invalid email " + email);
        }

        final UserDao userDao = getUserDaoImpl(role);

        if(userDao.getByEmail(email) != null) {
            log.error("Email registration with already used email: {}", email);
            throw new AlreadyRegistered("Email " + email + " is already used in system");
        }

        final String login = userRequest.getLogin();
        if(userDao.getByName(login) != null) {
            log.error("Email registration with already used login: {}", login);
            throw new AlreadyRegistered("Name " + login + " is already used in system");
        }

        final String password = PasswordHasher.hashPassword(userRequest.getPassword());
        final User user = getUser(role, login, email, password);

        user.setIpAddress(getRequestIp(request));
        userDao.add(user);

        final String jwt = setSessionUser(user);

        return createdResponse(user, true, jwt, isSslRequest(request));
    }
}
