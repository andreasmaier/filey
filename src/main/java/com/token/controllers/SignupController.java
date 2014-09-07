package com.token.controllers;

import com.google.common.collect.Lists;
import com.token.exceptions.ErrorInfo;
import com.token.exceptions.UserAlreadyExistsException;
import com.token.models.Authority;
import com.token.models.User;
import com.token.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {

    private final Logger log = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", produces = "application/json")
    @ResponseBody
    public ResponseEntity registerUser(@RequestParam String username, @RequestParam String password) throws UserAlreadyExistsException {

        User existingUser = userService.loadUserByUsername(username);

        if(null == existingUser){
            User user = new User();
            user.setUsername(username);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setEnabled(true);
            Authority auth = new Authority();
            auth.setAuthority("ROLE_USER");
            auth.setUser(user);
            user.setAuthorities(Lists.newArrayList(auth));

            userService.save(user);

            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            throw new UserAlreadyExistsException(username);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public @ResponseBody ErrorInfo usernameAlreadyExistsHandler(UserAlreadyExistsException exc) {
        log.error("Failed to create user. '" + exc.getUsername() + "' already exists.");

        return new ErrorInfo("username", exc);
    }
}
