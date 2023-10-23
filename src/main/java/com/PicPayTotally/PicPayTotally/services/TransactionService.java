package com.PicPayTotally.PicPayTotally.services;

import com.PicPayTotally.PicPayTotally.DTOs.TransactionDTO;
import com.PicPayTotally.PicPayTotally.domain.transactions.Transaction;
import com.PicPayTotally.PicPayTotally.domain.users.User;
import com.PicPayTotally.PicPayTotally.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    UserService userService;

    @Autowired
    AuthorizeService authorizeService;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {

        User sender = this.userService.findUserById(transactionDTO.senderId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        this.userService.validateUsertransaction(sender, transactionDTO.value());

        boolean isValid = this.authorizeService.authorizeTransaction(sender,transactionDTO.value());

        if (!isValid) {
            throw new Exception("transaction canceled contact support");
        }

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        Transaction newTransaction = new Transaction();
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setTimeStamp(LocalDateTime.now());

        userService.saveUser(sender);
        userService.saveUser(receiver);
        transactionRepository.save(newTransaction);

        //this.notificationService.SendNotification(sender,"transaction sent whit success");
        //this.notificationService.SendNotification(receiver,"transaction received whit success");

        return newTransaction;

    }


}
