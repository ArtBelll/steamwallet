package ru.steamwallet.swserver.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.steamwallet.swcommon.dao.core.UserDao;
import ru.steamwallet.swcommon.domain.User;
import ru.steamwallet.swcommon.exceptions.AlreadyRegistered;
import ru.steamwallet.swcommon.exceptions.BadRequest;
import ru.steamwallet.swcommon.exceptions.UserNotExists;
import ru.steamwallet.swcommon.utility.EmailValidator;
import ru.steamwallet.swcommon.utility.PasswordHasher;
import ru.steamwallet.swserver.controllers.core.SessionController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

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

        final UserDao<User> userDao = getUserDaoImpl(role);

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

    @RequestMapping(value = "auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginSeller(final HttpServletRequest request,
                                         @RequestBody final UserRequest userRequest,
                                         @RequestParam final int role) {
        final String ip = getRequestIp(request);

        log.info("Login userRequest procedure from {}:{}", ip, userRequest.getLogin());

        if (StringUtils.isEmpty(userRequest.getEmail()) || StringUtils.isEmpty(userRequest.getPassword())) {
            throw new BadRequest("Missed one or more request fields");
        }

        final UserDao<User> userDao = getUserDaoImpl(role);

        final String email = userRequest.getEmail();
        final User user = userDao.getByEmail(email);
        if(user == null) {
            throw new UserNotExists(email);
        }
        final String password = userRequest.getPassword();
        if (!PasswordHasher.checkPassword(password, user.getPassword())) {
            log.error("No password hash in db");
            throw new BadRequest("Invalid password");
        }

        user.setIpAddress(ip);

        final String jwt = setSessionUser(user);

        userDao.update(user);

        return createdResponse(user, false, jwt, isSslRequest(request));
    }

    @RequestMapping(value = "auth/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logoutSeller(final HttpServletRequest request) {
        final String ip = getRequestIp(request);

        log.info("Logout userRequest procedure from {}", ip);
        getSessionUser(request);

        return closeSession();
    }

    private static class UserRequest implements Serializable {
        @Getter
        private String login;

        @Getter
        private String email;

        @Getter
        private String password;

        @JsonCreator
        public UserRequest(@JsonProperty("login") String login,
                           @JsonProperty("email") String email,
                           @JsonProperty("password") String password) {
            this.login = login;
            this.email = email;
            this.password = password;
        }
    }
}
