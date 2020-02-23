package com.neelav.simplepaymentapp.service;

import com.neelav.simplepaymentapp.model.Accounts;

public interface TransactionService {

    void createTransaction(double amount, String transactionType, Accounts accounts);
}
