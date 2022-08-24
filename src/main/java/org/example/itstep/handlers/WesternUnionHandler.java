package org.example.itstep.handlers;

import org.example.itstep.BankAccount;
import org.example.itstep.NotEnoughMoneyException;
import org.example.itstep.Transfer;

public class WesternUnionHandler extends Handler {

    private Handler next;

    @Override
    public void Handle(Transfer transfer) {
        BankAccount sender = transfer.getSender();
        BankAccount receiver = transfer.getReceiver();
        Double commission = transfer.getMoney() * 0.15;

        transfer.saveSnapshots();
        try {
            sender.setBalance(sender.getBalance() - transfer.getMoney());
            receiver.setBalance(receiver.getBalance() + transfer.getMoney() - commission);

            if (sender.getBalance() < 0) {
                throw new NotEnoughMoneyException("The sender does not have enough money.");
            }

            System.out.println("WesternUnion made a money transfer. " +
                    "Sum: " + transfer.getMoney() +
                    ". Sender: " + transfer.getSender().getId() +
                    ". Receiver: " + transfer.getReceiver().getId() +
                    ". Transfer commission: " + commission);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            transfer.rollback();
        }
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }
}
