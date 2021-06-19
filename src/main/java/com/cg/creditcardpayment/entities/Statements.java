package com.cg.creditcardpayment.entities;

import java.time.LocalDate;

 

public class Statements {
    
    private Long statementId;
    
    private double dueAmount;
    
    private double billAmount;
    
    private LocalDate billingDate;
    
    private LocalDate dueDate;
    
    private String customerId;

    private String cardNumber;
       
    public Statements() {
        super();
    }

    public Statements(Statement stmt)
    {
        super();
        this.statementId = stmt.getStatementId();
        this.dueAmount = stmt.getDueAmount();
        this.billAmount = stmt.getBillAmount();
        this.billingDate = stmt.getBillingDate();
        this.dueDate = stmt.getDueDate();
        this.customerId = stmt.getCustomer().getUsername();
        this.cardNumber = stmt.getCreditCard().getCardNumber();
    }


    public Long getStatementId() {
        return statementId;
    }

    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

 

    public double getDueAmount() {
        return dueAmount;
    }

 

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

 

    public LocalDate getBillingDate() {
        return billingDate;
    }

 

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }

 

    public LocalDate getDueDate() {
        return dueDate;
    }

 

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }  
    
}
