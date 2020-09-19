package com.neelav.simplepaymentapp.service;

import com.neelav.simplepaymentapp.model.SmsRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TwilioMessageService implements MessageService {

    private static Logger log = LoggerFactory.getLogger(TwilioMessageService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Async
    @Override
    public AsyncResult<SmsRequest> sendSms(String type, String phoneNumber, double amount) throws InterruptedException{

       log.info("Execute method asynchronously - "
               + Thread.currentThread().getName());

        String message = "Your Account has been "+type+"ed by $" + amount + " for a Transaction made on the Simple " +
                "Payment App";

        Thread.sleep(10000);


        SmsRequest smsRequest = new SmsRequest(phoneNumber, message);

        return  new AsyncResult<SmsRequest>(restTemplate.postForObject("http://twilio-messaging-service/api/v1/sms", smsRequest, SmsRequest.class));

    }
}
