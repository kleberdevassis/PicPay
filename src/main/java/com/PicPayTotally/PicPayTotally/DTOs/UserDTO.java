package com.PicPayTotally.PicPayTotally.DTOs;

import com.PicPayTotally.PicPayTotally.domain.users.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, String email, String password, BigDecimal balance, UserType userType) {
}
