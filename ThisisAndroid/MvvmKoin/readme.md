#Koin

### Koin 에서 사용할 수 있는 DSL(Domain Specific Language)

+ module - create a Koin Module or a submodule(inside a module)
+ factory - inject 할 때마다 인스턴스 생성
+ single - 싱글톤으로 생성
+ get() - resolve a component dependency
+ named() - define a qualifier with type, enum or string
+ bind() - additional Kotlin type binding for given bean definition
+ binds() - list of addition Kotlin types binding for given bean definition
+ getProperty() - resolve a Koin property