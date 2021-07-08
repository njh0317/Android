# SQLite
+ 관계형 데이터베이스(데이터의 저장 형태와 관계를 정의하는데 컬럼과 로우가 있는 테이블)

## SQLite
### 테이블 생성
```
CREATE TABLE 테이블명 (
    [컬럼명1] [타입] [옵션],
    [컬럼명2] [타입] [옵션],
    [컬럼명3] [타입] [옵션]
)
```

**타입**
+ INTEGER
+ TEXT
+ REAL : 소수점이 있는 숫자형
+ BLOB
+ NUMERIC

**옵션**
+ PRIMARY KEY
    + SQLite에서는 PRIMARY KEY의 타입이 INTEGER일 때, 숫자를 자동으로 증가시킨다.

### SQLiteOpenHelper 사용
SQLite를 사용하기 위해서는 안드로이드의 컨텍스트가 가지고 있는 1. createDatabase() 메서드를 사용하거나, 2. SQLiteOpenHelper 클래스를 상속받아 사용한다.

+ SQLiteOpenHelper 클래스를 상속받는 것이 사용성이 좋고 쉽다.

+ **SQLiteOpenHelper** 클래스는 데이터베이스를 파일로 생성하고 코틀린 코드에서 사용할 수 있도록 데이터베이스와 연결하는 역할

1. SQliteHelper클래스를 생성하고, SQLiteOpenHelper 클래스를 상속 받는다.
+ SQLiteOpenHelper는 생성시에 Context, 데이터베이스명, 팩토리, 버전 정보가 필요하다.
    ```kotlin
    class SqliteHelper(context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version){
        //팩토리는 사용안하므로 생략
    }
    ```

2. 필수 구현 메서드 2가지
    1. `onCreate(db: SQLiteDatabase!): Unit`
    + 테이블 생성
    + 데이터베이스가 생성되어 있으면 더 이상 실행되지 않는다.
        ```kotlin
        override fun onCreate(db: SQLiteDatabase?) {
            val create = "CREATE TABLE MEMO (" +
                    "no integer primary key, "+
                    "content text, "+
                    "datetime integer" +
                    ")"

            db?.execSQL(create)
        }
        ```
    2. `onUpgrade(db: SQLiteDatabase!, oldVersion: Int, newVersion: Int): Unit`
    + SqliteHelper에 전달되는 버전 정보가 변경되었을 때 현재 생성되어 있는 데이터베이스의 버전과 비교해서 더 높으면 호출된다. 버전 변경 사항이 없으면 호출되지 않는다.

3. DML 관련 메서드 구현
    1. 삽입 메서드
    + SQLiteOpenHelper를 이용해서 값을 입력할 때는 코틀린의 Map 클래스처럼 키, 값 형태로 사용되는 **`ContentValues`** 클래스를 사용한다. 
    + 이미 구현된 writableDatabase에 테이블 명과 함께 앞에서 작성한 값을 전달해서 insert() 하고, 사용 후에는 close()를 호출해야 한다.
        ```kotlin
        fun insertMemo(memo: Memo){
            val values = ContentValues()
            values.put("content", memo.content)
            values.put("datetime", memo.datetime)

            val wd = writableDatabase
            wd.insert("memo", null, values)
            wd.close()
        }
        ```

    2. 조회 메서드
    + 쿼리를 작성
    + 읽기 전용 데이터베이스를 변수에 담는다.
    + 데이터베이스의 rawQuery()메서드에 앞아서 작성해둔 쿼리를 담아 실행하면 커서(cursor) 형태로 값이 반환된다.
        + 커서의 moveToNext() 메서드가 실행되면 다음 줄에 사용할 수 있는 레코드가 있는지 여부를 반환하고, 해당 커서를 다음 위치로 이동시킨다.
        ```kotlin
        fun selectMemo():MutableList<Memo>{
            val list = mutableListOf<Memo>()
            val select = "select * from memo"
            val rd = readableDatabase
            val cursor = rd.rawQuery(select, null)

            while(cursor.moveToNext()){
                val no = cursor.getLong(cursor.getColumnIndex("no"))
                val content = cursor.getString(cursor.getColumnIndex("content"))
                val datetime = cursor.getLong(cursor.getColumnIndex("datetime"))
                list.add(Memo(no, content, datetime))
            }
            cursor.close()
            rd.close()
            return list
        }

        ```

    3. 삭제 메서드
    + 조회와 동일하게 쿼리를 생성하고 rawQuery()를 이용해서 삭제할 수 있다.
        ```kotlin
        fun deleteMemo(memo: Memo){
            val delete = "delete from memo where no = ${memo.no}"

            val db = writableDatabase
            db.execSQL(delete)
            db.close()
        }
        ```


    4. 수정 메서드
    + Insert와 동일하게 ContentValues를 사용해서 수정할 값 저장
    + writableDatabase의 update() 메서드를 사용해서 수정한 다음 close()
        + update(테이블 명, 수정할 값, 수정할 조건, null)
        + 수정할 조건은 `PRIMARY KEY`로 지정된 컬럼을 사용
    
        ```kotlin
        fun updateMemo(memo: Memo){
            val values = ContentValues()
            values.put("content", memo.content)
            values.put("datetime", memo.datetime)

            val wd = writableDatabase
            wd.update("memo", values, "no = ${memo.no}", null)
            // 세 번째 값을 "no = ?"의 형태로 입력하고 네 번째에 ?에 매핑할 값을 arrayOf("{$memo.no}")의 형태로 전달 가능
            wd.close()
        }
        ```