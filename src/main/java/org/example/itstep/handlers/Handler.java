package org.example.itstep.handlers;

import org.example.itstep.BankAccountSnapshot;
import org.example.itstep.Transfer;

public abstract class Handler {
    private Handler next;

    public abstract void Handle(Transfer transfer);

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

}
