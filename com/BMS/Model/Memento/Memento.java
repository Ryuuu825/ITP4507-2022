package com.BMS.Model.Memento;

/**
 * The Memento interface.
 *
 * @see com.BMS.Model.Memento.CareTaker
 */
public interface Memento {
    void restore();

    void printDescription();
}
