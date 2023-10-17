package com.PicPayTotally.PicPayTotally.services;

import com.PicPayTotally.PicPayTotally.DTOs.NotificatonDTO;
import com.PicPayTotally.PicPayTotally.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    RestTemplate restTemplate;

    public void SendNotification(User user, String message) throws Exception {

        String email = user.getEmail();

        NotificatonDTO notificatonDTO = new NotificatonDTO(email, message);

     //  ResponseEntity<String> responseEmail = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify",notificatonDTO,String.class);

      // if(!(responseEmail.getStatusCode() == HttpStatus.OK)){
         //  throw new Exception("Email not sent");
       //}

    }
}
