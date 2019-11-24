package main.java;


import java.util.Date;

public class Transaction {

    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private Date createdAt;
    private Double amount;
    private String transactionType;


    private String relatedTransaction;

    public Transaction(String transactionId, String fromAccountId, String toAccountId, Date createdAt, Double amount, String transactionType,String relatedTransaction) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.createdAt = createdAt;
        this.amount = amount;
        this.transactionType = transactionType;
        this.relatedTransaction =relatedTransaction;
    }

    public String getTransactionId() {
        return transactionId;
    }


    public String getFromAccountId() {
        return fromAccountId;
    }


    public String getToAccountId() {
        return toAccountId;
    }



    public Date getCreatedAt() {
        return createdAt;
    }


    public Double getAmount() {
        return amount;
    }


    public String getTransactionType() {
        return transactionType;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }

}
