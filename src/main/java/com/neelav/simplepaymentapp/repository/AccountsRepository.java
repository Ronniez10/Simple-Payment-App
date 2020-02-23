package com.neelav.simplepaymentapp.repository;

import com.neelav.simplepaymentapp.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Integer> {

    Optional<Accounts> findByName(String name);
}
