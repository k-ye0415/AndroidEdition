# Android Permission
_2023/09/05 기준_  
\[[Android 공식 사이트 Manifest.permission](https://developer.android.com/reference/android/Manifest.permission)\]

### Bluetooth

<table>
<tr>
<td>

`BLUETOOTH`
</td>
<td>앱이 페어링된 블루투스 기기에 연결 할 수 있도록 허용</td>
</tr>
<tr>
<td>

`BLUETOOTH_ADMIN`
</td>
<td>앱이 블루투스 기기를 검색하고 페어링할 수 있도록 허용</td>
</tr>
<tr>
<td rowspan="2">

`BLUETOOTH_SCAN`
</td>
<td>주변 블루투스 기기를 검색하고 페어링하는데 필요</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td rowspan="2">

`BLUETOOTH_ADVERTISE`
</td>
<td>주변 블루투스 장치에 알릴 수 있어야 함</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td rowspan="2">

`BLUETOOTH_CONNECT`
</td>
<td>페어링된 블루투스 기기에 연결할 수 있어야 함</td>
</tr>
<tr>
<td>

[`Build.VERSION_CODES.S`](https://developer.android.com/reference/android/os/Build.VERSION_CODES#S)이상의 API 레벨부터 Runtime 권한이 필요하다.
</td>
</tr>
</table>

### Call

<table>
<tr>
<td rowspan="2">

`ANSWER_PHONE_CALLS`
</td>
<td>앱이 수신 전화에 응답하도록 허용</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td rowspan="2">

`CALL_PHONE`
</td>
<td>사용자가 전화를 확인하기 위해 다이얼러 사용자 인터페이스를 거치지 않고 애플리케이션이 전화 통화를 시작할 수 있도록 합니다.</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td rowspan="2">

`READ_PHONE_STATE`
</td>
<td>

현재 셀룰러 네트워크 정보, 진행 중인 통화 상태 및 장치에 등록된[`PhoneAccount`](https://developer.android.com/reference/android/telecom/PhoneAccount) 의 목록을 포함하여 전화 상태에 대한 읽기 전용 액세스를 허용합니다.
</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
</table>

### Network

<table>
<tr>
<td>

`INTERNET`
</td>
<td>애플리케이션이 네트워크 소켓을 열 수 있도록 허용</td>
</tr>
<tr>
<td>

`ACCESS_NETWORK_STATE`
</td>
<td>애플리케이션이 네트워크에 대한 정보에 액세스하도록 허용</td>
</tr>
<tr>
<td>

`ACCESS_WIFI_STATE`
</td>
<td>애플리케이션이 Wi-Fi 네트워크에 대한 정보에 액세스하도록 허용</td>
</tr>
<tr>
<td>

`CHANGE_WIFI_STATE`
</td>
<td>애플리케이션이 Wi-Fi 연결 상태를 변경할 수 있도록 허용</td>
</tr>
<tr>
<td>

`CHANGE_NETWORK_STATE`
</td>
<td>애플리케이션이 네트워크 연결 상태를 변경할 수 있도록 합니다.</td>
</tr>
</table>

### BroadCast

<table>
<tr>
<td>

`RECEIVE_BOOT_COMPLETED`
</td>
<td>

애플리케이션이 [`Intent.ACTION_BOOT_COMPLETED`](https://developer.android.com/reference/android/content/Intent#ACTION_BOOT_COMPLETED) 시스템 부팅이 완료된 후 브로드캐스트되는 를 수신하도록 허용
</td>
</tr>
</table>

### Display

<table>
<tr>
<td>

`WAKE_LOCK`
</td>
<td>PowerManager WakeLocks를 사용하여 프로세서를 잠자기 상태로 유지하거나 화면이 어두워지는 것을 방지할 수 있습니다.</td>
</tr>
<tr>
<td>

`SYSTEM_ALERT_WINDOW`
</td>
<td>

앱이 [`WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY`](https://developer.android.com/reference/android/view/WindowManager.LayoutParams#TYPE_APPLICATION_OVERLAY) 다른 모든 앱 위에 표시되는 유형을 사용하여 창을 만들 수 있도록 허용
</td>
</tr>
</table>

### Storage

<table>
<tr>
<td rowspan="2">

`WRITE_EXTERNAL_STORAGE`
</td>
<td>애플리케이션이 외부 저장소에 쓸 수 있도록 허용</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td rowspan="2">

`READ_EXTERNAL_STORAGE`
</td>
<td>애플리케이션이 외부 저장소에서 읽을 수 있도록 허용</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td rowspan="4">

`READ_MEDIA_AUDIO`
</td>
<td>애플리케이션이 외부 저장소에서 오디오 파일을 읽을 수 있도록 허용</td>
</tr>
<tr>
<td>

[`Build.VERSION_CODES.TIRAMISU`](https://developer.android.com/reference/android/os/Build.VERSION_CODES#TIRAMISU) 이상의 API 레벨부터 적용된다. <br>따라서 `READ_EXTERNAL_STORAGE` 권한은 필요하지 않다.
</td>
</tr>
<tr>
<td>

[`Build.VERSION_CODES.TIRAMISU`](https://developer.android.com/reference/android/os/Build.VERSION_CODES#TIRAMISU) 이하의 API 레벨은 `READ_EXTERNAL_STORAGE`권한이 필요하다.
</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td rowspan="4">

`READ_MEDIA_IMAGES`
</td>
<td>애플리케이션이 외부 저장소에서 이미지 파일을 읽을 수 있도록 허용</td>
</tr>
<tr>
<td>

[`Build.VERSION_CODES.TIRAMISU`](https://developer.android.com/reference/android/os/Build.VERSION_CODES#TIRAMISU) 이상의 API 리벨부터 적용된다. <br>따라서 `READ_EXTERNAL_STORAGE` 권한은 필요하지 않다.
</td>
</tr>
<tr>
<td>

[`Build.VERSION_CODES.TIRAMISU`](https://developer.android.com/reference/android/os/Build.VERSION_CODES#TIRAMISU) 이하의 API 레벨은 `READ_EXTERNAL_STORAGE`권한이 필요하다.
</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td rowspan="4">

`READ_MEDIA_VIDEO`
</td>
<td>애플리케이션이 외부 저장소에서 동영상 파일을 읽을 수 있도록 허용</td>
</tr>
<tr>
<td>

[`Build.VERSION_CODES.TIRAMISU`](https://developer.android.com/reference/android/os/Build.VERSION_CODES#TIRAMISU) 이상의 API 리벨부터 적용된다. <br>따라서 `READ_EXTERNAL_STORAGE` 권한은 필요하지 않다.
</td>
</tr>
<tr>
<td>

[`Build.VERSION_CODES.TIRAMISU`](https://developer.android.com/reference/android/os/Build.VERSION_CODES#TIRAMISU) 이하의 API 레벨은 `READ_EXTERNAL_STORAGE`권한이 필요하다.
</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
</table>

### Notification

<table>
<tr>
<td rowspan="2">

`POST_NOTIFICATIONS`
</td>
<td>앱이 알림을 게시하도록 허용</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
</table>

### Work Task

<table>
<tr>
<td rowspan="2">

`REORDER_TASKS`
</td>
<td>애플리케이션이 작업의 순서를 변경할 수 있도록 합니다.</td>
</tr>
<tr>
<td>note) 액티비티가 작업 스택에서 어떻게 재정렬되는지에 대한 플래그입니다. 이 플래그를 사용하면 액티비티가 새로운 작업으로 시작될 때 작업 스택에서 다른 액티비티를 재정렬할 수 있습니다.</td>
</tr>
</table>

### Location

<table>
<tr>
<td rowspan="2">

`ACCESS_COARSE_LOCATION`
</td>
<td>앱이 대략적인 위치에 액세스하도록 허용</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td rowspan="2">

`ACCESS_FINE_LOCATION`
</td>
<td>앱이 정확한 위치에 액세스하도록 허용</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
</table>

### Battery

<table>
<tr>
<td>

`REQUEST_IGNORE_BATTERY_OPTIMIZATIONS`
</td>
<td>

[`Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS`](https://developer.android.com/reference/android/provider/Settings#ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS) 를 사용하기 위해 애플리케이션이 보유해야 하는 권한
</td>
</tr>
</table>

### For T2 Device
 - 참고 : [Hard Key]([set] Hard Key)
<table>
<tr>
<td>

`READ_SETTINGS`
</td>
<td>안드로이드 앱에서 기기 설정 정보를 읽을 수 있는 권한
</td>
</tr>
</table>

### Audio

<table>
<tr>
<td rowspan="2">

`RECORD_AUDIO`
</td>
<td>애플리케이션이 오디오를 녹음할 수 있도록 허용</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
<tr>
<td>

`MODIFY_AUDIO_SETTINGS`
</td>
<td>애플리케이션이 전역 오디오 설정을 수정할 수 있도록 허용</td>
</tr>
</table>

### Camera

<table>
<tr>
<td rowspan="2">

`CAMERA`
</td>
<td>카메라 장치에 액세스하는 데 필요</td>
</tr>
<tr>
<td>

`Protection level: dangerous`<br>Runtime 권한 필요
</td>
</tr>
</table>

### APN \[[관련 Android 공식 사이트](https://source.android.com/docs/core/connect/ims-single-registration?hl=ko)\]

<table>
<tr>
<td>

`CONNECTIVITY_USE_RESTRICTED_NETWORKS`
</td>
<td>

데이터 트래픽을 설정하고 관리하기 위해 `ConnectivityManager`를 사용하여 IMS APN에 액세스하려면 앱이 [`android.permission.CONNECTIVITY_USE_RESTRICTED_NETWORKS`](https://android.googlesource.com/platform/frameworks/base/+/master/core/res/AndroidManifest.xml#1909) 권한도 요청해야 합니다.
</td>
</tr>
<tr>
<td>

`WRITE_APN_SETTINGS`
</td>
<td>애플리케이션이 apn 설정을 작성하고 사용자 및 비밀번호와 같은 기존 apn 설정의 중요한 필드를 읽을 수 있도록 허용</td>
</tr>
</table>

### Service

<table>
<tr>
<td>

`FOREGROUND_SERVICE`
</td>
<td>

일반 응용 프로그램이 [`Service.startForeground`](https://developer.android.com/reference/android/app/Service#startForeground(int,%20android.app.Notification)) 사용 할 수 있도록 하는 것.
</td>
</tr>
</table>

