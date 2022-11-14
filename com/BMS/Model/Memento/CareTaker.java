package com.BMS.Model.Memento;

import com.BMS.Exception.*;

public interface CareTaker {
    public Memento save(Object obj) throws BMSCustException;
    public void undoLast();
    public void undo(Memento memento);
}
