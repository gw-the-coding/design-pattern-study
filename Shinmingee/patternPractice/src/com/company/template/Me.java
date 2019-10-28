package com.company.template;

public class Me extends goBusan{

    public String v;

    @Override
    void choiceVehicles(String v) {
        if (v == "Bus")
            System.out.println("센트럴 시티 터미널에 간다.");
        else if(v=="Train")
            System.out.println("서울역에 간다.");
        else if(v=="Airplane")
            System.out.println("김포공항에 간다.");
    }

    @Override
    void getOn() {
        System.out.println("탑승한다~");
    }

    @Override
    void getOff() {
        System.out.println("하차한다!");
    }
}


