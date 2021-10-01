package org.owpk.controllers;

import lombok.RequiredArgsConstructor;
import org.owpk.entities.dto.UserDto;
import org.owpk.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mock auth controller which just
 * return default test user from database
 */
@RestController
@RequestMapping("/rest/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping
    public UserDto auth() {
        return userService.getUser();
    }
}
