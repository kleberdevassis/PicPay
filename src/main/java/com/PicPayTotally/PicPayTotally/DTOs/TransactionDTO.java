package com.PicPayTotally.PicPayTotally.DTOs;

import java.math.BigDecimal;

public record TransactionDTO(Long senderId, Long receiverId, BigDecimal value) {
}
