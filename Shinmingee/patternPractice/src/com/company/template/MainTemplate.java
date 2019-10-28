package com.company.template;

public class MainTemplate {
    public static void main(String[] args) {
            Me me = new Me();

            me.choiceVehicles("Bus");
            me.goToBuyTicket(2);
            me.getOn();
            me.getOff();

    }
}


abstract class goBusan {

    abstract void choiceVehicles(String vehicles);

    void goToBuyTicket(int numTicket) {
        if (numTicket != 0) {
            System.out.println("부산행 티켓을 " + numTicket + " 장 샀다.");
        }
    }
    abstract void getOn();

    abstract void getOff();
}