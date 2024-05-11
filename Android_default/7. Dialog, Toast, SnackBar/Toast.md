# Toast [[Android Developer]]
- Toast는 작은 팝업으로 작업에 대한 간단한 피드백을 제공
- 메세지에 필요한 공간만 채우며 현재 진행중인 활동은 계속 표시된다.
- 일정한 시간 후에 자동으로 사라진다.

### 📌 Toast 사용의 대안
- 포그라운드에 있는 경우 `Snackbar` 사용 하는 것이 좋음.
  - `Snackbar` 에는 사용자가 실행할 수 있는 옵션이 포함되어 있음.
- 백그라운드에 있는 경우 `Notification` 사용 하는 것이 좋음.

### 📌 Toast 개체 인스턴스화
1. `Context`
2. 표시될 텍스트
3. Toast 표시 시간

### 📌 Toast 사용
```kotlin
val text = "Hello toast!"
val duration = Toast.LENGTH_SHORT

val toast = Toast.makeText(this, text, duration) // in Activity
toast.show()
```


[Android Developer]: https://developer.android.com/guide/topics/ui/notifiers/toasts