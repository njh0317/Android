# Coroutine
`Coroutine`은 안드로이드에서 백그라운드 스레드에서 코드를 처리할 때 사용하는 하나의 방법이다.
+ 예를들어 1. 네트워크 리퀘스트, 2. 내부 저장소 접근 과 같은 경우에 사용한다.

## Coroutine - Thread 차이
**코루틴**이 하나의 실행-종료되어야 하는 일(Job)이라고 한다면 **쓰레드**는 그 일이 실행되는 곳

+ 따라서 하나의 쓰레드에 여러 개의 코루틴이 동시에 실행될 수 있다.
+ 코루틴 1이 작업을 하는 도중 코루틴 2로 코드를 넘겨도 코루틴 1만 잠시 멈추고, 공간을 제공한 스레드는 계속 움직인다.
    + 스레드를 이용해 처리한다면 1번에 해당하는 스레드가 잠시 멈추고 2번 스레드가 처리 하도록 우선순위를 넘겨야만 가능하다. (성능 저하)


## 코루틴 설정
+ build.gradle 파일에 의존성 추가
    ```xml
    dependencies {
        ...
        implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:version"
    }
    ```

## 코루틴 스코프
코루틴은 코루틴 스코프라 불리는 스코프 안에서 동작한다.

**글로벌 스코프** : 앱의 생명 주기와 함께 동작, 별도의 생명주기 관리가 필요 없다. 앱의 시작부터 종료될 때까지 혹은 장시간 실행되어야 하는 코루틴이 있다면 GlobalScope를 사용한다.

```kotlin
GlobalScope.launch {

}

```

**코루틴 스코프** : 필요할 때만 열고 완료되면 닫는경우 CoroutineScope를 사용한다.
```kotlin
binding.btnDownload.setOnClickListener {
    CoroutineScope(Dispatchers.IO).launch {
        //여기서 이미지를 불러오는 등의 코드 처리
    }
}
```

### 디스패처의 종류
CoroutineScope의 경우 코루틴이 실행될 스레드를 지정하기 위해 디스패처를 지정하야 한다.
+ Dispatchers.Default : CPU를 많이 사용하는 작업을 백그라운드 스레드에서 실행하도록 최적화되어 있는 디스패처, 안드로이드의 기본 스레드풀(Thread Pool)을 사용한다.
+ Dispatchers.IO : 이미지 다운로드, 파일 입출력 등의 입출력에 최적화되어 있는 디스패처
+ Dispatchers.Main : 안드로이드의 기본 스레드에서 코루틴을 실행하고 UI와 상호작용에 최적화되어 있는 디스패처, 예를 들어 텍스트 뷰에 글자를 입력해야 할 경우 Main 사용
+ Dispatchers.Unconfined

*보통 IO와 Main을 조합해서 사용

### withContext로 디스패처 분리
suspend 함수를 코루틴 스코프에서 호출할 경우 호출한 스코프와 다른 디스패처를 사용해야할 때 withContext를 사용해 디스패처를 변경한다.

```kotlin
suspend fun readFile(): String {
    return "파일 내용"
}
CoroutineScope(Dispatchers.Main).launch {
    //화면 처리
    val result = withContext(Dispatchers.IO) {
        readFile()
    }
    Log.d("코루틴", "파일 결과 = $result")
}
```

## launch와 상태 관리
+ launch와 async로 시작
    + launch : 상태 관리
    + async : 상태를 관리하고 연산 결과를 반환 받음
+ launch : 호출하는 것만으로 코루틴을 생성할 수 있고, 반환되는 잡(Job)을 변수에 저장해두고 상태 관리용으로 사용할 수 있다.
### cancel
+ 코루틴의 동작을 멈추는 상태 관리 메서드
+ 하나의 스코프 안에 여러 개의 코루틴이 있다면 하위의 코루틴도 모두 동작을 멈춘다.

    ```kotlin
    val job = CoroutineScope(Dispatchers.Default).launch {
        val job = launch {
            for (i in 0..10){
                delay(500)
                Log.d("")
            }
        }
    }

    binding.btnStop.setOnClickListener {
        job.cancel()
    }
    ```
### join
+ 코루틴의 동작을 순차적으로 실행할 수 있도록 한다.
+ 다음의 예시는 스코프 안에 2개의 코루틴이 launch로 사용되었는데, join() 메서드로 인해 앞의 코루틴 실행이 완료된 후에 두 번째 코루틴이 실행된다.
    ```kotlin
    CoroutineScope(Dispatchers.Default).launch(){
        launch {
            for(i in 0..5) {
                delay(500)
                Log()
            }
        }.join()
        launch{
            for(i in 0..5){
                delay(500)
                Log()
            }
        }
    }
    ```

## async와 반환값 처리
+ async는 스코프의 연산 결과를 받아서 사용할 수 있다.
+ 예를 들어 시간이 오래 걸리는 2개의 코루틴을 async로 선언하고, 결괏값을 처리하는 곳에서 await 함수를 사용하면 결과 처리가 완료된 후에 await 를 호출한 줄의 코드가 실행

    ```kotlin
    CoroutineScope(Dispatchers.Default).async {
        val deferred1 = async {
            delay(500)
            350
        }
        val deferred2 = async {
            delay(500)
            200
        }
        //deferred1.await(), deferred2.await() 으로 실행이 완료된 후에 값 사용 가능
    }
    ```

## suspend
+ 코루틴 안에서 suspend 키워드로 선언된 함수가 호출되면 이전까지의 코드 실행이 멈추고, suspend 함수의 처리가 완료된 후에 멈춰 있던 원래 스코프의 다음 코드가 실행 된다.

    ```kotlin
    suspend fun subRoutine() {
        for(i in 0..10) {

        }
    }
    CoroutineScope(Dispatchers.Main).launch {
        //코드1
        subRoutine()
        //코드2
    }
    ```
    + suspend 키워드를 붙였기 때문에 CoroutineScope 안에서 자동으로 백그라운드 스레드처럼 동작
    + 함수를 호출하면서 호출한 측의 코드를 잠시 멈췄지만 스레드의 중단은 없다.
    + 