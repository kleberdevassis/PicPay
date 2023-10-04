package com.PicPayTotally.PicPayTotally.controllers;

import com.PicPayTotally.PicPayTotally.DTOs.TransactionDTO;
import com.PicPayTotally.PicPayTotally.DTOs.UserDTO;
import com.PicPayTotally.PicPayTotally.domain.transactions.Transaction;
import com.PicPayTotally.PicPayTotally.domain.users.User;
import com.PicPayTotally.PicPayTotally.services.TransactionService;
import com.PicPayTotally.PicPayTotally.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class ControllerTransactions {
    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){
        User user = userService.createUser(userDTO);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<User>> findUsers(){
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(transaction,HttpStatus.CREATED);
    }
}
