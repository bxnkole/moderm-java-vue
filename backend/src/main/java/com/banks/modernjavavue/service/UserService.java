package com.banks.modernjavavue.service;

import com.banks.modernjavavue.model.UserEntity;
import com.banks.modernjavavue.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2016-01-18.
 *
 * @author lavenderx
 */
@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    public UserEntity getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void save(UserEntity username) {
        userRepo.save(username);
    }
}
