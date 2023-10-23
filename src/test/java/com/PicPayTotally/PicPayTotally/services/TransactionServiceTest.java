package com.PicPayTotally.PicPayTotally.services;

import com.PicPayTotally.PicPayTotally.DTOs.TransactionDTO;
import com.PicPayTotally.PicPayTotally.domain.users.User;
import com.PicPayTotally.PicPayTotally.domain.users.UserType;
import com.PicPayTotally.PicPayTotally.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    UserService userService;
    @Mock
    AuthorizeService authorizeService;

    @Mock
    NotificationService notificationService;

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("when the transaction is successfully")
    void createTransaction() throws Exception {

        User sender = new User(1L, "kleber", "margarido", "32323445565",
                "passou@gmail.com", "kl33", new BigDecimal(10), UserType.COMMON);
        User receiver = new User(2L, "Robert", "mezenga", "33244232425",
                "onde@gmail", "hgf3334", new BigDecimal(10), UserType.COMMON);

        when(this.userService.findUserById(1L)).thenReturn(sender);
        when(this.userService.findUserById(2L)).thenReturn(receiver);

        when(this.authorizeService.authorizeTransaction(any(),any())).thenReturn(true);

        TransactionDTO request = new TransactionDTO(1L,2L,new BigDecimal(10));
        this.transactionService.createTransaction(request);

        verify(this.transactionRepository,times(1)).save(any());

        sender.setBalance(new BigDecimal(0));
        verify(this.userService,times(1)).saveUser(sender);

        receiver.setBalance((new BigDecimal(20)));
        verify(this.userService,times(1)).saveUser(receiver);

        //verify(this.notificationService,times(1)).SendNotification(sender,"transaction done successfully");
       // verify(this.notificationService,times(1)).SendNotification(receiver,"transaction received successfully");

    }
}