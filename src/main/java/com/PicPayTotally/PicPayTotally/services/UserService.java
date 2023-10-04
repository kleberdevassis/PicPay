package com.PicPayTotally.PicPayTotally.services;

import com.PicPayTotally.PicPayTotally.DTOs.UserDTO;
import com.PicPayTotally.PicPayTotally.domain.users.User;
import com.PicPayTotally.PicPayTotally.domain.users.UserType;
import com.PicPayTotally.PicPayTotally.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public void validateUsertransaction(User user, BigDecimal value) throws Exception {

        if (user.getUserType() == UserType.MERCHANT) {
            throw new Exception("Merchant not authorized");
        }

        if (user.getBalance().compareTo(value) < 0) {
            throw new Exception("balance insufficient");
        }

    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User findUserById(Long id) throws Exception {
      return this.userRepository.findUserById(id).orElseThrow(()-> new Exception("User not Found"));
    }

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        this.userRepository.save(user);
        return user;
    }

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }


}
