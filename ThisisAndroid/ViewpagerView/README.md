# ViewPager View
뷰를 사용하는 뷰페이저 만들기 
+ 프래그먼트를 사용해 뷰 페이저를 구현할 경우에는 각각의 화면들이 독립적으로 구성될 필요가 있을 때
+ 리사이클러뷰에서 하나의 아이템 레이아웃을 사용해서 반복적으로 동일한 구조의 텍스트나 이미지를 보여주는 용도라면 프래그먼트보다 뷰를 사용한다.

1. 아이템 레이아웃 만들기
    + 프래그먼트와는 다르게 레이아웃 파일만 생성
2. Custom Pager Adapter 만들기
    1. CustomPagerAdapter 클래스 생성
    2. RecyclerView.ViewHolder를 상속받는 Holder 클래스를 파일 아래쪽에 만든다.
    + Holder 클래스의 binding 파라미터로 onCreateViewHolder에서 생성할 바인딩이 전달된다.
    + ViewHolder 클래스의 생성자에는 binding.root 를 전달한다.
        ```kotlin
        class Holder(val binding: ItemViewpagerBinding):RecyclerView.ViewHolder(binding.root){
            fun setText(text: String) { binding.textView.text = text }
        }
        ```
    3. CustomPagerAdapter 에서 RecyclerView.Adapter를 상속받고 Holder 제네릭으로 클래스를 지정
        ```kotlin
        class CustomPagerAdapter: RecyclerView.Adapter<Holder>() {
            ...
        }
        ```
    4. Adapter의 필수 메서드 구현
    + onCreateViewHolder()에서는 바인딩을 생성한 후 Holder에 전달
    + onBindViewHolder()에서 Holder에 만들어둔 setText 메서드를 호출해서 화면에 출력
        ```kotlin
        class CustomPagerAdapter: RecyclerView.Adapter<Holder>() {
            var textList = listOf<String>()
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
                val binding = ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Holder(binding)
            }

            override fun onBindViewHolder(holder: Holder, position: Int) {
                val text = textList[position]
                holder.setText(text)
            }

            override fun getItemCount(): Int {
                return textList.size
            }
        }

        ```
3. 레이아웃 파일에 ViewPager와 TabLayout 추가
4. MainActivity 소스 코드 연결
    ```kotlin
    val adapter = CustomPagerAdapter()
    adapter.textList = textList
    binding.viewPager.adapter = adapter

    val tabTitles = listOf("View A", "View B", "View C", "View D")

    //TabLayoutMediator를 사용해서 탭 레이아웃과 뷰페이저 연결
    TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
        tab.text = tabTitles[position]

    }.attach()
    ```