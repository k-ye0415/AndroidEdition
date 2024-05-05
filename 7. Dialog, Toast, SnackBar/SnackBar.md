# SnackBar [[Android Developer]]
- Snackbar 에 작업을 추가하게되면 사용자가 메세지에 응답할 수 있다.  

![snack_bar_img.png](https://github.com/k-ye0415/AndroidEdition/blob/main/Android_image/snack_bar_img.png)
```kotlin
class MyUndoListener : View.OnClickListener {

  fun onClick(v: View) {
    // Code to undo the user's last action
  }
}
```
```kotlin
val mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                               R.string.email_archived, Snackbar.LENGTH_SHORT)
mySnackbar.setAction(R.string.undo_string, MyUndoListener())
mySnackbar.show()
```


[Android Developer]: https://developer.android.com/develop/ui/views/notifications/snackbar/action