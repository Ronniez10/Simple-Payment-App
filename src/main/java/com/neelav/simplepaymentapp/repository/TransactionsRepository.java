package com.neelav.simplepaymentapp.repository;

import com.neelav.simplepaymentapp.model.Accounts;
import com.neelav.simplepaymentapp.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {


    public List<Transactions> findByAccountsId(int id);
}
