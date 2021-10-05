# 12949 - 행렬의 곱셈

## Algorithm

반복문

## Description

### mutablelist -> Array
  ```kotlin
  var answer = mutableListOf<IntArray>()
  answer.toList().toTypedArray() //Array
  ```
  + Array<IntArray> 형태는 새로운 array 추가가 힘들어 mutableListOf로 수정했다. 근데 문제는 다시 Array 로 변환하는 것이 힘들었다.
  + toList() 로 List로 변환한 뒤, toTypeArray() 를 써주니 Array 로 변환 완료

### Array 초기화
+ Array 초기화 방법
  ```kotlin
  var newarr = Array<Int>(size){i -> 0}
  ```
  + size 만큼 0 으로 초기화

### Array for 문
+ indice
  ```kotlin
  for(k in arr1[i].indices){
      //원하는 로직
  }
  
  ```
  + indice 를 사용하면 array 의 index 만큼 반복한다.
  
## Review

코틀린 공부할 겸.. 지난번에 풀었던 문제 복습 !
