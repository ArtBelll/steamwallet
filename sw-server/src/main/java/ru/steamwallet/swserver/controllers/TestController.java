package ru.steamwallet.swserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Artur Belogur on 28.03.17.
 */
@RestController
public class TestController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ResponseEntity<?> testServer() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }
}
