package com.company.state;

public class Mario{
    public State state;//상태를 저장

    private NormalMario normalMario;
    private BigMario bigMario;
    private FireMario fireMario;

    public Mario(){
        normalMario = new NormalMario(this);
        bigMario = new BigMario(this);
        fireMario = new FireMario(this);

        state = normalMario;//초기상태 지정
    }

    public void setState(State state){
        this.state = state;
    }

    public State ChangeState(String stateName){
        switch (stateName){
            case "NormalMario":
                return normalMario;
            case "BigMario" :
                return bigMario;
            case  "FireMario" :
                return  fireMario;
            default:
                return null;
        }
    }

    public void aButton() {
        state.aButton();
    }


    public void bButton() {
        state.bButton();
    }


    public void damage() {
        state.damage();
    }


    public void bigMushroom() {
        state.bigMushroom();
    }


    public void fireMushroom() {
        state.fireMushroom();
    }
}
