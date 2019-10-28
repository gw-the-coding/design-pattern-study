# Design Pattern Study
### Template Method
#### : 동일한 과정을 추상메서드로 구현하여 코드의 재사용성을 높이는 패턴.
***
Ex. 대중교통으로 부산 가기  
- 버스
 <br> - 센트럴 시티 터미널에 간다.
 <br> - 표를 산다
- 기차
 <br> - 서울역에 간다.
- 비행기
 <br> - 김포공항에 간다.
##### 동일한 과정
1. 터미널(역)에 가서 표를 사는 것
2. 탑승
3. 하차


```java
public class MainTemplate {
    public static void main(String[] args) {
            Me me = new Me();

            me.choiceVehicles("Bus");
            me.goToBuyTicket(2);
            me.getOn();
            me.getOff();

    }
}
```

```java
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
```

```java
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
```
