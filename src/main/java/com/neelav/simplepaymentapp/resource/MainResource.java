package com.neelav.simplepaymentapp.resource;

import com.neelav.simplepaymentapp.model.Accounts;
import com.neelav.simplepaymentapp.model.SmsRequest;
import com.neelav.simplepaymentapp.model.TransactionForm;
import com.neelav.simplepaymentapp.model.Transactions;
import com.neelav.simplepaymentapp.repository.AccountsRepository;
import com.neelav.simplepaymentapp.repository.TransactionsRepository;
import com.neelav.simplepaymentapp.service.AccountService;
import com.neelav.simplepaymentapp.service.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class MainResource {

    private static Logger log = LoggerFactory.getLogger(MainResource.class);

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/v1")
    public String getTransactionForUser(Model model)
    {

        List<Accounts> accounts = accountsRepository.findAll();

        log.info("List Size="+accounts.size());
        model.addAttribute("accounts",accounts);

        return "hello";
    }

    @GetMapping("/v1/customer/viewTransactions")
    public String showTransactions(@RequestParam("accountId") int id, HttpServletRequest request,Model model)
    {
        //String id = request.getParameter("accountId");
        log.info("Customer Id:"+id);

        List<Transactions> transactions = transactionsRepository.findByAccountsId(id);

        model.addAttribute("transactions",transactions);

        return "transactions";
    }

    @GetMapping("/v1/customer/doTransaction")
    public String doTransaction(@RequestParam("accountId") int id,Model model)
    {
        Accounts accounts;
        TransactionForm transactionForm=null;
        Optional<Accounts> ac = accountsRepository.findById(id);

        if(ac.isPresent()) {
            accounts = ac.get();

             transactionForm = new TransactionForm();
            transactionForm.setFrom(accounts.getName());
        }

        model.addAttribute("transactionForm",transactionForm);

        return "transaction-form";
    }

    @PostMapping("/v1/customer/doTransaction")
    public String performTransaction(@ModelAttribute("transactionForm") TransactionForm transactionForm)
    {
        log.info("From ="+transactionForm.getFrom());
        log.info("To ="+transactionForm.getTo());

        boolean b=accountService.updateAccounts(transactionForm);

        if(b) {
            sendSmsViaTwilio(transactionForm);

            return "redirect:/api/v1?success";
        }
        else {
            return "redirect:/api/v1?failed";
        }
    }

    private void sendSmsViaTwilio(TransactionForm transactionForm) {


            String debit = "Your Account has been debited by $" + transactionForm.getAmount() + " for a Transaction made on the Simple " +
                    "Payment App";

            String credit = "Your Account has been credited by $" + transactionForm.getAmount() + " for a Transaction made on the Simple " +
                    "Payment App";


            String debitNumber = accountsRepository.findByName(transactionForm.getFrom()).get().getPhoneNumber();
            String creditNumber = accountsRepository.findByName(transactionForm.getTo()).get().getPhoneNumber();

            SmsRequest debited = new SmsRequest(debitNumber, debit);
            SmsRequest credited = new SmsRequest(creditNumber, credit);

            restTemplate.postForObject("http://localhost:8080/api/v1/sms", debited, SmsRequest.class);

            restTemplate.postForObject("http://localhost:8080/api/v1/sms", credited, SmsRequest.class);


    }
}
