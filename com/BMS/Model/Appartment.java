package com.BMS.Model;

import com.BMS.Utils.CIN;

/**
 * The Appartment class.
 *
 * @see com.BMS.Model.Building
 */
public class Appartment extends Building {
    private double monthlyRental;
    private String supportStaff;

    public Appartment(int id, int noOfRooms,
                      double monthlyRental, String supportStaff) {
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
        System.out.println(this);
        System.out.println();
    }

    public String toString() {
        return "Building No: " + getId() + ", Support Staff: " + getSupportStaff() + ", Monthly Rental: " + getMonthlyRental();
    }

    public void printBuilding() {
        StringBuilder sb = new StringBuilder();
        sb.append("Building No: ").append(getId()).append("\n");
        sb.append("Support Staff: ").append(getSupportStaff()).append("\n");
        sb.append("Monthly Rental: ").append(getMonthlyRental()).append("\n");
        for (Room room : getRooms()) {
            sb.append("Room No.: ").append(getRooms().indexOf(room) + 1).append(", ").append(room).append("\n");
        }
        sb.append("\n").toString();
        System.out.println(sb);
    }

}
