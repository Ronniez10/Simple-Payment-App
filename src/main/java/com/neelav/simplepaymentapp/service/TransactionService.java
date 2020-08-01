package com.neelav.simplepaymentapp.service;

import com.neelav.simplepaymentapp.model.Accounts;
import com.neelav.simplepaymentapp.model.Transactions;

public interface TransactionService {

    Transactions createTransaction(Transactions transactions);
}
