package org.example.itstep;

public class BankAccountSnapshot {
    private BankAccountType type;
    private Double balance;

    public BankAccountSnapshot(BankAccountType type, Double money) {
        this.type = type;
        this.balance = money;
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

    @Override
    public String toString() {
        return "BankAccountSnapshot{" +
                "type=" + type +
                ", money=" + balance +
                '}';
    }
}
