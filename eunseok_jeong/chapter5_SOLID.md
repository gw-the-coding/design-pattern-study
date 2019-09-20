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

```
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







