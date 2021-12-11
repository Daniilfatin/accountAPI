package com.example.accountAPI.service;

import com.example.accountAPI.entity.UserEntity;
import com.example.accountAPI.exception.UserAlreadyExistException;
import com.example.accountAPI.exception.UserNotFoundException;
import com.example.accountAPI.model.User;
import com.example.accountAPI.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if(userRepo.findByUsername(user.getUsername()) != null) {
            throw  new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null){
            throw new UserNotFoundException("Пользователь не найден");
        }
        return User.toModel(user);
    }

    public String delete(Long id){
        userRepo.deleteById(id);
        return "Пользователь номер "+ id +" удален";
    }
}
