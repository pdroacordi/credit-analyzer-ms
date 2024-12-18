package org.acordi.microsservices.service;

import org.acordi.microsservices.entity.User;
import org.acordi.microsservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByCPF(String cpf){
        return userRepository.findByCpf(cpf);
    }

}
