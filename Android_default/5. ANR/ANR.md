# ANR
[[Android Developers - ANR]]  
[[Android Developers - Keeping your app responsive]]  
[[참고 사이트 #1]]  
[[참고 사이트 #2]]
- ANR 이란 `Application Not Responding` 의 약자이다.
- Android App 의 UI 스레드가 너무 오랫동안 차단되면 `'ANR(애플리케이션 응답없음)` 오류가 나타나게 된다.
- 앱이 포그라운드에 있으면 아래와 같은 시스템에서 사용자에게 다이얼로그를 표시한다. (사용자가 ANR 다이얼로그에서 앱을 강제 종류 할 수 있다.)  
![anr.png](https://github.com/k-ye0415/AndroidEdition/blob/main/Android_image/anr.png)

### 📌 `ANR` 발생하는 이유?
>- 입력 전달 타임아웃 : 앱이 입력 이벤트 (ex. 키 누름 또는 화면 터치)에 5초 이내에 응답하지 않는 경우
>- 서비스 실행 : 앱에서 선언한 서비스가 몇 초 이내에 `Service.onCreate()` 및 `Service.onStartCommand()`또는 `Service.onBinde()` 실행을 완료할 수 없는 경우
>- `Service.startForeground()`가 호출 되지 않음 : 앱이 `Context.startForegroundService()`를 사용하여 포그라운드에서 새 서비스를 시작했지만, 서비스가 5초 내에 `startForeground()`를 호출하지 않은 경우
>- 인텐트 브로드캐스트 : `BroadcastReceiver`가 설정된 시간 내에 실행을 완료하지 못한 경우 (앱이 포그라운드 활동이 있는 경우 이 제한 시간은 5초이다.)

모든 동작에 있어 5초 이내에 실행하지 않으면 발생한다는 것이 아닌, **UI 업데이트 담당하는 UI Thread(Main Thread) 에서 수행되는 동작의 5초**이다.

### 📌 `ARN` 발생 예방
- **UI 업데이트(사용자에게 보여지는 시각적인 것들)를 제외한 나머지 작업은 Main Thread 에서 수행하지 말것**
- 연산, 네트워크 통신, 저장소와 관련된 작업들은 새로운 Thread 를 생성하여 작업한다. 
- 해당 작업들은 굳이 사용자에게 시각적으로 보여지지 않아도 되기에 결과만 보여주도록 한다.
- 결과까지의 시간이 걸릴 경우 사용자에게 무언가 작업중임을 표시하기위해 프로그래스바 등으로 표현하면 좋다.  
사용자가 특정 기능(ex. 네트워크 통신, 저장소 등)을 수행하는 버튼을 눌렀을 때 수행하고 있음에도 시각적으로 알 수 없다면 실행되지 않다 판단하여 동일 기능을 반복, 연속해서 실행시키게 되면 작업의 과부하로 인해 `ANR`이 발생될 수 있고 앱을 강제 종료할 수 있기 때문이다.


[Android Developers - ANR]:   https://developer.android.com/topic/performance/vitals/anr?hl=ko
[Android Developers - Keeping your app responsive]:   https://developer.android.com/training/articles/perf-anr
[참고 사이트 #1]:https://solution94.tistory.com/112
[참고 사이트 #2]:https://itmining.tistory.com/3