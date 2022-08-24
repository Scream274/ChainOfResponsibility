package org.example.itstep;

import org.example.itstep.handlers.*;

public class App {
    public static void main(String[] args) {
        var acc1 = new BankAccount(34201L,BankAccountType.ONLINE, 2666D);
        var acc2 = new BankAccount(31421L,BankAccountType.CRYPTO, 1000D);

        var transfer1 = new Transfer(acc1, acc2, 3000D);

        Handler pbHandler = new PrivatBankHandler();
        Handler mbHandler = new MonobankHandler();
        Handler wuHandler = new WesternUnionHandler();
        Handler abHandler = new AlfabankHandler();

        System.out.println(acc1.getBalance());
        System.out.println(acc2.getBalance());

        pbHandler.setNext(mbHandler);
        mbHandler.setNext(abHandler);
        abHandler.setNext(wuHandler);

        pbHandler.Handle(transfer1);

        System.out.println(acc1.getBalance());
        System.out.println(acc2.getBalance());

    }
}
