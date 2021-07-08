# Room

## ORM(Object-Relational Mapping)
객체(Class)와 관계형 데이터베이스의 데이터(Table)을 매핑하고 변환하는 기술
+ 복잡한 쿼리를 잘 몰라도 코드만으로 데이터베이스를 컨트롤

## Room 추가하기
1. build.gradle 파일에 plugins 블록에 kotlin-kapt 명시
2. dependencies 블록에 Room 추가
+ kapt - Pluggable Annotation Processing API를 Kotlin에서도 사용 가능하게 하는 것
    ```xml
    def room_version = "2.2.6"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    ```

## RoomMemo 클래스 정의
1. RoomMemo클래스를 생성하고, @Entity 어노테이션을 class RoomMemo 위에 작성
+ Room 라이브러리는 @Entity 어노테이션이 적용된 클래스를 찾아 테이블로 변환한다.
+ 데이터베이스에서 테이블명을 클래스명과 다르게 하고 싶을 때는 @Entity(tableName = "테이블명")과 같이 작성한다.

2. 멤버 변수를 선언하고 변수명 위에 @ColumnInfo 어노테이션을 작성해서 테이블의 컬럼으로 사용된다는 것을 명시한다.
+ PrimaryKey 어노테이션을 사용해서 키를 명시, 자동증가 옵션(autoGenerate=true) 추가
+ constructor 작성

```kotlin
@Entity(tableName = "room_memo")
class RoomMemo {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo
    var datetime: Long = 0

    constructor(content: String, datetime: Long) {
        this.content = content
        this.datetime = datetime
    }
}
```

## RoomMemoDAO 인터페이스 정의
### DAO(Data Access Object)
데이터베이스에 접근해서 DML 쿼리(SELECT, INSERT, UPDATE, DELETE)를 실행하는 메서드의 모음
1. RoomMemoDAO 인터페이스 생성
2. @Dao 어노테이션을 이용해 Dao 라는 것을 명시
3. 삽입, 조회, 수정, 삭제에 해당하는 메서드를 만들고 각각의 어노테이션을 붙여준다.

```kotlin
@Dao
interface RoomMemoDao {
    @Query("select * from room_memo")
    fun getAll(): List<RoomMemo>

    @Insert(onConflict = REPLACE)
    fun insert(memo: RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)
}
```
+ onConflict = REPLACE : 동일한 키를 가진 값이 입력되었을 때 UPDATE 쿼리로 실행된다.
+ select 쿼리는 직접 작성

## RoomHelper 클래스 정의
+ RoomDatabase를 상속받아 클래스를 생성한다.
+ 추상 클래스로 생성해야 한다. 기존 클래스와 동일하게 생성하고 class 앞에 abstract 키워드를 붙이면 추상 클래스가 된다.

1. RoomHelper 클래스를 생성하고 abstract 키워드를 붙여서 추상 클래스로 만든다.
2. 클래스 명 위에 @Database 어노테이션을 작성한다.
    + entities : Room 라이브러리가 사용할 엔티티(테이블) 클래스 목록
    + version : 데이터베이스의 버전
    + exportSchema : true면 스키마 정보를 파일로 출력

3. RoomHelper 클래스 안에 앞에서 정의한 RoomMemoDao 인터페이스의 구현체를 사용할 수 있는 메서드명을 정의한다.
```kotlin
@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun roomMemoDao(): RoomMemoDao

}
```

## RoomHelper 사용하기
1. RoomHelper 변수를 선언한다.
    ```kotlin
    var helper : RoomHelper? = null
    ```
2. onCreate()에서 helper를 생성하는 부분을 추가한다.
+ databaseBuilder() 메서드의 세 번째 파라미터가 실제 생성되는 DB 파일의 이름
+ Room 은 기본적으로 서브 스레드에서 동작하도록 설계, allowMainThreadQueries()를 사용하면 메인에서 실행
    ```kotlin
    helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_memo")
        .allowMainThreadQueries()
        .build()
    ```
3. 쿼리 호출
    ```kotlin
    helper?.roomMemoDao()?.getAll()
    helper?.roomMemoDao()?.insert(memo)
    ```