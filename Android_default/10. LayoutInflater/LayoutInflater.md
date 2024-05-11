# LayoutInflater

### 📌 `LayoutInflater` 란?
- Android 에서 View를 만드는 방법 중 하나
- xml에 정의된 Resource 를 View 객체로 변환해주는 역할을 한다.
- xml에 미리 정해둔 틀을 실제 메모리에 올려주는 역할을 한다.
- Activity 를 만들면 `onCreate()`에 추가되는 `setContentView` 메서드와 유사한 역할.
- xml 레이아웃 파일에 대한 뷰를 생성할 때 `LayoutInflater` 를 이용해야 한다.
- `LayoutInflater` 객체의 inflate 메서드를 이용해 새로운 뷰를 생성할 수 있다.
  - inflate(xml파일, 만든 뷰를 넣을 부모 layout/container, 바로 inflate 할지의 여부)
```kotlin
    // 예시
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
```
- root 를 지정하지 않을 경우 xml 상의 최상위 뷰의 xml layout 설정들은 무시된다.  
![layoutinflater_img.png](https://github.com/k-ye0415/AndroidEdition/blob/01661a7148fa349c7a069c781344960f05144165/Android_default/Android_image/layoutinflater_img.png)

### 📌 `LayoutInflater` 생성하는 3가지 방법
1. Context 에서 `LayoutInflater` 를 가져온다.
```kotlin
val inflater :LyaoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
```
2. Activity 에서 `LayoutInflater` 를 얻어 온다. (Activity 는 자신의 window의 `LayoutInflater`를 사용)
```kotlin
val inflater : LayoutInflater = getLayoutInflater()
```
3. `LayoutInflater` 에 static 으로 정의되어 있는 LayoutInflater.from()을 이용해 `LayoutInflater`를 만든다.
```kotlin
val inflater : LayoutInflater = LayoutInflater.from(context)
// 1번의 방법처럼 내부적으로 context에서 LayoutInflater를 가져온다.
```

### 📌 `LayoutInflater` 사용시 주의사항
- `inflate()` 메서드로 layout 을 inflate 한 경우, 해당 xml 의 land(가로), port(세로) layout 을 자동으로 참조하게 된다.
- `inflate()` 된 View 의 child view 는 inflate 된 `view.findViewById` 로 찾아야 한다.
- `inflate()` 된 View 의 layoutParams 속성은 실제 layout 에서 match_parent 라도, wrap_content 로 강제로 변경된다.  
inflate 된 view 는 parent 가 없어지기 때문에 강제로 wrap_content 시킨다.
- `inflate()` 된 View 에서 다시 `LayoutInflater` 를 사용할 경우, 기존의 findViewById 와 event 설정들이 모두 사라진다.

[[참고 사이트 #1]]  
[[참고 사이트 #2]]

[참고 사이트 #1]: https://yejinson97gaegul.tistory.com/entry/LayoutInflater%EB%9E%80
[참고 사이트 #2]: https://www.crocus.co.kr/1584