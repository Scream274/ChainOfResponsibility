package org.example.itstep.handlers;

import org.example.itstep.BankAccount;
import org.example.itstep.BankAccountType;
import org.example.itstep.NotEnoughMoneyException;
import org.example.itstep.Transfer;

public class PrivatBankHandler extends Handler {

    private Handler next;

    @Override
    public void Handle(Transfer transfer) {
        BankAccount sender = transfer.getSender();
        BankAccount receiver = transfer.getReceiver();

        if (transfer.getMoney() < 50000 &&
                transfer.getSender().getType() != BankAccountType.CRYPTO &&
                transfer.getReceiver().getType() != BankAccountType.CRYPTO) {

            transfer.saveSnapshots();
            try {
                sender.setBalance(sender.getBalance() - transfer.getMoney());
                receiver.setBalance(receiver.getBalance() + transfer.getMoney());

                if (sender.getBalance() < 0) {
                    throw new NotEnoughMoneyException("The sender does not have enough money.");
                }

                System.out.println("PrivateBank made a money transfer. " +
                        "Sum: " + transfer.getMoney() +
                        ". Sender: " + transfer.getSender().getId() +
                        ". Receiver: " + transfer.getReceiver().getId());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                transfer.rollback();
            }
        } else if (next == null) {
            System.out.println("PrivateBank could not transfer money. Handlers are not available!");
            return;
        } else {
            System.out.println("PrivateBank could not transfer money.");
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
