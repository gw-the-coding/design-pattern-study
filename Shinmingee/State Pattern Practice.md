# Design Pattern Study

### State Pattern
##### : 특정 상태에 따라 동작이 달라지는 상황에서, 상태를 객체화 시켜서 동작하게 하는 패턴.
***
Ex. Mario 연습코드 설명  
&nbsp;&nbsp;&nbsp;&nbsp; 마리오의 기본세팅이 있고, 특정 상황에서 상태가 바뀐다.  
&nbsp;&nbsp;&nbsp;&nbsp; 바뀐 상태에서 동작이 실행되어야 한다.  
&nbsp;&nbsp;<연습코드 메인클래스 진행 순서>  
&nbsp;&nbsp;&nbsp;&nbsp; 
&nbsp;&nbsp;&nbsp;&nbsp; 큰버섯 먹었다. -> 데미지를 입었다. -> 파이어볼 버섯을 먹었다. ->  
&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp; a버튼을 눌렀다 -> b버튼을 눌렀다. -> 데미지를 입었다. -> a버튼을 눌렀다. -> 데미지를 입었다.  

|  <center></center> |  <center>Normal Mario</center> |  <center>Fire Mario</center> |  <center>Big Mario</center> |
|:--------|:--------|:--------|:--------|
| <center>**A-Button**</center> | <center>Jump</center> | <center>Fire Ball</center> | <center>Jump</center> |
| <center>**B-Button**</center> | <center>Jump</center> | <center>Jump</center> | <center>Jump</center> |
| <center>**Damage**</center> | <center>Game Over</center> | <center>(normal mario)</center> | <center>(normal mario)</center> |
| <center>**Big Mushroom**</center> | <center>(big mario)</center> | <center>(big mario)</center> | <center>(big mario)</center> |
| <center>**Fire Mushroom**</center> | <center>(fire mario)</center> | <center>(fire mario)</center> | <center>(fire mario)</center> |

-------
```java
 public class MainState {

    public static void main(String[] args) {
        Mario mario = new Mario();

        mario.bigMushroom();
        mario.damage();
        mario.fireMushroom();
        mario.aButton();
        mario.bButton();
        mario.damage();
        mario.aButton();
        mario.damage();

    }

}
```

```java
public interface State { //상태정의
    void aButton();
    void bButton();
    void damage();
    void bigMushroom();
    void fireMushroom();
}
```

```java
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
```
```java
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
```

```java
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

```

```java
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
```
