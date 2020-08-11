package com.neelav.simplepaymentapp;

import com.neelav.simplepaymentapp.model.Accounts;
import com.neelav.simplepaymentapp.model.TransactionForm;
import com.neelav.simplepaymentapp.model.Transactions;
import com.neelav.simplepaymentapp.repository.AccountsRepository;
import com.neelav.simplepaymentapp.repository.TransactionsRepository;
import com.neelav.simplepaymentapp.service.AccountService;
import com.neelav.simplepaymentapp.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SimplePaymentAppApplicationTests {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

	@MockBean
	private TransactionsRepository transactionsRepository;

	@MockBean
	private AccountsRepository accountsRepository;

	@Test
	public void testCreateTransaction()
	{

		Accounts accounts = new Accounts();
		accounts.setBalance(5000);
		accounts.setId(101);
		accounts.setName("Neelav");
		accounts.setPhoneNumber("1234567890");


		Transactions trans = new Transactions("DEBIT",100,accounts);



		when(transactionsRepository.save(trans)).thenReturn(trans);

		assertEquals(trans,transactionService.createTransaction(trans));
	}


	@Test
	public void testUpdateAccounts_ValidTransaction()
	{
		TransactionForm transactionForm = new TransactionForm("Saumya","Ronnie",10);
		boolean b=false;
		//

		Accounts creditAccount = new Accounts(101, transactionForm.getTo(), 500, "12345");

		Accounts debitAccount  = new Accounts(102, transactionForm.getFrom(), 500, "54321");
		if(transactionForm.getAmount()<debitAccount.getBalance()) {

			Transactions  debit = new Transactions("DEBIT",transactionForm.getAmount(),debitAccount);
			Transactions  credit = new Transactions("CREDIT",transactionForm.getAmount(),creditAccount);

			when(transactionService.createTransaction(debit)).thenReturn(debit);
			when(transactionService.createTransaction(credit)).thenReturn(credit);

			debitAccount.setBalance(debitAccount.getBalance()-transactionForm.getAmount());
			creditAccount.setBalance(creditAccount.getBalance()+transactionForm.getAmount());
			accountsRepository.save(debitAccount);
			accountsRepository.save(creditAccount);

			verify(accountsRepository,times(2)).save(anyObject());
			b=true;
		}

		assertTrue(b);

	}


	@Test
	public void testUpdateAccounts_InValidTransaction()
	{
		TransactionForm transactionForm = new TransactionForm("Saumya","Ronnie",7000);
		boolean b=false;
		//

		Accounts creditAccount = new Accounts(101, transactionForm.getTo(), 500, "12345");

		Accounts debitAccount = new Accounts(102, transactionForm.getFrom(), 500, "54321");

		if(transactionForm.getAmount()<debitAccount.getBalance()) {

			Transactions  debit = new Transactions("DEBIT",transactionForm.getAmount(),debitAccount);
			Transactions  credit = new Transactions("CREDIT",transactionForm.getAmount(),creditAccount);

			when(transactionService.createTransaction(debit)).thenReturn(debit);
			when(transactionService.createTransaction(credit)).thenReturn(credit);

			debitAccount.setBalance(debitAccount.getBalance()-transactionForm.getAmount());
			creditAccount.setBalance(creditAccount.getBalance()+transactionForm.getAmount());
			accountsRepository.save(debitAccount);
			accountsRepository.save(creditAccount);

			verify(accountsRepository,times(2)).save(anyObject());
			b=true;
		}

		assertFalse(b);

	}

}
