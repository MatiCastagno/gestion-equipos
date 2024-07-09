package org.example.equipos.service.impl;

import org.example.equipos.model.Users;
import org.example.equipos.repository.UsersRepository;
import org.example.equipos.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public Users save(Users user) {
        return userRepository.save(user);
    }

}
