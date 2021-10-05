# 81301 - 숫자 문자열과 영단어

## Algorithm

문자열

## Description

### map
+ 선언
  ```kotlin
  val number = mapOf("zero" to "0", "one" to "1", "two" to "2",
        "three" to "3", "four" to "4", "five" to "5",
        "six" to "6", "seven" to "7", "eight" to "8",
        "nine" to "9")
  ```
+ key 만 for 문

  ```kotlin
  number.keys.forEach { k ->
      //조건
  }
  ```

### 문자열
+ 문자열 안에 특정 문자열 존재 여부 확인
  ```
  if(s.contains(k)) //true, false
  ```

+ 문자열 대체 
  ```kotlin
  replace_string = origin_string.replace(찾는 문자열, 대체할 문자열)
  ```
  + 파이썬에서는 찾는 문자열이 여러개 있더라도 하나씩 되는데 코틀린에서는 한번에 다 대체된다 (ex. oneoneone4 -> 1114) 아주 편하다
## Review

코틀린 공부할 겸.. 지난번에 풀었던 문제 복습 !
