# Design Pattern Study

### Strategy Pattern
#### : 인터페이스의 구현으로 동적으로 행위를 자유롭게 바꿀 수 있게 해주는 패턴.
***
Ex. Shape  코드  
&nbsp;&nbsp;&nbsp;&nbsp;삼각형, 사각형, 원을 등록할 수 있고  
&nbsp;&nbsp;&nbsp;&nbsp;각각의 도형들에는 console로 각도형의 형태를 프린트 함.  
&nbsp;&nbsp;&nbsp;&nbsp;collection에 등록된 모든 도형은 allDraw 메소드로 한번에 그려져야함.
 
```java
 public class MainStrategy {

    public static void main(String[] args) {
        Shapes s = new Shapes();
        Triangle triangle = new Triangle();
        Circle circle = new Circle();
        Rect rect = new Rect();
        
        triangle.draw();
        circle.draw();
        rect.draw();

        s.registerShape("Triangle");
        s.registerShape("Rect");
        s.registerShape("Triangle");
        s.registerShape("Circle");

        s.drawAll();

    }

}
```

```java
public interface Shape {
    public void draw();
}
```

```java
class Triangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Triangle!!!");
    }

}

class Rect implements Shape{

    @Override
    public void draw() {
        System.out.println("Rect!!!");
    }

}

class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("Circle!!!");
    }

}
```
```java
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
```
