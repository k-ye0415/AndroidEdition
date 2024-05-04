# SharedPreferences

### 📌 SharedPreferences
- 간단한 데이터를 저장하기 위해 사용
- 데이터 로드속도가 보다 빠르고 복잡한 절차없이 간편하게 데이터를 저장 및 가져올 수 있다.


### 📌 SharedPreferences 특징
- xml 파일 형태로 데이터를 저장.
  - 파일 형태로 저장되므로 너무 많은 데이터를 넣게 되면 메모리 예외가 생길 수 있음.
  - 파일 형태로 저장되므로 메모리 손상의 위험에 영향이 갈 수 있음.
  - 보안에 취약하므로 보안이 요구되는 데이터는 SharedPreferences 로 저장하지 않는 것이 옳다.
- 어플리케이션이 삭제되기 전까지 데이터가 보존된다.
- Key-value 형태로 데이터가 저장 됨.
- 불러오려는 Key 값이 없는 경우 공백 문자열을 리턴한다.
- 초기 설정값 (자동 로그인 여부, 진동 유무 등)과 같이 간단한 값을 저장하고 싶을 때 사용한다.

### 📌 SharedPreferences 저장 모드
<table>
<tr>
<td>MODE</td>
<td>의미</td>
</tr>
<tr>
<td>MODE_PRIVATE</td>
<td>어플리케이션 내에서 사용 가능, 외부 어플리케이션에서 사용 불가. <strong>Default 값</strong></td>
</tr>
<tr>
<td>MODE_WORLD_READABLE</td>
<td>외부 어플리케이션에서 읽기 가능 (API 17부터 deprecated)</td>
</tr>
<tr>
<td>MODE_WORLD_WRITEABLE</td>
<td>외부 어플리케이션에서 쓰기 가능 (API 17부터 deprecated)</td>
</tr>
</table>


### 📌 SharedPreferences 사용하기
💡 SharedPreferences 인스턴스 가져오기
```kotlin
// 모든 Context 에서 호출 시
val spf : SharedPreferences = context.getSharedPreferences("key", Context.MODE_PRIVATE)

// 하나의 Activity 에서 사용 시
val spf : SharedPreferences = context.getPreferences(MODE_PRIVATE)
```

💡 SharedPreferences 데이터 저장하기
데이터를 저장할 수 있는 메소드의 종류
```kotlin
val spf : SharedPreferences = context.getPreferences(Context.MODE_PRIVATE)
val editor = spf.edit()
// Boolean 값
editor.putBoolean("status", true).commit()
// Float 값
editor.putFloat("pi", 3.14).apply()
// Int 값
editor.putInt("count", 1).apply()
// Long 값
editor.putLong("time", System.currentTimeMillis()).apply()
// String  값
editor.putString("userId", "hello world").apply()
// String 배열 값
editor.putStringSet("hobby", listOf<String>("취미1", "취미2")).apply()
```
> ✏️ `commit()` 과 `apply()` 차이
> `commit()`
> - 호출 시 스레드는 block 되며 디스크에 값을 **동기적**으로 업데이트한다.
> - ANR 을 막기위해 메인 스레드에서 호출하는 것을 주의 해야 한다.
> - 처리 결과를 true/false 로 반환한다.
> - 결과 값이 없는 경우 `apply()`를 사용하는 것이 좋을 수 있다.
> 
> `apply()`
> - 메모리 내에 SharedPreferences 객체를 즉시 변경하지만 디스크에 **비동기적**으로 업데이트 한다.
> - 호출 후 바로 return 되어 스레드를 block 하지 않는다.


💡 SharedPreferences 데이터 불러오기
```kotlin
val spf : SharedPreferences = context.getPreferences(Context.MODE_PRIVATE)
// 저장된 모든 값 (Map 타입으로 리턴)
val anything = spf.getAll()
// Boolean 값 (key, default value)
val status = spf.getBoolena("status", true)
// Float 값 (key, default value)
val pi = spf.getFloat("pi", 3.14)
// Int 값 (key, default value)
val count = spf.getInt("count", 0)
// Long 값 (key, default value)
val time = spf.getLong("time", System.currentTimeMillis())
// String 값 (key, default value)
val userId = spf.getString("userId", "")
// String 배열 값
val hobbyList = spf.getStringSet("hobby", emptyList<String>())
```

💡 SharedPreferences 데이터 삭제하기
```kotlin
// 특정 데이터 삭제
val spf : SharedPreferences = context.getPreferences(Context.MODE_PRIVATE)
val editor = spf.edit()
editor.remove("count")
editor.apply()

// 모든 데이터 삭제
val spf : SharedPreferences = context.getPreferences(Context.MODE_PRIVATE)
val editor = spf.edit()
editor.clear()
editor.apply()
```

[[참고 사이트 #1]]

[참고 사이트 #1]: https://hapen385.tistory.com/29