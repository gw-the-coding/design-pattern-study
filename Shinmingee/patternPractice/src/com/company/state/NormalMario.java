package com.company.state;

public class NormalMario implements State{

    private Mario mario;

    public NormalMario(Mario mario){
        this.mario = mario;
//        System.out.println("[NORMAL MARIO]");
    }

    @Override
    public void aButton() {
        System.out.println("JUMP_A");
    }

    @Override
    public void bButton() {
        System.out.println("JUMP_B");
    }

    @Override
    public void damage() {
        System.out.println("GAME OVER");
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
