# 프래그먼트 간 데이터 전달

## Fragment Listener
+ fragment 버전 1.3.x 부터 fragment간 통신을 위해 Fragment Listener 기능 제공 

01. build.grade 파일에 dependency 추가
    ```
    def fragment_version = "1.3.0-beta02"
    //자바용 fragment 1.3.0
    implementation "androidx.fragment:fragment:$fragment_version"
    //코틀린용 fragment 1.3.0
    implementation "androidx.fragment:fragment-ktx:$fragment_version"
    ```
    + fragment 버전은 https://developer.android.com/jetpack/androidx/releases/fragment 에서 확인

02. sender

    01. onViewCreated를 override한다.
    02. bundle 객체를 선언하고 전달하고자 하는 값을 key, value로 설정한다
        ```kotlin
        val bundle = bundleOf("valueKey" to "YES")
        ```
    
    03. setFragmentResult 메서드를 key, bundle 형태로 설정
        ```kotlin
        setFragmentResult("request", bundle)
        ```
03. receiver

    01. onViewCreated를 override한다.
    02. setFragmentResultListener("request") 를 사용해 key에 해당하는 요청을 받는다.
    + 값을 보내는 측 프래그먼트에서 "request" 라는 키로 값을 보내면 이 리스너 안의 코드가 실행된다.
        ```kotlin
        setFragmentResultListener("request") { key, bundle ->
            //let을 사용해서 꺼낸 값이 있을 때만 화면에 값을 세팅한다.
            //"request"는 요청 전체에 대한 키이고
            //"valueKey"는 요청에 담겨 있는 여러 개의 값 중 하나의 값을 가리키는 키
            bundle.getString("valueKey")?.let{
                binding.textView.setText(it)
            }
        }
        ```

        

