# DataBinidng + MVVM (간략하게 정리)

MainActivity XML
```xml
<<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="viewModel"
            type="com.practice.buna.mvvm.feature.viewmodel.MainViewModel" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".feature.MainActivity"
        android:background="#FFF27F">

        <ImageView
            android:id="@+id/camera_button"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:src="@android:drawable/ic_menu_camera"
            android:clickable="true"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintBottom_toTopOf="@+id/camera_name"
            android:onClick="@{viewModel::onClickCameraBtn}"/>
...

</layout>
```

MainActivity.kt
```kotlin
 class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 기존의 setContentView 사용 안 함.
        //setContentView(R.layout.activity_main)

        // 데이터 바인딩 객체 생성
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // 뷰모델 생성
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

	// isPressed 변수 관찰
        mainViewModel.isPressed.observe(this) { isPressed ->
            if(isPressed) {
                binding.cameraName.text = "카메라 버튼 눌림!"
            } else {
                binding.cameraName.text = "카메라 버튼 뗌!"
            }
        }

        with(binding) {
            viewModel = mainViewModel // xml의 viewModel과 View에서 생성한 viewModel을 바인딩
        }
    }
}
```


MainViewModel.kt
```kotlin
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // 카메라 버튼이 눌렸는지 확인하기 위한 MutableLiveData
    var isPressed = MutableLiveData<Boolean>(false)

    // 카메라 눌림 감지를 메서드
    fun onClickCameraBtn(v: View) {
        // 버튼 눌림 상태 Toggle
        isPressed.value = isPressed.value?.not()
    }
}

```



[참고 사이트](https://itstory1592.tistory.com/51)