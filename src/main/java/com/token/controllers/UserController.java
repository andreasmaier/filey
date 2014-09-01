package com.token.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> create() {

        return new ResponseEntity<String>("Na das ist nice", HttpStatus.OK);
    }
}
