package com.neelav.simplepaymentapp.model;

public class TransactionForm {

    private String to;

    private String from;
    
    private double amount;

    public TransactionForm() {
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
