package com.company.state;

public class Character {
    private State state;
    private Character character;

    public Character(){
        state = new Mario();
    }

    public void aButton(Character character){
        state.jump();
    }
    public void bButton(Character character){
        state.jump();
    }
    public void damage(Character character){
        state.die();
    }

    public void damage(BigMario bigMario){
        state.sizeDown(this);
    }

    public void bigMushrooms(){
        state.sizeUP(this);
        state = new BigMario();
    }

    public void fireball(){
        state.fireBall(this);
        state = new FireMario();
    }

}
