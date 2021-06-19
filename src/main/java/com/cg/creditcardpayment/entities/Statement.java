package com.cg.creditcardpayment.entities; 

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

 

import org.springframework.format.annotation.DateTimeFormat;

 

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
*StatementEntity
* The Statement program implements an application such that
* the data of the Statements is sent to the database
*/
@Entity
public class Statement {
    /**
    * This a local variable: {@link #statementId} defines the unique statementId of the statement
    * @HasGetter
    * @HasSetter
    */
    @Id
    @Column(name="statement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statementId;
    /**
    * This a local variable: {@link #dueAmount} defines the due amount of the statement
    * @HasGetter
    * @HasSetter
    */
    @PositiveOrZero(message = "Due Amount has to be zero or more")
    private double dueAmount;
    /**
    * This a local variable: {@link #billAmount} defines the billAmount of the statement
    * @HasGetter
    * @HasSetter
    */
    @Positive(message="Bill Amount has to be greater than zero")
    private double billAmount;
    /**
    * This a local variable: {@link #billDate} defines the bill date of the statement
    * @HasGetter
    * @HasSetter
    */
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate billingDate;
    /**
    * This a local variable: {@link #dueDate} defines the dueDate of the statement
    * @HasGetter
    * @HasSetter
    */
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    
    @JsonBackReference(value="customer")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private Customer customer;
    
    @JsonBackReference(value="credit-card")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="card_number")
    private CreditCard creditCard;
    
    
    public Statement() {
        super();
    }
    
    /**
     * @param statementId
     * @param dueAmount
     * @param billAmount
     * @param billingDate
     * @param dueDate
     * @param customer
     */
    public Statement(Long statementId, @PositiveOrZero(message = "Due Amount has to be zero or more") double dueAmount,
            @Positive(message = "Bill Amount has to be greater than zero") double billAmount,
            @FutureOrPresent LocalDate billingDate,
            @FutureOrPresent LocalDate dueDate, Customer customer) {
        super();
        this.statementId = statementId;
        this.dueAmount = dueAmount;
        this.billAmount = billAmount;
        this.billingDate = billingDate;
        this.dueDate = dueDate;
        this.customer = customer;
    }
    
    /**
     * @param statementId
     * @param dueAmount
     * @param billAmount
     * @param billingDate
     * @param dueDate
     * @param customer
     * @param creditCard
     */
    public Statement(Long statementId, @PositiveOrZero(message = "Due Amount has to be zero or more") double dueAmount,
            @Positive(message = "Bill Amount has to be greater than zero") double billAmount,
            @FutureOrPresent LocalDate billingDate,
            @FutureOrPresent LocalDate dueDate, Customer customer,
            CreditCard creditCard) {
        super();
        this.statementId = statementId;
        this.dueAmount = dueAmount;
        this.billAmount = billAmount;
        this.billingDate = billingDate;
        this.dueDate = dueDate;
        this.customer = customer;
        this.creditCard = creditCard;
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

 

    public Customer getCustomer() {
        return customer;
    }

 

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

 

    public double getBillAmount() {
        return billAmount;
    }

 

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

 

    public CreditCard getCreditCard() {
        return creditCard;
    }
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
    
    
}