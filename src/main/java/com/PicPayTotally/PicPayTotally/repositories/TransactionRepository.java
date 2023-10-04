package com.PicPayTotally.PicPayTotally.repositories;

import com.PicPayTotally.PicPayTotally.domain.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
