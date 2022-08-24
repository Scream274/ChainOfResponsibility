package org.example.itstep;

public class Transfer {
    private BankAccount sender;
    private BankAccount receiver;
    private Double money;
    private BankAccountSnapshot senderSnapshot;
    private BankAccountSnapshot receiverSnapshot;

    public Transfer(BankAccount sender, BankAccount receiver, Double money) {
        this.sender = sender;
        this.receiver = receiver;
        this.money = money;
    }

    public void saveSnapshots(){
        senderSnapshot = sender.getAccountSnapshot();
        receiverSnapshot = receiver.getAccountSnapshot();
    }

    public void rollback(){
        sender.setBalance(senderSnapshot.getBalance());
        sender.setType(senderSnapshot.getType());

        receiver.setBalance(receiverSnapshot.getBalance());
        receiver.setType(receiverSnapshot.getType());
    }

    public BankAccount getSender() {
        return sender;
    }

    public void setSender(BankAccount sender) {
        this.sender = sender;
    }

    public BankAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(BankAccount receiver) {
        this.receiver = receiver;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
