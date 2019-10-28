package com.company.state;

public class Mario{
    private State state;

    public void Mario(State state){
        this.state = state;
    }

    public void aButton() {
        System.out.println("JUMP!");
    }

    public void bButton() {
        System.out.println("JUMP!");
    }

    public void damage() {
        System.out.println("die...");
    }

    public void bigMushroom() {
        state.bigMushroom(this);
        //bigMario
    }

    public void fireBall() {

    }
}
