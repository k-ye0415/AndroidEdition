# Android 4대 컴포넌트

## 3. Broadcast Receiver

- 단말기에서 발생하는 다양한 이벤트, 정보를 받고 반응하는 컴포넌트  
  (Ex. 화면 On/Off, 재부팅 등)
- Publish ↔️ Subscribe 패턴과 유사하게 안드로이드 시스템 또는 다른 App으로부터 브로드캐스트 메세지를 받는다.
- 안드로이드 시스템에서 벌어지는 일 중에 앱이 알아야하는 상황이 발생하면 <span style="color:#448FBC8F">**Broadcast(방송)**</span>가
  뿌려지고,  
  특정 앱에서 이 Broadcast를 받아드리고 그에 대한 동작을 수행하는 역할이 <span style="color:#FF6347">**Receiver(수신)**</span>
  이다.
-

<table>
<tr>
<td><span style="color:#448FBC8F">Broadcast</span></td>
<td><span style="color:#FF6347">Receiver</span></td>
</tr>
<tr>
<td>방송</td>
<td>수신</td>
</tr>
</table>

#### 3-1. 정적 리시버

- AndroidManifest.xml 에 등록하여 구현. **(한번 등록하면 해제 할 수 없음.)**
- 앱이 종료가 되어도 수신 가능.
- 안드로이드 8.0(Oreo) 부터 몇몇을 제외하고 브로드캐스트 리시버를 AndroidManifest.xml에 등록할 수 없게 되었다. [[참고 사이트 #1]]

💡 **Send**

```kotlin
val intent = Intent()
intent.setAction("패키지명.액션명")
// ex) intent.setAction("com.example.broadcast.MY_NOTIFICATION")
intent.putExtr("Key", "value") // data 도 넣어 보낼 수 있음.
sendbradcast(intent)
```

💡 **Receive**

```xml
<!-- AndroidManifest.xml -->
<receiver android:name=".MyBroadcastReceiver"> <!-- 수신하는 앱의 BroadcastReceiver class 명 -->
    <intent-filter>
        <action android:name="패키지명.액션명" /> <!-- 발신하는 앱에서 정의 된 패키지명과 액션명 -->
    </intent-filter>
</receiver>
```

```kotlin
    class MyBroadcastReceiver : BroadcastReceiver() {  // BroadcastReceiver() class 를 무조건 상속받아야함.

    override fun onReceive(context: Context, intent: Intent) {
        // onReceive 도 메인스레드에서 동작하는 함수이기에 오래걸리는 작업을 수행할 경우 ANR이 발생할 수 있음.
        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }
    }
}
```

#### 3-2. 동적 리시버

- 클래스 파일에서 등록, 해제 할 수 있음. AndroidManifest.xml 에 등록하지 않아도 됨.
- 앱이 실행되고 있는 중에만 수신할 수 있다.

💡 **Send**

```kotlin
val intent = Intent()
intent.setAction("패키지명.액션명")
// ex) intent.setAction("com.example.broadcast.MY_NOTIFICATION")
intent.putExtr("Key", "value") // data 도 넣어 보낼 수 있음.
sendbradcast(intent)
```

💡 **Receive**

```kotlin
class ReceiveActivity : AppCompatActivity {
  
  val receiver = MyBroadcastReceiver()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    val intentFilter = IntentFilter()
    intentFilter.addAction("패키지명.액션명")
    registerReceiver(receiver, intentFilter)
  }

  override fun onDestroy() {
    super.onDestroy()
    
    unregisterReceiver(receiver)
  }
}
```

```kotlin
    class MyBroadcastReceiver : BroadcastReceiver() {  // BroadcastReceiver() class 를 무조건 상속받아야함.

    override fun onReceive(context: Context, intent: Intent) {
        // onReceive 도 메인스레드에서 동작하는 함수이기에 오래걸리는 작업을 수행할 경우 ANR이 발생할 수 있음.
        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            toString().also { log ->
                Log.d(TAG, log)
                Toast.makeText(context, log, Toast.LENGTH_LONG).show()
            }
        }
    }
}
```

장점 : 앱의 부하를 줄일 수 있음.  
단점 : 해제를 적절히 해주지 않으면 메모리 릭이 발생할 수 있음.

### <span style="color:#FF6347">**😵 주의**</span>

리시버는 너무 많은 작업, 시간이 오래걸리는 작업을 하면 안됨!  
처리 지연 시간이 길어진 경우 ANR이 발생.  
Thread 를 별도 생성하여 처리.


[참고 사이트 #1]: https://developer.android.com/guide/components/broadcast-exceptions?hl=ko