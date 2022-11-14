package com.BMS.Model.Memento;

import java.util.Stack;

import com.BMS.Exception.*;
import com.BMS.Utils.Logger;

public class BMSCareTaker implements CareTaker {
    public static final BMSCareTaker instance = new BMSCareTaker();
    
    private Stack<Memento> mementos = new Stack<Memento>();

    public Memento save(Object building) throws BMSCustException {
       
        // get the memento
        Memento memento = null;
        // get the runtime type
        String mementoClassLocation = building.getClass().getName() + "Memento";

        try {
            Class<?> mementoClass = Class.forName(mementoClassLocation);
            memento = (Memento) mementoClass.getConstructor(building.getClass()).newInstance(building);
            mementos.push(memento);
            return memento;

        } catch (Exception e) {
            Logger.debug(e.getLocalizedMessage());
            throw new CouldNotFoundMementoClassException(building.getClass().getName());
        }
    }


    public void undoLast()
    {
        if(mementos.size() > 0)
        {
            mementos.pop().restore();
        }
    }

    public void undo(Memento memento)
    {
        memento.restore();
        // remove the memento from the stack
        mementos.remove(memento);
    }

}
