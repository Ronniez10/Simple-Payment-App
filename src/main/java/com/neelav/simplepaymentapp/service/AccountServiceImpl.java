package com.neelav.simplepaymentapp.service;

import com.neelav.simplepaymentapp.model.Accounts;
import com.neelav.simplepaymentapp.model.SmsRequest;
import com.neelav.simplepaymentapp.model.TransactionForm;
import com.neelav.simplepaymentapp.model.Transactions;
import com.neelav.simplepaymentapp.repository.AccountsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private static Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountsRepository accountsRepository;

    public boolean updateAccounts(TransactionForm transactionForm)
    {
        double amt = transactionForm.getAmount();

        SmsRequest smsRequest=null;

        boolean b=false;

        Optional<Accounts> fromAC = accountsRepository.findByName(transactionForm.getFrom());
        Optional<Accounts> toAC = accountsRepository.findByName(transactionForm.getTo());

        if(fromAC.isPresent() && toAC.isPresent())
        {
            Accounts from = fromAC.get();
            Accounts to = toAC.get();
            if(amt < from.getBalance())
            {
                Transactions credit = new Transactions("CREDIT",amt,to);
                Transactions debit = new Transactions("DEBIT",amt,from);
                transactionService.createTransaction(credit);
                transactionService.createTransaction(debit);

                from.setBalance(from.getBalance()-amt);
                log.info("From Account Balance="+from.getBalance());
                to.setBalance(to.getBalance()+amt);
                log.info("To Account Balance="+to.getBalance());

                accountsRepository.save(from);
                accountsRepository.save(to);

                b=true;
            }
            else
                b=false;
        }

        return b;
    }

}
