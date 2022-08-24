package org.example.itstep.handlers;

import org.example.itstep.BankAccount;
import org.example.itstep.BankAccountType;
import org.example.itstep.NotEnoughMoneyException;
import org.example.itstep.Transfer;

public class AlfabankHandler extends Handler {

    private Handler next;

    @Override
    public void Handle(Transfer transfer) {
        BankAccount sender = transfer.getSender();
        BankAccount receiver = transfer.getReceiver();

        if (transfer.getSender().getType() != BankAccountType.ONLINE &&
                transfer.getReceiver().getType() != BankAccountType.ONLINE) {

            transfer.saveSnapshots();
            try {
                sender.setBalance(sender.getBalance() - transfer.getMoney());
                receiver.setBalance(receiver.getBalance() + transfer.getMoney());

                if (sender.getBalance() < 0) {
                    throw new NotEnoughMoneyException("The sender does not have enough money.");
                }

                System.out.println("Alfa bank made a money transfer. " +
                        "Sum: " + transfer.getMoney() +
                        ". Sender: " + transfer.getSender().getId() +
                        ". Receiver: " + transfer.getReceiver().getId());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                transfer.rollback();
            }

        } else if (next == null) {
            System.out.println("Alfa bank could not transfer money. " +
                    "Handlers are not available!");
            return;
        } else {
            System.out.println("Alfa bank could not transfer money.");
            next.Handle(transfer);
        }

    }

}
