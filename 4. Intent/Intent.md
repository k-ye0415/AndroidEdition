# Intent

[[Android 공식 사이트]]

`Intent`는 매세징 객체로 다른 구성 요소(Android 4대 컴포넌트)로부터 작업을 요청하는데 사용 할 수 있다.
<table>
<tr>
<td rowspan="3">Activity</td>
<td>Activity의 새 인스턴스를 시작하려면 <code>startActivity()</code>를 사용하여 전달하면 된다.</td>
</tr>
<tr>
<td>Activity가 완료 되었을 때 결과를 수신하려면 <code>startActivityForResult()</code>를 호출한다.</td>
</tr>
<tr>
<td>Activity는 해당 결과를 수신하는 Activity의 <code>onActivityResult()</code> 콜백에서 별도의 <code>Intent</code> 객체로 수신한다.</td>
</tr>
<tr>
<td>Service</td>
<td>서비스를 시작하여 파일 다운로드와 같은 일회성 작업을 수행하도록 하려면 <code>startService()</code>를 사용하여 전달하면 된다.</td>
</tr>
<tr>
<td>Broadcast</td>
<td><code>sendBroadcast()</code> 또는 <code>sendOrderBroadcast()</code> 를 사용하여 전달하면 된다.</td>
</tr>
</table>

### `Intent` 유형
💡 명시적 인텐트
- 인텐트에 클래스 객체나 컴포넌트 이름을 지정하여 호출할 대상을 확실하게 알 수 있는 경우에 사용한다.   
  (주로 앱 내부에서 사용)
- 예시 : 새로운 Activity를 호출

💡 암시적 인텐트
- 호출할 대상이 달라질 수 있는 경우에 사용한다.  
(시스템이 인텐트를 이용해 요청한 작업을 처리할 수 있는 적잘한 컴포넌트를 찾아 사용자에게 그 작업과 처리 결과를 보여준다.)
- 예시 : 사진 업로드 할 때 카메라 or 갤러리 앱 중 사용자에게 선택하도록 하는


### `Intent` 빌드
💡 구성 요소 이름
- 인텐트를 **명시적**인 것으로 만들어주는 중요한 정보
  - 구성 요소 이름이 정의한 앱 구성 요소에만 전달하는 **명시적 인텐트**
- 구성 요소 이름이 없으면 **암시적**으로 된다.
  - 인텐트를 수신해야 하는 구성요소는 다른 인텐트 정보를 기반으로 시스템이 결정하는 **암시적 인텐트**

💡 작업 [[참고 사이트 #1 - Android standard Activity Actions]]  
수행할 일반적인 작업을 나타내는 문자열.  
>✏️ 예시
>- `ACTION_VIEW` : 이 작업은 Activity 가 사용자에게 표시할 수 있는 어떤 정보를 가지고 있을 때 `startActivity()`가 있는 인텐트에서 사용.
>- `ACTION_SEND` : 공유 인텐트라고도 하며 사용자가 다른 앱을 통해 공유할 수 있는 데이터를 가지고 있을 때 `startActivity()`가 있는 인텐트에서 사용.
>```kotlin
>const val ACTION_TIMETRAVEL = "com.example.action.TIMETRAVEL"
>```

💡 데이터
- 작업을 수행할 데이터 또는 해당 데이터의 MIME 유형을 참조하는 URI(Uri 객체)이다.
- 데이터가 `content:` URI 는 데이터가 기기에 위치하고 `ContentProvider`가 제어한다.
- 데이터만 URI 설정하려면 `setData()` 호출.
- MIME 유형만 설정하려면 `setType()` 호출.
- 두가지를 다 명시적으로 설정하려면 `setDataAndType()` 호출.

💡 카테고리 [[참고 사이트 #2 - Android Standard Categories]]
- 인텐트를 처리하는 구성 요소의 종류에 관한 추가 정보를 담은 문자열이다. (~~있어도 되고 없어도 되고~~)
- 카테고리는 `addCategory()` 로 지정할 수 있음.
>✏️ 예시
> - `CATEGORY_BROWSABLE` : 해당 Activity 가 웹브라우저를 통해 시작되도록 허용하고 이미지, 이메일 등의 링크로 참조된 데이터를 표시하게 한다.
> - `CATEGPRY_LAUNCHER` : 해당 Activity가 작업의 최초 Activity이며, 시스템의 APP 시작 관리자에 목록으로 게재된다.
> ```kotlin
> <intent-filter>
>     <action android:name="android.intent.action.MAIN" />
>     <category android:name="android.intent.category.LAUNCHER" />
> </intent-filter>
> ```

💡 엑스트라
- 작업을 수행하는 데 필요한 추가 정보가 담긴 키-값 쌍이다.
- `putExtra()` 를 사용하여 데이터를 추가할 수 있다.
- 모든 엑스트라 데이터를 포함 한 `Bundle` 객체를 만든 다음 `Bundle`을 인텐트에 `putExtras()` 로 추가할 수 있다.


💡 플래그 [[참고 사이트 #3 - Android Flags]]
- 인텐트에 대한 메타데이터와 같은 기능.
- 시스템에 Activity 를 시작할 방법에 대한 지침을 줄 수 있고, Activity를 시작한 다음 어떻게 처리해야 하는지도 지정할 수 있음.

### `IntentFilter`
- 암시적 인텐트를 실행을 위해 필요한 요소이다. 
- 사용할 인텐트가 여러 종류면 그 개수만큼 인텐트 필터를 `AndroidManifest.xml` 에 만들어야 한다.

### `PendingIntent`
- `PendingIntent` 가 가지고 있는 `Intent`를 당장 수행하지 않고 특정 시점에 수행하도록 하는 특징이 있다.
- 사용 사례 : Notification(푸시알림), 바탕화면(런처)위젯, AlarmManager etc

[참고 사이트 #1 - Android standard Activity Actions]: https://developer.android.com/reference/android/content/Intent#standard-activity-actions
[참고 사이트 #2 - Android Standard Categories]: https://developer.android.com/reference/android/content/Intent#standard-categories
[참고 사이트 #3 - Android Flags]: https://developer.android.com/reference/android/content/Intent#flags
[Android 공식 사이트]: https://developer.android.com/guide/components/intents-filters?hl=ko