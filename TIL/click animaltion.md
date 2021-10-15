# Click Animation

버튼과 같은 view 가 클릭됐을 때 클릭됐는지 아닌지 여부에 따라 색을 변경하는 방법

1. 'color' resource directory 를 만들고 파일 생성

+ ex) color/button_text_color.xml

2. 파일에 state_pressed가 true일 경우와 false 일 경우에 색을 정의한다.

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <selector
        xmlns:android="http://schemas.android.com/apk/res/android">
        <item
            android:state_pressed="true"
            android:color="#66000000" />
        <item
            android:state_pressed="false"
            android:color="#FFFFFF" />
    </selector>

    ```

3. view 요소의 color에 해당 파일 지정

    ```xml
    android:textColor="@color/button_text_color"
    ```