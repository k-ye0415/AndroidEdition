# Android 4대 컴포넌트

## 2. Service [참고 사이트#1]

- Service 란 UI 화면에 존재하지 않고 백그라운드에서 실행되는 컴포넌트
- [Android 공식 사이트 - Service]
    - 백그라운드에서 오래 실행되는 작업을 수행할 수 있고, 사용자 인터페이스 (UI) 제공하지 않음.
    - 내 앱이 아닌 다른 앱도 `Service`를 수행할 수 있다.
    - 구성요소를 서비스에 <span style='background-color:#ffdce0; color:#444444'>바인딩하여 서비스</span>와 상호작용 할 수
      있음.
      <details>
      <summary><span style='background-color:#ffdce0; color:#444444'>바인딩된 서비스</span></summary>
      <div markdown="1">

        - 클라이언트 ↔️ 서버 인터페이스 안의 서버.<br>
        - `Activity`와 같은 구성요소를 서비스에 바인딩, 요청, 응답, [프로세스 통신(IPC)] 실행 가능.
        - 일반적으로 바인드된 서비스는 다른 앱 구성요소를 도울 떄까지만 유지되고 백그라운드에서 무한히 실행되지는 않는다.

      </div>
      </details>
  > ✏️ 예시  
  > 네트워크 [트랜잭션]을 처리하면서 음악을 재생하고 파일 I/O를 수행하거나 콘텐츠 제공자와 상호작용 가능하며, 이 모든것을 백그라운드에서 처리할 수 있음.

<table>
<tr>
<td>Service 타입</td>
<td>내용</td>
</tr>
<tr>
<td rowspan="4">Foreground Service</td>
<td>사용자와 상호작용하지 않아도 계속 실행됨.</td>
</tr>
<tr>
<td>사용자에게 잘 보이는 몇몇 작업을 수행.</td>
</tr>
<tr>
<td>✏️ 예시<br> 음악 앱을 실행 시 음악을 재생할 때 포그라운드 서비스를 사용한다.</td>
</tr>
<tr>
<td>포그라운드 서비스는 `Notification` 과 같은 알림을 표시해야한다.  <br> (그래서 사용자와 상호작용하지 않아도 계속 실행된다)
</td></tr>
<tr>
<td rowspan="2">Background Service</td>
<td>사용자가 직접 알지 못하는 작업을 수행함.</td>
</tr>
<tr>
<td>✏️ 예시  <br> 사진을 다운로드 할 때 다운로드 버튼을 누르고, 앱을 백그라운드로 보내도 다운로드를 계속 진행하는 것</td>
</tr>
<tr>
<td rowspan="4">Bound service<br> (<strike>실습필요해보임</strike></td>
<td>앱 내의 서비스를 사용하여 간단한 클라이언트 <-> 서버 환경을 구성. (특정 컴포넌트와 서비스간 상호작용)</td>
</tr>
<tr>
<td><code>bindService()</code> 호출 시 서비스가 바인딩 됨.</td>
</tr>
<tr>
<td>바인딩된 서비스는 또 다른 애플리케이션 구성 요소가 이에 바인딩되어 있는 경우에만 실행가능.</td>
</tr>
<tr>
<td>여러개의 구성 요소가 서비스에 한꺼번에 바인딩될 수 있지만, 이 모든 것에서 바인딩이 해제되면 해당 서비스는 소멸 됨.</td>
</tr>
</table>

- 서비스가 시작되었든, 바인딩되었든 아니면 양쪽 모두이든 모든 앱 구성 요소가 해당 서비스를 사용할 수 있다.  
  (다른 앱에서도 사용 가능)
- 서비스도 마찬가지로 `manifest`에 선언하여 사용하지만, 다른 앱의 접근을 막기 위해 서비스를 비공개로 선언하여 사용할 수
  있다. [manifest-Service](#manifest)

### 서비스와 스레드 간의 선택

- **서비스는 백그라운드에서 실행될 수 있는 구성 요소일 뿐** ➡️ 꼭 필요한 경우에만 서비스를 사용해야 한다.
- 사용자가 앱과 상호작용하는 동안 메인스레드 밖에서 작업을 수행해야 하는 경우 새 스레드를 생성해야한다.

> ✏️예시  
> 액티비티가 실행되는 중에만 음악을 재생하고자 하는 경우,
> `onCreate()` 안에 스레드를 생성하고 `onStart()`에서 실행 후 `onStop()`에서 중단하면 된다.  
> 또한 기존 스레드 클래스 대신 `AsyncTask` 또는 [`HandlerThread`] 를 사용할 수도 있다.  
> (~~AsyncTask는 Deprecate 되었음~~)

### Service Callback Method

- 서비스를 생성하려면 `Service()` 를 상속 받는 클래스가 필요하다.

```kotlin
class MusicService : Service() {
    ...
}
```

- 해당 클래스를 상속받게되면 콜백 메서드를 재정의해야한다.

<table>
  <tr>
    <th>콜백 메서드</th>
    <th>내용</th>
  </tr>
  <tr>
    <td rowspan="4"><code>onStartCommand()</code></td>
    <td>다른 구성요소가 서비스를 시작하도록 요청하는 경우 호출 (ex.<code>Activity</code>)</td>
  </tr>
  <tr>
    <td><code>startService()</code> 를 호출하여 시작.</td>
  </tr>
  <tr>
    <td>해당 메서드가 실행되면 서비스가 시작되고 백그라운드에서 무한히 실행될 수 있다.</td>
  </tr>
  <tr>
    <td>무한히 도는 것을 방지하기 위해 <code>stopSelf()</code> 또는 <code>stopService()</code> 호출.<br>(바인딩만 제공하는 경우 이 메서드를 구현하지 않아도 된다.)</td>
  </tr>
  <tr>
    <td rowspan="4"><code>onBind()</code></td>
    <td>다른 구성요소가 서비스에 바인딩되고자 하는 경우 호출 (ex. <A href="https://noobcodeing.tistory.com/49">RPC</A> )</td>
  </tr>
  <tr>
    <td><code>bindService()</code>를 호출하여 시작.<br> 파라미터로는 Intent, ServiceConnection, Int 가 필요하다.
<br>
<pre><code>private val conn = object : ServiceConnection {
  override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
    ...
  }

  override fun onServiceDisconnected(name: ComponentName?) {
    ...
  }
}
val intent = Intent(Activity class, Service class)
bindService(intent, conn, Context.BIND_AUTO_CREATE)
  </code></pre></td>
  </tr>
  <tr>
    <td>해당 메서드는 클라이언트가 서비스와 통신을 주고 받기 위한 인터페이스를 제공해야하는데, <code>IBinder</code>를 반환하면 된다.<br><pre><code>override fun onBind(intent: Intent?): IBinder? {
    return null
}</code></pre></td>
  </tr>
  <tr>
    <td>해당 메서드는 필수로 구현해야하지만, 바인딩을 사용하지 않을 것이면 위에 코드처럼 <code>null</code>을 반환하면 된다.</td>
  </tr>
  <tr>
    <td rowspan="2"><code>onCreate()</code></td>
    <td>서비스가 처음 생성되었을 때 해당 메서드를 일회성 설정 절차로 수행한다. <br>(onCreate() ➡️ onStartCommand() 또는 onCreate() ➡️ onBind())</td>
  </tr>
  <tr>
    <td>일회성으로 수행하기에, 이미 서비스가 실행 중이라면 해당 메서드는 호출되지 않는다.</td>
  </tr>
  <tr>
    <td rowspan="2"><code>onDestroy()</code></td>
    <td>서비스를 더 이상 사용하지 않고 소멸시킬 때 수행한다.</td>
  </tr>
  <tr>
    <td>해당 메서드는 서비스가 수신하는 마지막 콜백 메서드이다.</td>
  </tr>
</table>

💡 `startService()` 로 서비스 시작하는 경우

- 해당 서비스는 `stopSelf()` or `stopService()` 호출하여 서비스를 중단시킬 때까지 실행중인 상태로 유지.
- 생명주기 : onCreate ➡️ <span style="color:orange">**onStartCommand**</span> ➡️ stopSelf() or (다른
  구성요소에서)stopService() ➡️ onDestroy
    - <span style="color:orange">**onStartCommand**</span> 는 return 값이 필요!
  <table>
  <tr>
  <td rowspan="4"><code>START_NOT_STICKY</code></td>
  <td> 안드로이드가 서비스를 강제 정지한 경우, 재시작 하지 않음.</td>
  </tr>
  <tr>
  <td>시스템이 서비스를 <code>onStartCommand()</code>반환 후 중단시키면 서비스를 <strong>재생성하면 안된다.</strong><br>(전달할 보류 인텐트가 있는 경우는 예외)</td>
  </tr>
  <tr>
  <td>해당 return 값은 서비스가 불필요하게 실행되는 일을 피할 수 있는 <span style="color:orange"><strong>가장 안전한 옵션</strong></span></td>
  </tr>
  <tr>
  <td>앱이 완료되지 않은 모든 작업을 단순히 시작할 수 있을 때 유용.</td>
  </tr>
  <tr>
  <td rowspan="4"><code>START_STICKY</code></td>
  <td>안드로이드가 서비스를 강제 정지한 경우, null Intent 를 보내 재시작.</td>
  </tr>
  <tr>
  <td>시스템이 서비스를 <code>onStartCommand()</code>반환 후 중단시키면 서비스를 다시 생성하고 <code>onStartCommand()</code>를 호출하지만, 마지막 인텐트는 <strong>전달하지 않는다.</strong></td>
  </tr>
  <tr>
  <td>전달하지 않는 대신 null 인텐트로 <code>onStartCommand()</code>를 호출한다.<br>(서비스를 시작하기 위한 보류 인텐트가 있는 경우 예외, 해당 보류 인텐트를 전달.)</td>
  </tr>
  <tr>
  <td>명령을 실행하지는 않지만 무한히 실행 중이며 작업을 기다리고 있는 미디어 플레이어에 적합.</td>
  </tr>
  <tr>
  <td rowspan="4"><code>START_REDELIVER_INTENT</code></td>
  <td>안드로이드가 서비스를 강제 정지한 경우, 동일한 Intent 를 보내 재시작.</td>
  </tr>
  <tr>
  <td>시스템이 서비스를 <code>onStartCommand()</code>반환 후 서비스를 중단하는 경우 서비스를 다시 생성하고 해당 서비스에 전달된 마지막 인텐트로 <code>onStartCommand()</code>를 호출한다.</td>
  </tr>
  <tr>
  <td>모든 보류 인텐트가 차례로 전달 된다.</td>
  </tr>
  <tr>
  <td>즉시 재개되어야 하는 작업을 능동적으로 수행 중인 파일 다운로드 등의 서비스에 적합.</td>
  </tr>
  </table>

💡 `bindService()` 로 서비스를 시작하는 경우

- 해당 구성요소가 바인딩된 경우에만 실행된다.
- 서비스가 모든 클라이언트로부터 바인딩이 해제되면 시스템이 알아서 중단시킨다.
- 생명주기 : onCreate ➡️ onBind ➡️ unBindService() 를 호출하는 경우 ➡️ onUnbind ➡️ onDestroy

💡 `startService()` 와 `bindService()`의 생명주기

![Alt text](https://github.com/k-ye0415/AndroidEdition/blob/01661a7148fa349c7a069c781344960f05144165/Android_default/Android_image/service_image.png)

### 🚩 Service 🆚 IntentService [참고 사이트#2]

<table>
<tr>
<td rowspan="2">Service</td>
<td>메인 스래드에서 실행되므로, 오랜 시간이 걸리는 작업을 수행할 때는 백그라운드 스레드를 사용해야 한다.  </td>
</tr>
<tr>
<td>백그라운드에서 동작하지만 메인스레드에 포함된다.</td>
</tr>
<tr>
<td rowspan="4">Intent Service</td>
<td>백그라운드에서 작업을 수행하는 데 특화된 컴포넌트이다. </td>
</tr>
<tr>
<td>작업을 수행하기 위해 큐(Queue)를 사용하며, 한번에 하나의 작업만 처리한다.</td>
</tr>
<tr>
<td>새로운 스레드에서 동작한다.</td>
</tr>
<tr>
<td>모든 작업이 완료되면 자동으로 종료된다.</td>
</tr>
</table>

<table>
<tr>
<td></td>
<td>Service</td>
<td>Intent Service</td>
</tr>
<tr>
<td>실행 방법</td>
<td><code>startService()</code></td>
<td>Intent 사용에 의해 실행.<br><code>onHandleIntent()</code></td>
</tr>
<tr>
<td rowspan="2">차이점</td>
<td>개발자가 직접 스레드를 생성하여 백그라운드 작업 처리 필요</td>
<td>작업을 큐에 저장하고 자동으로 스레드를 생성하여 작업 처리</td>
</tr>
<tr>
<td>수동으로 Service 종료</td>
<td>자동으로 Service 종료</td>
</tr>
<tr>
<td>단점</td>
<td>무거운 작업일 때 메인스레드에 영향을 주어 느려질 수 있다.</td>
<td>병렬적으로 수행될 수 없으므로 연속적인 Intent 호출에 관해 순차적으로 처리 된다.</td>
</tr>
<tr>
<td rowspan="4">주의할 점</td>
<td colspan="2">두가지 Service를 구현할 때는 생명주기와 스레드 처리를 주의해야 한다.<br>특히 <code>Service</code>는 기본적으로 메인 스레드에서 동작하므로 메인스래드를 블록하지 않도록 주의 해야 한다.</td>
</tr>
<tr>
<td colspan="2">지나치게 오랫동안 백그라운드에서 작업을 수행하는 경우, 배터리 수명이 감소하고 시스템 자원이 소모될 수 있다.</td>
</tr>
<tr>
<td colspan="2">실행 우선순위는 각각 <code>startService()</code> 와 <code>startForegroundService()</code>를 사용하여 변경할 수 있다.<br><code>startForegroundService()</code>를 사용하면 더 높은 우선순위로 실행된다.(ex. Notification)</td>
</tr>
<tr>
<td colspan="2">메모리 누수를 방지하기 위해 항상 <code>onDestroy()</code>에서 리소스 해제를 하는 것이 좋다.</td>
</tr>
<tr>
<td>사용 예시</td>
<td>음악 재생, 네트워크 요청, 위치 추적 등</td>
<td>파일 다운로드, 데이터베이스 작업 등</td>
</tr>
</table>

### Manifest

- `Activity`와 마찬가지로 manifest 에 선언을 해주어야 한다.

```xml
<manifest ... >
    ...
    <application ... >
        <service
            android:name=".ExampleService" />
        ...
    </application>
</manifest>
```

[더 자세한 선언 방법은 링크 참고]

- `android:name` 은 유일한 필수 특성.
- `android:exported` 가 `false`로 설정되면 본인의 앱에서만 사용 가능하다.

[Android 공식 사이트 - Service]: https://developer.android.com/guide/components/services?hl=ko

[프로세스 통신(IPC)]: https://dar0m.tistory.com/233

[트랜잭션]: https://sjh836.tistory.com/11

[`HandlerThread`]: https://developer.android.com/reference/android/os/HandlerThread

[더 자세한 선언 방법은 링크 참고]: https://developer.android.com/guide/topics/manifest/service-element?hl=ko

[RPC]: https://noobcodeing.tistory.com/49

[참고 사이트#1]: https://lucky516.tistory.com/178

[참고 사이트#2]: https://blacktrees.tistory.com/entry/Android-Service%EC%99%80-IntentService%EC%9D%98-%EC%B0%A8%EC%9D%B4%EC%A0%90