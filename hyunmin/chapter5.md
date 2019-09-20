### 1 단일 책임 원칙
단어만 봐도, 너무 


### 2 개방 폐쇄 원칙
나는 개방폐쇄 원칙에 집중하였다. 
리스코프 치환 원칙과 의존 역전 원칙이 모두 개방폐쇄 원칙과 매우 밀접한 이론인 동시에, 코드의 유연성과도 연관되어 있기 때문에, 이 부분이 중요하다고 생각했다.

개방폐쇄 원칙을 좀 더 쉽게 표현해 보자면,
기능은 확장이 가능하도록 설계를 하는 동시에, 기능이 추가될 때마다 기존 코드의 수정을 최소화 하도록 하는 것이다.

따라서 무엇이 변하는지, 무엇이 변하지 않는지 구분하는 것이 중요하다.

예시 

```cpp

class CMyList
{
public:
	CMyList(CMyNode *pHead);
	~CMyList(void);

protected:
	void ReleaseList(void);
	CMyNode *m_pHead;

public:
	CMyNode* FindNode(const char* pszKey);
	int AddNewNode(CMyNode *pNewNode);
	void PrintAll(void);
	int RemoveNode(const char* pszKey);

};

class CUserData (원칙을 지키지 않은 클래스)
{
	friend class CMyList;
public:
	CUserData(void);
	~CUserData(void);

	const char*			GetName(void) const		{ return szName; }
	const char*			GetPhone(void) const	{ return szPhone; }
	const CUserData*	GetNext(void) const		{ return pNext; }

	static int GetUserDataCounter(void) { return nUserDataCounter; }

protected:
	char	szName[32];
	char	szPhone[32];

	CUserData *pNext;

	static int nUserDataCounter;
};

```

```cpp

class CMyList (변동없음)
{
public:
	CMyList(CMyNode *pHead);
	~CMyList(void);

protected:
	void ReleaseList(void);
	CMyNode *m_pHead;

public:
	CMyNode* FindNode(const char* pszKey);
	int AddNewNode(CMyNode *pNewNode);
	void PrintAll(void);
	int RemoveNode(const char* pszKey);

};

class CMyNode (추가된 인터페이스)
{
	friend class CMyList;
public:
	CMyNode(void);
	virtual ~CMyNode(void);

	CMyNode* GetNext(void) const { return pNext; }

	virtual const char* GetKey(void) = 0;
	virtual void PrintNode(void) = 0;

private:
	CMyNode *pNext;
};

class CUserData : public CMyNode
{
public:
	CUserData(void);
	~CUserData(void);

	const char*			GetName(void) const		{ return szName; }
	const char*			GetPhone(void) const	{ return szPhone; }
	static int GetUserDataCounter(void) { return nUserDataCounter; }

protected:
	char	szName[32];
	char	szPhone[32];

	static int nUserDataCounter;
public:
	virtual const char* GetKey(void);
	virtual void PrintNode(void);
	CUserData(const char* pszName, const char* pszPhone);
};

```

관리시스템을 CMyList라고 하고, 관리 대상 자료를 CUserData 라고 본다면, 한가지 어색한 부분이 있다.
CUserData의 pNext함수는 구체적인 자료라기 보다는, 관리시스템이 가진 기능의 일부로 보는 것이 적합하다.

그래서 관리시스템을 CMyList + CMyNode(pNext, GetKey)라고 분리하게 되면, 
이제는 CMyList를 상속받은 어떤 클래스라도 모두 CMyList가 관리할 수 있게 된다.


### 3 리스코프 치환 원칙
이 원칙을 지키는 하나의 방법은 부모클래스를 받은 자식 클래스가 재정의하지 않는 것이다.
하지만 현실은 그렇지 못하기 때문에, 재정의해야할 것과 하지 않아야할 것으로 구분해야하고,
재정의 한다면, 상위 클래스의 인풋과 리턴값을 잘 이해해서 재정의해야한다. 


### 4 인터페이스 분리 원칙
단일책임원칙과 밀접하며, 역할을 분리하여 변경을 최소화하는 하자는 것이다. 

### 5 의존 역전 원칙
요약해보면, 변화하기 어렵거나 거의 없는 곳에 의존하라는 것이다. 



참고 사이트/도서

https://gmlwjd9405.github.io/2018/07/05/oop-solid.html

이것이 C++이다.

