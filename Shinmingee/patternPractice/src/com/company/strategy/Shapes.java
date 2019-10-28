package com.company.strategy;

import java.util.ArrayList;

public class Shapes {
    private Shape shape;

    public void Shape(){

        this.shape = shape;
    }

    ArrayList<String> arrayList = new ArrayList<>();

    public void registerShape(String s){
        arrayList.add(s);
    }

    public void drawAll(){
        for(int i=0; i<=arrayList.size(); i++){
            System.out.println(arrayList.get(i));
        }
    }
}
