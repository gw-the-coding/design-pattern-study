package com.company.state;

public class BigMario implements State{
    private Mario mario;

    public BigMario(Mario mario){
        this.mario = mario;
//        System.out.println("[BIG MARIO]");
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
        System.out.println("Size Down");
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
