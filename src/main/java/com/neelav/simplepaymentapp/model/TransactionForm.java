package com.neelav.simplepaymentapp.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransactionForm {

    @NotNull(message ="is required")
    private String to;

    @NotNull(message ="is required")
    private String from;

    @Min(value = 5,message = "The Transaction Amount must be greater than 5 Dollars !")
    private double amount;

    public TransactionForm() {
    }

    public TransactionForm(@NotNull(message = "is required") String to, @NotNull(message = "is required") String from, @Min(value = 5, message = "The Transaction Amount must be greater than 5 Dollars !") double amount) {
        this.to = to;
        this.from = from;
        this.amount = amount;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
