package com.BMS.Model;

public class AppartmentMemento extends BuildingMemento {

    // double monthlyRental; String supportStaff;
    private double monthlyRental;
    private String supportStaff;

    public AppartmentMemento(Appartment appartment) {
        super(appartment);
        this.monthlyRental = appartment.getMonthlyRental();
        this.supportStaff = appartment.getSupportStaff();
    }

    public void restore() {
        super.restore();
        Appartment appartment = (Appartment) building;
        appartment.setMonthlyRental(monthlyRental);
        appartment.setSupportStaff(supportStaff);
    }

    
}
