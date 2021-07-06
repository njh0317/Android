# Fragment
**프래그먼트(Fragment)** : 화면을 분할해서 독립적인 코드로 구성할 수 있게 도와주는 것

## 액티비티에 프래그먼트 추가하기
액티비티에 프래그먼트를 추가하는 방법에는 두 가지가 있다.
1. 레이아웃 카테고리/프레임 레이아웃
    + 화면 전환이 필요할 경우 사용하면 좋다.
2. 컨테이너 카테고리/\<fragment> 
    + 화면 전환 없이 프래그먼트 하나만 화면에 표시할 때 사용

### 순서
1. activity_main.xml(프래그먼트를 추가해 줄 액티비티 xml파일)을 열고 위의 두 가지중 하나를 추가해서 배치(프래그먼트가 보일 위치)

2. Activity에서 추가하는 방법(프레임 레이아웃을 쓴 경우)
    + 프래그먼트를 삽입하기 위해서는 프래그먼트 매니저를 통해 삽입할 레이아웃의 id를 지정한다.
    + 삽입하는 과정은 하나의 트랜잭션으로 관리되기 때문에 트랜잭션 매니저를 통해  `begin transaction > add fragment > commit transaction` 순서로 처리

    1. fragment 객체 생성
        ```kotlin
        listFragment = ListFragment()
        val transaction = supportFragmentManager.
        ```
    2. 액티비티가 가지고 있는 프래그먼트 매니저를 통해 트랜잭션 시작, 시작한 트랜잭션을 변수에 저장
        ```kotlin
        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.frameLayout, listFragment)
        transaction.commit()
        ```
    3. 트랜잭션의 add 메서드로 frameLayout을 id로 가지고 있는 레이아웃에 앞에서 생성한 listFragment를 삽입, 커밋
        ```kotlin
        transaction.add(R.id.frameLayout, listFragment)
        //replace(레이아웃, 프래그먼트) : 레이아웃에 삽입되어 있는 프래그먼트와 교체
        //remove(프래그먼트) : 지정한 프래그먼트 제거
        transaction.commit()
        ```
3. 레이아웃에서 Fragment 추가(\<fragment> 사용)
    1. 컨테이너 카테고리의 \<fragment>를 화면에 가져다 놓으면 해당 태그에 삽입할 클래스 선택 팝업창이 뜬다.


## 프래그먼트 화면 전환
1. MainActivity에 1) 다른 프래그먼트를 호출하고, 2) 다시 돌아오는 함수를 선언한다.
    + 다른 프래그먼트 호출 함수에서 add()와 commit()사이에 addToBackStack() 을 추가하면 뒤로가기 버튼을 사용 가능하다.

    + 돌아오기 함수
        ```kotlin
        fun goBack(){
            onBackPressed()
        }
        ```

2. 각 Fragment에서 화면 변경 event가 일어나면 MainActivity에 있는 함수를 호출함으로써 화면을 전환한다.
    + Fragment에서 MainActivity의 메소드 사용
        ```kotlin
        lateinit var mainActivity:MainActivity
        ...
        override fun onAttach(context: Context) {
            super.onAttach(context)
            mainActivity = context as MainActivity
        }
        ```

## 액티비티에서 프래그먼트로 값 전달하기
### 프래그먼트 생성 시 값 전달하기
1. 액티비티 - 프래그먼트 생성시 bundle에 전달할 값 저장
    ```kotlin
    listFragment = ListFragment()
    var bundle = Bundle()
    bundle.putString("key1", "List Fragment")
    bundle.putInt("key2", 20210101)

    listFragment.arguments = bundle
    //transaction
    ```
2. 프래그먼트 - 전달받은 값은 arguments에서 직접 꺼낸다.
    ```kotlin
    binding.textTitle.text = arguments?.getString("key1")
    binding.textValue.text = "${arguments?.getInt("key2")}"
    ```
### 이미 생성되어 있는 프래그먼트에 값 전달하기
1. 프래그먼트 - 액티비티로 전달받을 문자열을 처리할 메서드를 선언
    ```kotlin
    fun setValue(value: String){
        binding.textFromActivity.text = value
    }
    ```
2. Activity - 프래그먼트 객체를 이용해 프래그먼트 내 메서드를 사용한다.
    ```kotlin
    lateinit var listFragment:ListFragment
    ...
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()//프래그먼트 초기화

        binding.btnSend.setOnClickListener {
            listFragment.setValue("전달할 값") //함수 사용
        }

    }
    ```