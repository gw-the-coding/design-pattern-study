package com.company.state;

public class FireMario implements State{
    private Mario mario;

    public FireMario(Mario mario){
        this.mario = mario;
//        System.out.println("[FIRE MARIO]");
    }

    @Override
    public void aButton() {
        System.out.println("FIRE BALL!!");
    }

    @Override
    public void bButton() {
        System.out.println("JUMP_B");
    }

    @Override
    public void damage() {
        System.out.println("LOSE FIRE");
        mario.state = mario.ChangeState("NormalMario");
    }

    @Override
    public void bigMushroom() {
        System.out.println("Got Big Mushroom!");
        mario.state = mario.ChangeState("BigMario");
    }

    @Override
    public void fireMushroom() {
        System.out.println("Got Fire Mushroom!");
        mario.state = mario.ChangeState("FireMario");
    }
}
