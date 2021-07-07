# SharedPreference
안드로이드에서는 간단한 데이터의 저장을 목적으로 SharedPreferences를 제공
+ SharedPreferences는 내부 저장소를 이용하기 때문에 권한 설정이 필요 없고 훨씬 간단한 코드로 사용할 수 있다.

## 값 저장하기
1. SharedPreference 생성

    **getSharedPreferences(이름, 모드)**
    + 이름 : 입력된 데이터가 저장될 파일 명
    + 모드 : 파일 접근 권한(API LEVEL 17부터 보안상의 이유로 MODE_PRIVATE 만 사용됨)
    ```kotlin
    val shared = getSharedPreferences("이름", Context.MODE_PRIVATE)
    ```
    **getPreferences()**
    + 개별 액티비티에서 사용하거나 액티비티가 하나 밖에 없는 경우 사용
    + 호출하는 액티비티의 이름으로 저장 파일이 생성
    ```kotlin
    val preference = getPreferences(Context.MODE_PRIVATE)
    ```

2. Editor 꺼내기

    Editor 인터페이스는 edit() 메서드를 호출해서 사용할 수 있다.
    ```kotlin
    val editor = shared.edit()
    ```

3. putInt().putString() 등 메서드로 저장하기

    입력될 값의 타입에 맞는 Editor의 메서드를 사용해서 저장할 수 있다.
    ```kotlin
    editor.putString("키","값")
    ```

4. apply()로 파일에 반영하기
    ```kotlin
    editor.apply()
    ```

## 값 읽기
데이터를 저장할 떄와 다르게 중간에 Editor를 사용하는 단계가 없으며, SharedPreferences의 메서드를 직접 호출해서 데이터를 불러온다.

1. SharedPreference 생성하기
    ```kotlin
    val shared = getSharedPreferences("이름", Context.MODE_PRIVATE)
    ```
2. getInt(), getString() 등 메서드로 값 읽어오기 
+ defaultValue를 지정하면 해당 키의 데이터가 없으면 지정한 기본 값을 반환한다.

    ```kotlin
    shared.getString("키", "기본값")
    ```

## 이 외 메서드
+ remove(String key) : 해당 키의 데이터 삭제
+ clear() : 모든 데이터를 삭제
+ apply() : 변경한 업데이트를 파일에 비동기적으로 저장
+ commit() : 변경한 업데이트를 파일에 동기적으로 저장. 동기 작업이므로 UI 스레드에서 호출하는 것을 피해야 한다.

*참고 : commit()은 메인 스레드(UI 스레드)를 사용하기 때문에 짧은 순간이지만 화면이 멈출 수 있다. 따라서 특별한 경우가 아니면 항상 apply() 메서드를 사용하는 것이 좋다.