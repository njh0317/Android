# Permission

## AndroidManifest.xml에 명세
+ \<user-permission/> 태그를 사용해서 권한을 추가한다.
    ```xml
    <uses-permission android:name="android.permission.CAMERA"/>
    ```
## 소스코드에서 위험 권한 처리하기
### 1단계 : 권한에 대한 사용자 승인 확인
설정 파일에서 명세한 카메라 권한에 대해서 승인 처리가 되었는지 확인한다.

01. 카메라 권한의 승인 상태를 먼저 확인 한 다음 결괏값을 cameraPermission 변수에 저장한다.
+ 권한은 모두 Manifest(android) 클래스에 문자열 상수로 정의되어 있다.
    ```kotlin
    val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
    ```

02. cameraPermission에 저장된 값이 승인 되었는지 아닌지 확인하고, 값에 따라 다른 메서드를 호출한다.
    ```kotlin
    if(cameraPermission == PackageManager.PERMISSION_GRANTED){
        //승인된 경우
        //프로그램 진행 메서드
        startProcess()
    }
    else{
        //미승인
        //권한 요청 메서드
        requestPermission()
    }
    ```
### 2단계 : 사용자에게 승인 요청
`ActivityCompat.requestPermissions()`를 호출하면 사용자에게 권한을 요청하는 팝업창이 뜨고 승인을 처리하면 `onRequestPermissionResult()` 메서드를 호출한다.
+ requestPermission()이 호출 된 경우

01. ActivityCompat.requestPermissions() 메서드를 호출한다. 
+ 두 번째 파라미터는 배열(권한이 복수 개일 때 대비)
+ 세 번째 파라미터는 request code, 권한을 요청한 주체가 어떤 것인지 구분하기 위해 코드를 숫자로 입력 
    ```kotlin
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 99)
    ```

### 3단계 : 사용자 승인 후 처리
권한 승인을 묻는 팝업창에 사용자가 DENY(거절) 또는 ALLOW(수락)을 클릭하면 액티비티의 onRequestPermissionResult() 메서드가 호출된다.

01. onRequestPermissionResult() 메서드 오버라이드
    
    ```kotlin
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    ```

    1. requestCode : 요청한 주체를 확인하는 코드, requestPermissions() 메서드의 세 번째 파라미터로 전달
    2. permissions : 요청한 권한 목록. requestPermissions() 메서드의 두 번째 파라미터로 전달
    3. grantResults : 권한 목록에 대한 승인/미승인 값. 권한 목록의 개수와 같은 수의 결괏값이 전달
02. requestCode가 요청 시에 입력했던 99인지 확인하는 코드를 작성하고, 권한 결괏값을 체크해서 승인 여부를 체크하고 승인이면 startProcess() 메서드를 실행하고, 미승인이면 앱을 종료한다.
    ```kotlin
    when(requestCode) {
        99 -> {
            //권한 결괏값을 확인 후 실행 내용 결정
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) startProcess()
            else finish()
        }
    }
    ```