package com.neelav.simplepaymentapp.service;

import org.springframework.scheduling.annotation.AsyncResult;

public interface MessageService {

    public AsyncResult sendSms(String type, String phoneNumber, double amount) throws InterruptedException;
}
