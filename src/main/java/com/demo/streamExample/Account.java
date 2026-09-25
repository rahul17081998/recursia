package com.demo.streamExample;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Data
@Getter
@Setter
@NoArgsConstructor

public class Account {
    private String accountId;
    private String holderName;
    private String currency;          // "USD", "INR", "EUR"
    private List<Transaction> transactions;

    // ---- inner (nested static) class ----
    @Data
    @Setter
    @Getter
    @NoArgsConstructor
    public static class Transaction {
        private String txnId;
        private String type;          // "DEBIT" or "CREDIT"
        private double amount;
        private String status;        // "SUCCESS", "FAILED", "PENDING"
        private LocalDate date;

        public Transaction(String txnId, String type, double amount,
                           String status, LocalDate date) {
            this.txnId = txnId;
            this.type = type;
            this.amount = amount;
            this.status = status;
            this.date = date;
        }
        public String getTxnId()   { return txnId; }
        public String getType()    { return type; }
        public double getAmount()  { return amount; }
        public String getStatus()  { return status; }
        public LocalDate getDate() { return date; }
    }

    public Account(String accountId, String holderName, String currency,
                   List<Transaction> transactions) {
        this.accountId = accountId;
        this.holderName = holderName;
        this.currency = currency;
        this.transactions = transactions;
    }
    public String getAccountId()             { return accountId; }
    public String getHolderName()            { return holderName; }
    public String getCurrency()              { return currency; }
    public List<Transaction> getTransactions() { return transactions; }
}
