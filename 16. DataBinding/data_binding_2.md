# DataBinding

## 다양한 타입의 DataBinding
모델 클래스(POJO), 리소스, 컬렉션 타입(배열, 리스트, 맵 등) 등의 데이터를 바인딩할 수 있다.

✏️ 예시  
데이터 클래스
```kotlin
data class Person(
    val firstName: String,
    val lastName: String,
    val phone: String
)
```

XML 파일
```xml
<?xml version="1.0" encoding="utf-8"?><!-- 데이터 바인딩을 사용할 XML은 layout 태그로 시작 -->
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <!-- XML내에서 사용할 변수 선언 -->
        <!-- 패키지명을 포함한 클래스의 경로를 type으로 설정 -->
        <!-- name으로 선언된 이름을 XML내에서 사용 -->
        <variable
            name="person"
            type="kr.co.lee.databindingexample.Person" />
    </data>

    <androidx.appcompat.widget.LinearLayoutCompat
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <!-- @{}는 뷰에 데이터 바인딩을 사용하는 코드 -->
        <!-- person이라고 선언된 변수의 프로퍼티를 가져와 셋팅 -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{person.firstName}" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{person.lastName}" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{person.phone}" />

    </androidx.appcompat.widget.LinearLayoutCompat>
</layout>
```
View 에서 DataBinding 의 기본 코드는 `@{}` 코드 이다.  
`@{}`코드에 의해 모델 객체가 이용되며 **getter -> public 필드 -> 함수명** 순으로 판단하여 데이터를 가져온다.  
>✏️ `@{person.firstName}` 을 선언 했다면 모델 클래스에서 `getFirstName()` 함수가 호출 되고, 선언되어 있지 않다면 `public String name`필드에 접근한다. 만약 게터 함수와 public 필드가 없다면 firstName() 함수를 호출하고, 이 함수마저 없다면 **에러가 발생**한다.

Activity
```kotlin
class ExampleActivity : AppCompatActivity() {
    lateinit var binding: ActivityExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding 레이아웃 지정
        binding = DataBindingUtil.setContentView(this, R.layout.activity_example)

        // 모델 클래스 생성 및 초기화
        val examplePerson = Person("홍", "길동", "01000000000")
        // XML에 선언된 variable(person)에 값 셋팅
        binding.person = examplePerson
    }
}
```

XML 에 선언된 변수(`person`) 의 setter 를 호출하여 데이터만 넘기면 데이터가 XML 에 바인딩 되는 구조이다.


### ViewModel + LiveData + DataBinding 구조
ViewModel
```kotlin
class ExampleViewModel: ViewModel() {
	private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> = _person
    
    fun setPerson() {
    	// Data 영역(내부 DB, 서버) 등에서 이름 획득...
        val personItem = Person(얻어온 정보를 사용하여 객체 생성)
        
    	_person.value = personItem
    }
}
```

Activity
```kotlin
class ExampleActivity : AppCompatActivity() {
    lateinit var binding: ActivityExampleBinding
    // Activity-KTX 사용하여 ViewModel 초기화
	val viewModel: ExampleVieModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding 레이아웃 지정
        binding = DataBindingUtil.setContentView(this, R.layout.activity_example)
        binding.apply {
        	// LiveData를 DataBinding에서 사용한다면 다음과 같이 설정하여 
            // LiveData의 LifeCycleOwner가 현재 activity라는 것을 명시해야함
        	lifecycleOwner = this@ExampleActivity
            vm = viewModel
        }
    }
}
```

XML
```xml
<?xml version="1.0" encoding="utf-8"?><!-- 데이터 바인딩을 사용할 XML은 layout 태그로 시작 -->
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>
        <!-- XML내에서 사용할 변수 선언 -->
        <!-- 패키지명을 포함한 클래스의 경로를 type으로 설정 -->
        <!-- name으로 선언된 이름을 XML내에서 사용 -->
        <variable
            name="vm"
            type="kr.co.lee.databindingexample.ViewModel" />
    </data>

    <androidx.appcompat.widget.LinearLayoutCompat
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <!-- @{}는 뷰에 데이터 바인딩을 사용하는 코드 -->
        <!-- person이라고 선언된 변수의 프로퍼티를 가져와 셋팅 -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{vm.person.firstName}" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{vm.person.lastName}" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{vm.person.phone}" />

    </androidx.appcompat.widget.LinearLayoutCompat>
</layout>
```

## 이벤트 바인딩
- 함수 참조와 리스너 바인딩 두 가지 형태로 제공된다.
- 둘의 가장 큰 차이점은 바인딩을 참조하는 시점이다.  
> 차이점  
>> 함수 참조 바인딩
>>- 컴파일 단계에서 바인딩이 참조된다.  
>>- 매개변수, 리턴 타입이 모두 일치해야한다.  

>>리스너 바인딩
>>- 런타임 시전에 바인딩이 참조된다.
>>- 리턴 타입만 일치해도 된다.

### 함수 참조 이벤트 바인딩
함수 참조 값을 등록하여 사용하는 이벤트 바인딩을 **함수 참조에 의한 이벤트 바인딩** 이라 한다.  
함수 참조란, 함수를 호출하는게 아니라 호출해야 하는 함수의 정보를 등록하는 것이며 사용시에는 `::` 를 이용하여 이벤트가 발생할 때 호출되어야 하는 함수 이름을 지정하는 방식이다.  
(함수의 이름은 자유롭게 지정 가능하지만, 매개변수 부분은 같아야 한다.)

onClick(view:View)의 매개변수와 같게 지정한 메서드
```kotlin
fun onClickToast(view: View) {
    Toast.makeText(this, "Hello, World!", Toast.LENGTH_SHORT).show()
}
```

XML
```xml
<data>
    <!-- XML내에서 사용할 변수 선언 -->
    <!-- 패키지명을 포함한 클래스의 경로를 type으로 설정 -->
    <!-- name으로 선언된 이름을 XML내에서 사용 -->
    <variable
        name="clickToast"
        type="kr.co.lee.databindingexample.ExampleActivity" />
</data>

<Button
    android:id="@+id/btn_example"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:onClick="@{clickToast::onClickToast}" />
```

### 리스너 이벤트 바인딩
개발자 임의의 시그니처로 등록하여 이벤트 등록을 하기 위해 리스너 바인딩 방식을 사용한다.  
리스너 바인딩 방식은 **이벤트 핸들러로 람다 함수를 등록하는 방식** 이다.  
개발자가 등록한 람다 함수가 이벤트가 발생할 때 그대로 이벤트 함수 내에서 실행되므로 개발자가 임의이 시그니처를 지정할 수 있다.

이벤트 코드
```kotlin
fun onClickListenerWithoutPerson() {
    Toast.makeText(this, "Sample Code", Toast.LENGTH_SHORT).show()
}

fun onClickListenerWithPerson(person: Person) {
    Toast.makeText(this, "Sample Code - ${person.lastName}", Toast.LENGTH_SHORT).show()
}
```

XML
```xml
<Button
    android:onClick="@{() -> mainActivity.onClickListenerWithoutPerson()}"
    android:onClick="@{() -> mainActivity.onClickListenerWithPerson(person)"
    ... />
```
return 타입만 일치하도록 작성하여 사용해야한다.


[참고 사이트](https://brunch.co.kr/@oemilk/108)