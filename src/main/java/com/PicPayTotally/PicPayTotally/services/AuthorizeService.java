package com.PicPayTotally.PicPayTotally.services;

import com.PicPayTotally.PicPayTotally.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AuthorizeService {

    @Autowired
    RestTemplate restTemplate;

    public boolean authorizeTransaction(User user, BigDecimal value) {
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
