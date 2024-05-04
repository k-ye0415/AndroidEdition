# Service VS Thread

### 📌 `Service`
안드로이드 OS 의 메모리 부족으로 파괴될 경우 `onStartCommand()`에서 `START_TRICKY` 또는 `START_REDELIVER_INTENT`를 확인 해 서비스를 재 시작하는지 확인한다.
안드로이드 컴포넌트라서 메모리 부족으로 앱을 종료하는 동안 고려해야 할 우선순위 수준이 있다.

### 📌 `Thread`
안드로이드 OS 에 의해 메모리 부족으로 파괴될 경우 재시작이 보장되지 않는다.
안드로이드의 구성요소가 아니라서 안드로이드는 메모리 부족으로 앱을 종료하는 동안 `Thread` 의 우선순위를 고려하지 않는다.


### 📌 `Service` 와 `Thread` 차이점
- `Service` 는 Main Thread 에서 실행되고 `Thread` 는 자체 `Thread` 에서 실행된다.
- 긴 작업에 `Service`를 사용하는 경우 Main Thread 가 차단될 수 있다.

<table>
<tr>
<td>Service</td>
<td>Thread</td>
</tr>
<tr>
<td>대부분 UI 없이 백그라운드에서 오래 실행 되는 작업</td>
<td>백그라운드 프로세스를 수행할 수 있는 OS 수준의 기능</td>
</tr>
<tr>
<td>사용자와 상호작용과 관련이 없는 백그라운드 작업 적합</td>
<td>포그라운드에서 앱이 사용자와 상호작용하는 과정에서 적합</td>
</tr>
<tr>
<td>Main Thread 에서 실행 됨.</td>
<td>자체 Thread 에서 실행 됨.</td>
</tr>
<tr>
<td>안드로이드 컴포넌트이고, 인터페이스가 없다.</td>
<td>경량 프로세스이고 안드로이드 컴포넌트가 아니다.<br>UI Thread 를 업데이트 할 수 없고 이를 위한 핸들러가 필요하다.</td>
</tr>
<tr>
<td>백그라운드에서 실행되며 독립적인 자체 생명주기를 갖는다.<br>따라서 Service 를 명시적으로 중지하지 않는 한 계속 실행 된다.</td>
<td>Activity 생명주기에서 실행되며 Activity 가 <code>onDestroy()</code>되면 종료 및 중지 된다.</td>
</tr>
</table>

### 💡 종료
만약 `Service` 에서 `Thread` 를 생성했을 때, `Service` 를 종료한다 하더라도 `Thread` 는 자동 종료가 되지 않는다.  
(`Thread` 를 종료하지 않고 `Service` 만 종료할 경우 생기는 것이 **좀비 프로세스**이다.)

[[참고 사이트 #1]]  
[[참고 사이트 #2]]

[참고 사이트 #1]: https://onlyfor-me-blog.tistory.com/393
[참고 사이트 #2]: https://cau-meng2.tistory.com/57