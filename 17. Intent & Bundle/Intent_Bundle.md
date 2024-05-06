# Intent & Bundle
- Android 애플리케이션에서 Activity, Service, Broadcast Receiver, Content Provider 등 컴포넌트 간의 통신을 하려면 Intent 를 사용해야한다. 
- Intent 객체는 컴포넌트 간에 통신을 위한 메시지를 전달하는 역할을 한다.
- Bundle 객체는 통신을 할 때 전달할 메세지를 저장하는 역할을 한다.
- 즉, Bundle 에 데이터를 저장해서 Intent 로 데이터를 전달한다.

## [Intent](https://github.com/k-ye0415/AndroidEdition/blob/73ab122daf2f11d80b5a7f5faf89d227d32a0ad3/4.%20Intent/Intent.md)
Intent 는 Android 애플리케이션의 컴포넌트 간에 작업 요청을 전달하는 메세지 객체이다.  
Intent 를 이용해 다른 애플리케이션의 컴포넌트를 호출하거나, 자신의 컴포넌트를 호출할 수 있다.  
Intent 는 명시적 호출과 암시적 호출로 구분된다.

### 명시적 호출
호출하려는 컴포넌트를 직접 지정하는 방법으로 호출하려는 컴포넌트의 패키지명과 클래스명을 지정해야한다.
```kotlin
val intent = Intent(this, MyActivity::class.java)
startActivity(intent)
```

### 암시적 호출
호출하려는 컴포넌트의 패키지명과 클래스명을 모르는 경우 사용하는 방법으로 호출하려는 작업의 내용을 지정하고, 시스템이 이 작업을 처리할 수 있는 컴포넌트를 찾아서 호출한다.
```kotlin
val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"))
startActivity(intent)
```

## Bundle
- Bundle 은 Android 에서 다양한 데이터를 저장하고 전달하기 위한 객체이다.  
- Bundle 객체는 Map 인터페이스를 구현하므로, 키-값 쌍으로 데이터를 저장하고 추출할 수 있다.
- Bundle 의 보관은 shut down 후 다시 초기화 할 때 보관할 수 있다.  
_(✏️ 메모리 부족으로 shut down 되는 경우 또는 디바이스를 가로/세로모드로 바꿀 때 shut down 되는 경우 onCreate() 에서 bundle saveinstancestate를 보면 처음 onCreate() 일 때 bundle 값인 saveInstancestate 는 null 이 되고 저장 했을 때 저장 값을 그리게 된다.)_

## Intent & Bundle 사용법
### Intent.putExtra()
데이터 전달
```kotlin
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("key", "Hello, World!")
            startActivity(intent)
        }
    }
}
```
데이터 받기
```kotlin
class MainActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val key = findViewById<TextView>(R.id.key)

        key.text = intent.getStringExtra("key");

    }
}
```

Intent.putExtra() 내부 코드
```java
public @NonNull Intent putExtra(String name, @Nullable String value) {
    if (mExtras == null) {
        mExtras = new Bundle();
    }
    mExtras.putString(name, value);
    return this;
}
```
`mExtras` 는 Bundle 객체이다. 해당 객체가 null 이라면 Bundle 객체를 생성한 뒤 key-value 를 저장하고 return 된다.  
Intent.putExtra() 메서드를 사용하면 Bundle 객체를 따로 생성하지 않아도 내부적으로 자동으로 Bundle 객체를 생성하여 값을 저장한 뒤 데이터를 보낸다.

### Bundle
데이터 전달
```kotlin
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            val bundle = Bundle()
            bundle.putString("key", "Hello, World!")
            intent.putExtra("bundle", bundle)
            startActivity(intent)
        }
    }
}
```
데이터 받기
```kotlin
class MainActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bundle = intent.getBundleExtra("bundle")
        val key = findViewById<TextView>(R.id.key)

        if (bundle != null) {
            key.text = bundle.getString("key")
        }
    }
}
```

## Serializable & Parcelable
기본적인 Intent.putExtra() 에 전달할 수 있는 타입은 다양하지만, 객체를 전달해야한다면 Serializable 또는 Parcelable을 구현한 클래스를 통해 객체를 직렬화 한 뒤 보내야 한다.
>💡 **직렬화(Serialization)**  
객체를 바이트 스트림으로 변환하여 전송하거나 저장하는 것을 말한다.  
직렬화를 통해 객체를 전송하거나 저장할 수 있으며, 이를 역직렬화(Deserialization)하여 다시 객체로 변환할 수 있다.

## Serializable
- Serializable 은 JAVA 표준 인터페이스이다.
- data class 에서 Serializable 만 구현하면 된다.
```kotlin
data class User(
    val name: String?,
    val age: Int
) : Serializable
```
>💡 **Serializable 단점**  
Serializable 인터페이스를 구현한 클래스는 직렬화될 때 reflection 을 사용하여 객체의 모든 필드를 검색하고, 이를 일련의 바이트로 변환하는 방식으로 직렬화하기에 Parcelable 보다 속도가 느리다.  
해당 방식으로 하기 때문에 런타임에 데이터를 직렬화/역직렬화하는 과정에 많은 객체를 생성하기 때문에 GC가 할 일이 많아지므로 Serializable 을 사용하는 객체가 많아질수록 성능에 안좋은 영향을 미치게 된다.  
</br>_(reflection 은 자바에서 객체를 생성하고, 해당 객체의 메소드나 멤버 변수 등의 정보를 알아내는 기술)_  
_(GC 는 Garbage Collection 으로 메모리 관리 관점에서 보자면 사용이 끝나 해제되어야 하는 메모리, 즉 불필요한 메모리)_


Serializable 로 객체 전달
```kotlin
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            val bundle = Bundle()
            bundle.putSerializable("userData", User("brownie", 27))
            intent.apply {
                this.putExtra("bundle", bundle)
            }
            startActivity(intent)
        }
    }
}

data class User(
    val name: String?,
    val age: Int
) : Serializable

```
객체 받기
```kotlin
class MainActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bundle = intent.getBundleExtra("bundle")
        val name = findViewById<TextView>(R.id.name)
        val age = findViewById<TextView>(R.id.age)

        if (bundle != null) {
            val userData = bundle?.getSerializable("userData") as User
            name.text = userData.name
            age.text = userData.age.toString()
        }
    }
}   
```

## Parcelable
- Parcelable 은 Android SDK의 인터페이스이다.
- Serializable 과는 다르게 relection 과정이 없고 직렬화/역직렬화를 하는 과정을 개발자가 구현해야한다.  
_(kotlin에서는 plugins `{id 'kotlin-parcelize'}`를 통해 쉽게 구현할 수 있음.)_
- 구현된 코드가 미리 컴파일되어 런타임에 빠르게 처리되기 때문에 Serializable 보다 빠르게 객체를 전달할 수 있다.

build.gradle.kts (Module :app)
```kts
plugins {
    id 'kotlin-parcelize'
}
```
객체 전달
```kotlin
class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            // bundle 안쓰고 Parcelable
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("userData", User("brownie", 27))
            startActivity(intent)
        }
    }
}

@Parcelize
data class User(
    val name: String?,
    val age: Int
) : Parcelable
```
객체 받기
```kotlin
class MainActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        
        val name = findViewById<TextView>(R.id.name)
        val age = findViewById<TextView>(R.id.age)

        // bundle 안쓰고 parcelable
        val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("userData", User::class.java)
        } else {
            intent.getParcelableExtra<User>("userData")
        }

        if (userData != null) {
            name.text = userData.name
            age.text = userData.age.toString()
        }
    }
}
```


[참고 사이트 #1](https://imtreemiddle.notion.site/Android-Interview-3ce7ddf12ddb413a9d2213173654d52c)  
[참고 사이트 #2](https://codinggom-daily.tistory.com/26)