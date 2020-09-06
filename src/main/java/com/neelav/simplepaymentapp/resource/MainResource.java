package com.neelav.simplepaymentapp.resource;

import com.neelav.simplepaymentapp.model.Accounts;
import com.neelav.simplepaymentapp.model.SmsRequest;
import com.neelav.simplepaymentapp.model.TransactionForm;
import com.neelav.simplepaymentapp.model.Transactions;
import com.neelav.simplepaymentapp.repository.AccountsRepository;
import com.neelav.simplepaymentapp.repository.TransactionsRepository;
import com.neelav.simplepaymentapp.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
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


    //Redirecting the User to Homepage
    @GetMapping("/")
    public String defaultHome()
    {
        return "redirect:/api/bank";
    }


    //This the Homepage Controller Method
    @GetMapping("/api/bank")
    public String getTransactionForUser(Model model, Principal principal)
    {
        log.info("Current Logged in Username: "+principal.getName());
        List<Accounts> accounts = accountsRepository.findAll();

        List<Accounts> displayAccounts= accounts.stream().filter(account -> !account.getName()
                .equals(principal.getName())).collect(Collectors.toList());

        log.info("List Size="+accounts.size());


        boolean accoutnExists= accounts.stream().anyMatch(account -> account.getName().equals(principal.getName()));
        double availableBalance=0;
        int accountId =0;
        if(!principal.getName().equals("admin")) {
            Accounts loggedInAccount = accountsRepository.findByName(principal.getName()).get();
            availableBalance = loggedInAccount.getBalance();
            accountId = loggedInAccount.getId();
        }

        model.addAttribute("accounts",displayAccounts);
        model.addAttribute("username",principal.getName());
        model.addAttribute("accountBalance",availableBalance);
        model.addAttribute("accountId",accountId);

        return "homepage";
    }

    /*@GetMapping("/api/bank/customer")
    public String getAccountDetails(@RequestParam("accountId") int id,Model model)
    {
        Optional<Accounts> acc = accountsRepository.findById(id);

        Accounts accounts;
        if(acc.isPresent())
             accounts = acc.get();
        else
            throw new NullPointerException();

        log.info("Account Details are:"+acc);

        model.addAttribute("accountDetails",accounts);

        return "accountDetails";
    }*/

    //Called when the "View Transactions" mesage is called on the Homepage
    @GetMapping("/api//bank/customer/viewTransactions")
    public String showTransactions(@RequestParam("accountId") int id,Model model) throws IllegalAccessException {
        //String id = request.getParameter("accountId");
        log.info("Customer Id:"+id);

        Optional<Accounts> account = accountsRepository.findById(id);
        Accounts ac = null;
        if(account.isPresent())
        {
            ac = account.get();
        }
        else
        {
            throw new IllegalAccessException("Not Available Account");
        }

        List<Transactions> transactions = transactionsRepository.findByAccountsId(id);

        model.addAttribute("transactions",transactions);
        model.addAttribute("accountName",ac.getName());
        model.addAttribute("accountBalance",ac.getBalance());

        return "transactions";
    }


    //Clicking on the Transfer Button in UI
    @GetMapping("/api/bank/customer/doTransaction")
    public String doTransaction(@RequestParam("accountId") int id,Model model,Principal principal)
    {

        TransactionForm transactionForm = new TransactionForm();
        Accounts fromAccount = accountsRepository.findByName(principal.getName()).get();

        log.info("Account Details="+fromAccount);

        double availableBalance = fromAccount.getBalance();

        Accounts toAccount =accountsRepository.findById(id).get();

        transactionForm.setFrom(fromAccount.getName());
        transactionForm.setTo(toAccount.getName());



        if(!model.containsAttribute("transactionForm")) {


            model.addAttribute("transactionForm", transactionForm);
            model.addAttribute("availableBalance", availableBalance);

            return "transaction-form";

        }
        else
        {
            model.addAttribute("availableBalance", availableBalance);

            return "transaction-form";
        }
    }

    //Method which is called on the Form Submission of a Transaction
    @PostMapping("/api/bank/customer/doTransaction")
    public String performTransaction(@Valid @ModelAttribute("transactionForm") TransactionForm transactionForm,
                                     BindingResult bindingResult, RedirectAttributes attr, HttpSession session)
    {
        if(bindingResult.hasErrors()) {
            Optional<Accounts> acc = accountsRepository.findByName(transactionForm.getFrom());
            Accounts actual = acc.get();

            attr.addFlashAttribute("org.springframework.validation.BindingResult.transactionForm", bindingResult);
            attr.addFlashAttribute("transactionForm", transactionForm);

            return "redirect:/api/bank/customer/doTransaction?accountId=" +actual.getId();
            //return "transaction-form";
        }

        log.info("From  Customer Name="+transactionForm.getFrom());
        log.info("To Customer Name ="+transactionForm.getTo());

        boolean b=accountService.updateAccounts(transactionForm);

        if(b) {
            sendSmsViaTwilio(transactionForm);

            return "redirect:/api/bank?success";
        }
        else {
            return "redirect:/api/bank?failed";
        }
    }

    //Calling the Twilio Micriservice Via Rest Template ,the messages to be delivered are formed in the method itself.
    private void sendSmsViaTwilio(TransactionForm transactionForm) {


            String debit = "Your Account has been debited by $" + transactionForm.getAmount() + " for a Transaction made on the Simple " +
                    "Payment App";

            String credit = "Your Account has been credited by $" + transactionForm.getAmount() + " for a Transaction made on the Simple " +
                    "Payment App";


            String debitNumber = accountsRepository.findByName(transactionForm.getFrom()).get().getPhoneNumber();
            String creditNumber = accountsRepository.findByName(transactionForm.getTo()).get().getPhoneNumber();

            SmsRequest debited = new SmsRequest(debitNumber, debit);
            SmsRequest credited = new SmsRequest(creditNumber, credit);

            restTemplate.postForObject("http://twilio-messaging-service/api/v1/sms", debited, SmsRequest.class);

            restTemplate.postForObject("http://twilio-messaging-service/api/v1/sms", credited, SmsRequest.class);


    }
}
