package com.BMS.Model;

import com.BMS.Utils.CIN;

public class Appartment extends Building {
    private double monthlyRental;
    private String supportStaff;

    public Appartment(int id , int noOfRooms,
            double monthlyRental , String supportStaff) {
        super(id, noOfRooms);
        this.monthlyRental = monthlyRental;
        this.supportStaff = supportStaff;
    }

    public double getMonthlyRental() {
        return monthlyRental;
    }

    public void setMonthlyRental(double monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    public String getSupportStaff() {
        return supportStaff;
    }

    public void setSupportStaff(String supportStaff) {
        this.supportStaff = supportStaff;
    }

    public void modifyBuilding() {
        System.out.print("Modify Monthly Rental : ");
        this.monthlyRental = CIN.nextDouble();
        CIN.nextLine();
        System.out.print("Modify Support Staff : ");
        this.supportStaff = CIN.nextLine();
        System.out.println("Building Modified:");
        printBuilding();
        System.out.println();
    }

    public void printBuilding() {
        System.out.println("Building No: " + getId() + ", Support Staff: " + getSupportStaff() + ", Monthly Rental: " + getMonthlyRental() );
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Building No: " + getId() + "\n");
        sb.append("Support Staff: " + getSupportStaff() + "\n");
        sb.append("Monthly Rental: " + getMonthlyRental() + "\n");
        for (Room room : getRooms()) {
            sb.append("Room No.: " + (getRooms().indexOf(room) + 1) + ", " + room + "\n");
        }
        return sb.append("\n").toString();
    }
    
}
