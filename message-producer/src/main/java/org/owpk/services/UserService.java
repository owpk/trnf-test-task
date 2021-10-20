package org.owpk.services;

import lombok.RequiredArgsConstructor;
import org.owpk.entities.User;
import org.owpk.entities.dto.UserDto;
import org.owpk.exception.ResourceNotFoundException;
import org.owpk.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("item with id: " + id));
    }

    public User save(UserDto userDto) {
        var u = new User();
        u.setEmail(userDto.getEmail());
        u.setName(userDto.getName());
        return userRepo.save(u);
    }
}
