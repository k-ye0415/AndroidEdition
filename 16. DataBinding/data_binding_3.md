# DataBiding

## import, variable, include
DataBinding 라이브러리는 `<data>` 태그 안에서 `import`, `variable`, `include` 태그를 제공한다.
- `import` : 레이아웃 파일안에서 클래스에 대한 참조를 쉽게 만든다.
- `variable` : 바인딩 표현식 `@{}`에 사용할 수 있는 변수(프로퍼티)를 나타낸다.
- `include` : 앱 전체에서 복잡한 레이아웃을 재사용할 수 있다.

## import
코드에서 `import`를 통해 클래스를 쉽게 참조하는 것처럼 View 에서도 코드와 같은 방식으로 호출하여 사용.  

✏️ 예시
XML
```xml
<data>
    <import type="android.view.View"/>
</data>

<TextView
   android:text="@{user.lastName}"
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"
   android:visibility="@{user.isAdult ? View.VISIBLE : View.GONE}"/>
```

### 타입
import 태그를 통해 참조하고 variable 의 타입으로 사용할 수 있다.

XML
```xml
<data>
    <import type="com.example.User"/>
    <import type="java.util.List"/>
    <variable name="user" type="User"/>
    <variable name="userList" type="List&lt;User>"/>
</data>
```
>📌 "<" 를 사용하면 `:app:dataBindingProcessLayoutsDevDebug` 에러를 발생하기 때문에 이스케이프처리를 해줘야 한다.  
'&' 👉🏻 \&amp;  
'<' 👉🏻 \&lt;  
'>' 👉🏻 \&gt; 

### 정적 필드 및 메소드 참조
최상위 함수나 최상위 프로퍼티가 선언된 클래스 파일을 import 하여 가져온 후 레이아웃 파일안에서 그 클래스를 사용 및 참조할 수 있다.

XML
```xml
<!-- 최상위 함수 capitalize 함수가 선언된 파일 이름이 MyStringUtils라고 가정 -->
<data>
    <import type="com.example.MyStringUtils"/>
    <variable name="user" type="com.example.User"/>
</data>
…
<TextView
    android:text="@{MyStringUtils.capitalize(user.lastName)}"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
```

## variable
XML 에서 사용할 수 있는 변수이다.

XML
```xml
<data>
    <import type="android.graphics.drawable.Drawable"/>
    <variable name="user" type="com.example.User"/>
    <variable name="image" type="Drawable"/>
    <variable name="note" type="String"/>
</data>
```
- 생성된 바인딩 클레스에는 각 variable 에 대한 setter/getter 가 정의 되어 있다.
- variable 에 대해서 setter 가 호출되기 전까지는 default 값을 사용한다. (참조 타입은 null과 같은 값을 가지고 있다.)
- 최상위 태그를 `<layout>`으로 지정한 데이터 바인딩이 적용된 XML 에서 Context 객체에 접근할 수 있다. (루트 뷰의 `getContext()` 메서드에서 가져온 Context 객체이다.)


## include
`<include>` 태그를 통해 하나의 XML 에 작성된 내용을 다른 XML 로 작성해서 포함할 수 있다.  
include 되는 XML 에 사용한 바인딩 표현식이 include 한 XML 에 나타나지 않는다는 주의해야하는 점이 있다.

include 되는 sub.xml
```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:bind="http://schemas.android.com/apk/res-auto">
   <data>
       <variable name="model" type="com.example.Model"/>
   </data>
   <TextView
       android:text='@{"include xml... data : " + model.name}' />
</layout>
```

include 하는 main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:bind="http://schemas.android.com/apk/res-auto">
   <data>
       <variable name="model" type="com.example.Model"/>
   </data>
   <LinearLayout
       android:orientation="vertical"
       android:layout_width="match_parent"
       android:layout_height="match_parent">
       <include layout="@layout/sub"
           bind:model="@{user}"/>
   </LinearLayout>
</layout>
```
include 에 의해 데이터 바인딩이 정상적으로 되려면 include 하는 XML(main.xml)에서 명시적으로 include 대상이 되는 XML(sub.xml)에 데이터를 넘겨줘야 하며, include 대상이 되는 XML(sub.xml)내에도 이를 받을 준비가 되어야 한다.


## Binding Adapter
바인딩 어뎁터는 뷰에 개발자가 정의하는 메소드를 호출하여 값을 설정하는 작업을 한다.  
바인딩 어댑터는 view 의  속성을 custom 하게 작성하여 추가하는 것.

BindingAdapter
```kotlin
@BindingAdapter("visible")
fun visible(view: View, isVisible: Boolean) {
    view.visibility = if(isVisible) View.VISIBLE else View.INVISIBLE
}

// 확장함수 형태
@BindingAdapter("visible")
fun View.visible(isVisible: Boolean) {
	visibility = if(isVisible) View.VISIBLE else View.INVISIBLE
}
```
- BindingAdapter 는 `@BindingAdapter` 애노테이션을 통해 생성.  
(인자를 여러개 넘기고 싶다면 value 파라미터로 String 배열과 requireAll 파라미터로 true or false 를 지정하면 된다.)
    >✏️ 예시  
    >```kotlin
    >    @BindingAdapter(value = {"arg1", "arg2"}, requireAll = true)
    >    public static void setFormattedText(TextView textView, String arg1, int arg2) {
    >        // 여기에서 arg1과 arg2를 사용하여 TextView에 원하는 형식으로 텍스트를 설정합니다.
    >        String formattedText = String.format("Argument 1: %s, Argument 2: %d", arg1, arg2);
    >        textView.setText(formattedText);
    >    }
    >```
    >위의 코드에서는 value 배열에 "arg1"과 "arg2"라는 두 개의 인자를 명시했습니다. requireAll은 true로 설정하여, 모든 인자가 필수로 전달되어야 한다는 것을 나타내고 있습니다.
    >```xml
    ><TextView
    >    android:layout_width="wrap_content"
    >    android:layout_height="wrap_content"
    >    app:arg1="@{viewModel.text}"
    >    app:arg2="@{viewModel.number}" />
    >```
- 커스텀 속성으로 지정한 이름을 애노테이션의 생성자 인자로 지정한다.  
    `@BindingAdapter("visible")`
- 함수명은 자유롭게 작성 가능하며, 확장 함수를 사용해서 선언하면 매개변수에 view 를 넘길 필요가 없지만, 확장 함수가 아니면 매개변수로 view 를 지정 및 전달이 필요하다.


XML
```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    
    <data>
        <variable
            name="viewModel"
            type="kr.co.lee.databindingexample.viewmodels.MainViewModel" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">
        
        <!-- visible 이라는 Custom 속성을 정의 -->
        <TextView
            app:visible="@{viewModel.isVisible}"
            android:id="@+id/binding_view"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Binding Adapter 테스트"
            android:textSize="20sp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

      	<!-- viewModel의 setVisible() 메소드를 통해 값 변경 -->
        <Button
            android:id="@+id/visible_button"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="visible or inVisible ?"
            app:layout_constraintTop_toBottomOf="@id/binding_view"
            android:onClick="@{() -> viewModel.setVisible()}"/>


    </androidx.constraintlayout.widget.ConstraintLayout>

</layout>
```

## ViewModel 및 Activity
Activity
```kotlin
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // ViewModel 객체 생성
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // DataBinding 레이아웃 지정
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            // binding의 lifeCycle을 액티비티로 지정
            lifecycleOwner = this@MainActivity
            // XML의 variable로 선언한 ViewModel 객체 지정
            viewModel = mainViewModel
        }
    }
}
```

ViewModel
```kotlin
class MainViewModel: ViewModel() {
    private val _isVisible = MutableLiveData<Boolean>(false)
    val isVisible: LiveData<Boolean> get() = _isVisible

    // 버튼을 클릭하면 호출되는 메소드
    fun setVisible() {
        _isVisible.value = (_isVisible.value != true)
    }
}
```
XML
```xml
<Button
    android:id="@+id/visible_button"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="visible or inVisible ?"
    app:layout_constraintTop_toBottomOf="@id/binding_view"
    android:onClick="@{() -> viewModel.setVisible()}"/>
```



[참고 사이트](https://stackoverflow.com/questions/37152824/android-databinding-using-logical-operator)