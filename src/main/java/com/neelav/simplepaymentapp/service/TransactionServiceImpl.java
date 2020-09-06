package com.neelav.simplepaymentapp.service;

import com.neelav.simplepaymentapp.model.Accounts;
import com.neelav.simplepaymentapp.model.Transactions;
import com.neelav.simplepaymentapp.repository.TransactionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    private static Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionsRepository transactionsRepository;

    public Transactions createTransaction(Transactions transactions)
    {
        log.info("Amount="+transactions.getAmount());
        log.info("Transaction Type= "+transactions.getType());
        log.info("Account="+transactions.getAccounts());
        return  transactionsRepository.save(transactions);
    }
}
