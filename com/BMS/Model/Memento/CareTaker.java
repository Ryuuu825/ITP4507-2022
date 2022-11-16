package com.BMS.Model.Memento;

import com.BMS.Exception.BMSCustException;

/**
 * The CareTaker instance.
 *
 * @see com.BMS.Model.Memento.Memento
 */
public interface CareTaker {
    Memento save(Object obj) throws BMSCustException;

    void undoLast();

    void undo(Memento memento);
}
