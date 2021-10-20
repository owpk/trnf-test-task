package org.owpk.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owpk.entities.dto.UserDto;
import org.owpk.services.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * Mock auth controller which just
 * save user from ui
 */
@RestController
@RequestMapping("/rest/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping
    public Long auth(@RequestBody UserDto userDto) {
        if (userDto.getName() == null)
            userDto.setName("User");
        if (userDto.getEmail() == null)
            userDto.setName("voyzvz@gmail.com");
        var user = userService.save(userDto);
        log.info("USER LOGGED: {}", user);
        return user.getId();
    }
}
