- 단일 책임 원칙(single Responsibility Principle, SRP)
각 클래스는 단 한가지의 책임을 부여 받아, 수정할 이유가 단 한 가지여야 한다.
SRP를 위배하는 안티 패턴의 극단적인 예로 전지전능 객체가 있다. 
전지전능 객체는 가능한 많은 기능을 담아 하나의 괴물 같은 클래스를 이룬다.
이러한 객체와 함께 일하는 것으 대단히 어렵다.

메모장 하나가 있을때

```cpp
struct Journal
{
  string title;
  vector<string> entries;

  explicit Journal(const string& title) : title{title} {}
};
```

메모장의 각 항목을 기입된 순서대로 저장하는 부분을 구성
```cpp
void Journal::add(const string& entry)
{
  static int count =1;
  entries.push_back(boost::lexical_cast<string>(count++)
    +": " + entry);
}

Journal j{"Dear Diary"};
j.add("I cried today");
j.add("I ate a bug");
```
이때 add함수가 메모장안에 구성되는 것은 매우 자연스러운 일이다.

만약 메모장을 영구적으로 파일에 저장하는 기능을 만들려면 어디에 구성해야할까?
```cpp
void Journal::save(const string& filename)
{
  ofstream ofs(filename);
  for (auto& s : entries)
    ofs << s << endl;
}
```
이 방식은 문제가 있다. 메모장의 책임은 메모 항목들을 기입/관리하느 것이지 디스크에 쓰느 것이 아니다.
만약 디스크에 파일을 쓰는 기능을 데이터의 기입/관리를 담당하는 클래스가 함께 책임지도록 한다면,
데이터 저장 방식이 바뀔 때마다 그러한 클래스들을 일일이 모두 수정해야 한다.

따라서, 파일 저장 기능은 메모장과 별도로 취급하여 별도의 클래스로 만드느 것이 바람직하다.
```cpp
struct PersistenceManager
{
  static void save(const Journal& j, const string& filename)
  {
    ofstream ofs(filename);
    for (auto& s : j.entries)
      ofs << s << endl;
  }
};
```

-열림-닫힘 원칙(Open-Closed Principle, OCP)
열림-닫힘 원칙으 타입이 확장에느 열려 있지만 수정에느 닫혀 있도록 강제하는 것을 뜻함
기존코드의 수정없이(배포된 코드의 수정없이) 기능을 확장할 수 있도록 구현해야한다.


데이터베이스에 어떤 제품군에 대한 정보가 저장되어 있다고 할때,

```cpp
enum class Color { Red, Green, Blue };
enum class Size { Small, Medium, Large };

struct Product
{
  string name;
  Color color;
  Size size;
};
```

주어진 제품 집합을 조건에 따라 필터링 하는 기능 구성

```cpp
struct ProductFilter
{
  typedef vector<Product*> Items;
};

ProductFilter::Items ProductFilter::by_color(Items items, Color color)
{
  Items result;
  for (auto& i : items)
    if (i->color == color)
      result.push_back(i);
  return result;
}
```
만약 사이즈에 의해서 필터링을 한다면?

```cpp
ProductFilter::Items ProductFilter::by_size(Items items, Size size)
{
  Items result;
  for (auto& i : items)
    if (i->size == size)
      result.push_back(i);
  return result;
}
```

확실히 뭔가 같은 작업을 반복하는 느낌..

또 다시 요구사항이 들어와서, 이번엔 컬러와 사이즈르 모두 지정해서 필터링 하려 한다면?
또 다시 메소드를 구성해야 할까?

spec을 따로 정의해보면 어떨까?

```cpp
struct Specification
{
  virtual bool is_satisfied(Product* item) = 0;
}

struct Filter
{
  virtual vector<Product*> filter(vector<Product*> items, Specification& spec) = 0;
};

struct BetterFilter : Filter<Product>
{
  vector<Product*> filter(vector<Product*> items,Specification& spec) override
  {
    vector<Product*> result;
    for (auto& p : items)
      if (spec.is_satisfied(p))
        result.push_back(p);
    return result;
  }
};

struct ColorSpecification : Specification
{
  Color color;
  explicit ColorSpecification(const Color color) : color{color} {}
  
  bool is_satisfied(Product* item) override {
  return item->color == color;
  }
};

```

위와 같이 구성하면 아래와 같이 필터링 코드르 구성할 수 있음

```cpp

Product apple{"Apple", Color::Green, Size::Small};
Product tree{"Tree", Color::Green, Size::Large};
Product house{"House", Color::Blue, Size::Large};

vector<Product*> all {&apple, &tree, &house};

BetterFilter bf;
ColorSpecification green(Color::Green);

auto green_things = bf.filter(all, green);
for (auto& x : green_things)
  cout << x->name << " is green" << endl;
```

여기서 단순히 Size에 대한 필터를 구성하려면 
SizeSpecification을 구성한다.
둘다 하려면 ColorSpecification과 SizeSpecification을 가지는 AndSpecification을 구성하여 필터에 넣는것으로 구현한다.

이럴 경우 기존의 소스를 수정하거나 하지 않으면서, 확장하는 방법으로 소스를 구성할 수 있게 된다.



- 리스코프(Liskov) 치환 원칙(Liskov Substitution Principle, LSP)
어떤 자식 객체에 접근할 때 그 부모 객체의 인터페이스로 접근하더라도 아무런 문제가 없어야 한다.
자식 객체를 그 부모 객체와 동등하게 취급할 수 있어야 한다.


```cpp

class Rectangle
{
protected:
  int width, height;
public:
  Rectangle(const int width, const int height)
    : width{width}, height{height} { }
    
  int get_width() const { return width;}
  virtual void set_width(const int width) { this->width = width;}
  int get_height() const { return height;}
  virtual void set_height(const inst height) {this->height = height;}
  int area() const {return width * height;}
};


class Square : public Rectangle
{
  public:
    Square(int size) : Rectangle(size,size) {}
    void set_width(const int width) override {
    this->width = height = width;
    }
    void set_height(const int height) override {
    this->height = width = height;
    }
}

void process(Rectangle& r)
{
  int w = r.get_width();
  r.set_height(10);
  
  cout << expected area = " << (w * 10) << ", got " << r.area() << endl;
}

Square s{5};
process(s); //기대된 결과 = 50, 구해진 값 = 100

```

결국엔 파생된 서브 클래스 Square를 그 부모 클래스 Rectangle 타입으로 활용할 때,
당장은 괜찮을 수 있어도 나중에 문제가 발견된 수 있다.

이런경우 아예 서브클래스를 두기 보다 Factory클래스를 두어 직사각형과 정사각형을 따로따로 생성한다면?

```cpp
struct RectangleFactory
{
  static Rectangle create_rectangle(int w, int h);
  static Rectangle create_square(int size);
}

bool Rectangle::is_square() const
{
  return width == height;
}

```


- 인터페이스 분리 원칙(Interface Segregation Principle, ISP)
필요에 따라 구현할 대상을 선별할 수 있도록 인터페이스를 별개로 두어야 한다.
한 덩어리의 복잡한 인터페이스를 목적에 따라 구분하여 나눔으로써,
인터페이스 모든 항목에 대한 구현을 강제하지 않고
실제 필요한 인터페이스만 구현할 수 있도록 한다.

```cpp
struct MyFavouritePrinter : IMachine
{
  void print(vector<Document*> docs) override;
  void fax(vector<Document*> docs) override;
  void scan(vector<Document*> docs) override;
}//복합기를 하나 만들었다.

struct IMachine
{
  virtual void print(vector<Document*> docs) = 0;
  virtual void fax(vector<Document*> docs) = 0;
  virtual void scan(vector<Document*> docs) = 0;
}//프린터의 구현을 하청업체에게 맡기고 싶어. 각 제품 라인업에 따라 구현을 달리하도록 만들어야지.
//만약 print만 되는 프린터를 만들려면 어떡하지?
//fax, scan을 어떻게 구현해야하지?

```

```cpp
struct IPrinter
{
  virtual void print(vector<Document*> docs) = 0;
};

struct IScanner
{
  virtual void scan(vector<Document*> docs) = 0;
}
//기능별로 인터페이스를 분리하면 원하는 기능만을 섞어서 구현이 가능하다.

struct Printer : IPrinter
{
  void print(vector<Document*> docs) override;
};

struct IMachine: IPrinter, IScanner
{};

struct Machine: IMachine
{
  IPrinter& printer;
  IScanner& scanner;
  
  Machine(IPrinter& printer, IScanner& scanner)
    : printer{printer}, scanner{scanner}
  {}
  
  void print(vector<Document*> docs) override {
    printer.print(docs);
  }
  
  void scan(vector<Document*> docs) override {
    scanner.scan(docs);
  }
};
```


만약 어떤 어플리케이션의 플러그인 모듈을 개바할 때 뭐가 뭔지 알 수 없는 혼란스럽기만한 수십 개의 함수를 빈 껍데기, 또는 null리턴으로 구현하고 있다면
그 애플리케이션의 플러그인 인터페이스 설계자가 인터페이스 분리 원칙을 위반한 것임




-



