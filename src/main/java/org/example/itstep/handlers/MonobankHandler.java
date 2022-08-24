package org.example.itstep.handlers;

import org.example.itstep.BankAccount;
import org.example.itstep.BankAccountType;
import org.example.itstep.NotEnoughMoneyException;
import org.example.itstep.Transfer;

public class MonobankHandler extends Handler {
    private Handler next;

    @Override
    public void Handle(Transfer transfer) {
        BankAccount sender = transfer.getSender();
        BankAccount receiver = transfer.getReceiver();


        if (transfer.getSender().getType() != BankAccountType.CASH &&
                transfer.getReceiver().getType() != BankAccountType.CASH) {

            transfer.saveSnapshots();
            try {
                sender.setBalance(sender.getBalance() - transfer.getMoney());
                receiver.setBalance(receiver.getBalance() + transfer.getMoney());

                if (sender.getBalance() < 0) {
                    throw new NotEnoughMoneyException("The sender does not have enough money.");
                }

                System.out.println("Monobank made a money transfer. " +
                        "Sum: " + transfer.getMoney() +
                        ". Sender: " + transfer.getSender().getId() +
                        ". Receiver: " + transfer.getReceiver().getId());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                transfer.rollback();
            }
        } else if (next == null) {
            System.out.println("Monobank could not transfer money. Handlers are not available!");
        } else {
            System.out.println("Monobank could not transfer money.");
            next.Handle(transfer);
        }
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }
}
