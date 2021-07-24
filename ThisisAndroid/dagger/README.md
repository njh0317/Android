# Dagger

## App
[Android Kotlin Dagger2](https://www.youtube.com/watch?v=_B0skaOiVCU)를 참고하였습니다.
+ Retrofit2, MVVM, RecyclerView, GithubAPI 사용

## dagger 기본개념 
1. Inject
2. Component
3. Subcomponent
4. Module
5. Scope

### Inject
+ 의존성 주입 요청
+ Inject 어노테이션으로 주입을 요청하면 연결된 Component가 Module로 부터 객체를 생성하여 넘겨준다.

### Component
+ 연결된 Module을 이용하여 의존성 객체를 생성하고, Inject로 요청받은 인스턴스에 생성한 객체를 주입한다.
+ 의존성을 요청 받고 주입하는 Dagger의 주된 역할을 수행

### Subcomponent
+ Component는 계층관계를 만들 수 있다.
+ Subcomponent는 Dagger의 중요한 컨셉인 그래프 형성
+ Inject 로 주입을 요청받으면 Subcomponent에서 먼저 의존성을 검색하고, 없으면 부모로 올라가면서 검색한다.
+ Subcomponent는 Inner class 방식의 하위계층 Component이다.
+ Sub의 Sub 가능

### Module
+ Component에 연결되어 의존성 객체를 생성
+ 생성 후 Scope에 따라 관리도 한다.
+ `@Provide` : `@Module`클래스 안에 선언된 메소드에 붙인다.
+ Module클래스는 의존성 주입에 필요한 객체들을 Provide 메소드를 통해 관리한다.

### Scope
+ 생성된 객체의 Lifecycle 범위
+ 안드로이드에서는 주로 PerActivity, PerFragment 등으로 화면의 생명주기와 맞추어 사용한다.
+ Module에서 Scope를 보고 객체를 관리

### Dagger가 의존성을 주입하는 Flow

```

@Inject -> Subcomponent -> Module -> Scope에 있으면 return 없으면 생성

Subcomponent Module에서 맞는 타입을 못찾으면 상위 Component -> Module -> Scope에 있으면 return 없으면 생성

```