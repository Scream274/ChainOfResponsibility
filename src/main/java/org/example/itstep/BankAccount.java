package org.example.itstep;

public class BankAccount {

    private Long id;
    private BankAccountType type;
    private Double balance;

    public BankAccount(Long id, BankAccountType type, Double balance) {
        this.id = id;
        this.type = type;
        this.balance = balance;
    }

    public BankAccountSnapshot getAccountSnapshot(){
        return new BankAccountSnapshot(type, balance);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccountType getType() {
        return type;
    }

    public void setType(BankAccountType type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
