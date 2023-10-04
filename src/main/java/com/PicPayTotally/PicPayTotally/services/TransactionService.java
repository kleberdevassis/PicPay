package com.PicPayTotally.PicPayTotally.services;

import com.PicPayTotally.PicPayTotally.DTOs.TransactionDTO;
import com.PicPayTotally.PicPayTotally.domain.transactions.Transaction;
import com.PicPayTotally.PicPayTotally.domain.users.User;
import com.PicPayTotally.PicPayTotally.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {

        User sender = this.userService.findUserById(transactionDTO.senderId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        this.userService.validateUsertransaction(sender, transactionDTO.value());

        boolean isValid = this.validateUserTransaction(sender, transactionDTO.value());

        if(!isValid){
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

        return newTransaction;

    }

    public boolean validateUserTransaction(User user, BigDecimal value) {
        try {
            ResponseEntity<Map> validateTransaction = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

            if (validateTransaction.getStatusCode() == HttpStatus.OK) {
                String message = (String) validateTransaction.getBody().get("message");
                return "Autorizado".equalsIgnoreCase(message);
            } else return false;

        } catch (Exception e) {
            // Em caso de exceção (por exemplo, se a URL não estiver funcionando), retorne true.
            return true;
        }
    }


}
