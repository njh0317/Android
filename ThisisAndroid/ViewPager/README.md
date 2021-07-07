# ViewPager
탭 메뉴로 화면 구성하기

안드로이드에서는 스와이프로 화면을 전환할 수 있도록 컨테이너인 뷰페이저(ViewPager)를 제공하고, 탭 메뉴 구성을 위해서는 탭 레이아웃(TabLayout)을 제공한다.

## 뷰페이저에서 프래그먼트 사용
### 뷰페이저와 어댑터생성
+ 페이지 어댑터(PagerAdapter)를 통해 뷰페이저에서 보일 화면들을 연결

1. Activity에 ViewPager2 추가하고, 상하좌우 컨스트레인트를 화면 가장자리로
2. 프래그먼트 어댑터 생성
    1. FragmentAdapter 클래스를 생성하고, FragmentStateAdapter를 상속받는다.
        ```kotlin
        class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        ```
    2. 보여줄 프래그먼트 리스트인 fragmentList 선언
        ```kotlin
        var fragmentList = listOf<Fragment>()
        ```
    3. FragmentStateAdapter의 필수 메서드 구현
        + createFragment() : 현재 페이지의 position이 파라미터로 넘어온다. position에 해당하는 위치의 프래그먼트를 만들어서 안드로이드에 반환한다.
        + getItemCount() : 어댑터가 화면에 보여줄 전체 프래그먼트 개수를 반환
        ```kotlin
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList.get(position)
        }
        ```
### MainActivity에서 연결
프래그먼트와 어댑터를 MainActivity의 소스 코드에서 연결한다.

1. 생성할 프래그먼트 목록 코드를 추가
    ```kotlin
    val fragmentList = listOf(FragmentA(), FragmentB(), FragmentC(), FragmentD())
    ```

2. 어댑터를 생성하고, 앞에서 생성해둔 프래그먼트 목록을 저장한다.
    ```kotlin
    val adapter = FragmentAdapter(this)
    adapter.fragmentList = fragmentList
    ```
3. 레이아웃의 viewPager를 import 하고 어댑터를 적용한다.
    ```kotlin
    binding.viewPager.adapter = adapter
    ```

### 탭 레이아웃 적용
1. main.xml에 TabLayout 추가
2. TabLayout과 뷰페이저 연결
ViewPager2에서는 TabLayoutMediator를 사용해서 연결한다.
    ```kotlin
    //tab
    val tabTitles = listOf<String>("A", "B", "C", "D")
    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab: TabLayout.Tab, i: Int ->
        tab.text = tabTitles[i]
    }.attach()
    ```