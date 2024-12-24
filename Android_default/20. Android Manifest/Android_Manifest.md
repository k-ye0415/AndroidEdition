# Android Manifest

## Manifest 란?
Android studio 에서 사용되는 Manifest 는 특별한 설정이 없다면 무조건 생성되는 파일로 의미 그대로 Android Studio 에서 `Build`를 명확하게 보여주는 파일이다.

Android Sutido 는 코드 편집만 가능한 IDE 일 뿐이고, 빌드는 `gradle` 파일에서 설정하고 AAR(Android ARchive) libraries 나 JAR(Java ARchive) libraries 등을 받아서 수행하기 때문에 코드 작성과 빌드가 구분되어있기 때문에 `Build`를 명확하게 보여주고 설명한다.

이처럼 분리되어 있는 코드와 빌드에 대한 연관 관계 및 코드 수행 시 알아야할 필수 정보들을 모아놓은 파일이 `Manifest` 이다.

> ✏️ Manifest == Android studio build 설정 파일

## Manifest 구성
- `AndroidManifest.xml` 명칭으로 프로젝트 내부에 있어야 한다.
- 별도의 지정을 하지 않으면 `src/main` 의 경로로 지정되지만, `manifest` 경로로 지정되기도 한다.
- 메니페스트 파일은 Android 빌드 도구, Android 운영체제 및 Google play 에 앱에 관한 필수 정보를 설명한다.

### Manifest 필수 항목
- [앱의 패키지 이름](#앱의-패키지-이름-apk-name)
- [앱의 구성 요소](#앱의-구성-요소-app-components)(모든 Activity, Service, Broadcast Receiver, Content provider)
- 앱이 시스템 또는 다른 앱의 보호된 부분에 액세스하기 위해 [필요한 권한](#권한permission).
- 앱에 필요한 [하드웨어 및 소프트웨어 기능](#기기-호환성device-compatibility)

#### 앱의 패키지 이름 (APK name)
앱의 패키지 이름, APK(Android aPplication Package) 이름에 대한 선언이 필요하다.
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.project">     // APK 이름 선언
...
    <activity
        android:name=".MainActivity"
        android:exported="true">
...
```
앱의 패키지 이름의 역할.
- App Resource 에 접속하는데  사용되는 `R` 클래스의 네임 스페이스(개체를 구분할 수 있는 범위, 변수명, 함수명 등을 의미) 로 적용.  
(✏️ `com.example.project.R`)
- 선언된 다른 코드들의 상대경로가 된다.  
(✏️ MainActivity 의 최종 경로는 `com.example.project.MainActivity`)

#### 앱의 구성 요소 (App Components)
Android 4대 컴포넌트들을 사용하고 싶다면 Manifest 에 선언해주어야 한다.
|태그|설명|
|--|--|
|`<activity>`|Activity 이름 및 내용|
|`<service>`|Service 이름 및 내용|
|`<receiver>`|Broadcast Receiver 이름 및 내용|
|`<provider>`|Provider 이름 및 내용|

해당 컴포넌트들은 인텐트에 의해 활성화 된다.  
✏️ `<activity>` 태그 내부에 `<intent>` 태그로 액션과 카테고리를 지정
```xml
<application
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:theme="@style/Theme.MyApp">
    <activity android:name=".MainActivity">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
</application>

```
📌 `intent-filter`  
앱에서 엔텐트를 시스템에 전달하면 시스템은 각 앱의 매니페스트에 선언된 intetn-filter 를 확인하여 해당 인텐트를 처리할 수 있는 인텐트를 포함한 컴포넌트를 찾게 된다. 만약, 여러 개의 앱이 인텐트를 다를 수 있다면, 사용자가 해당 인텐트를 어떤 앱에게 넘길지 선택할 수 있게 된다.

📌 `application`  
매니페스트에 선언된 컴포넌트들과 `<application>` 은 유저에게 보여줄 수 있는 icon 과 label 속성을 가지고 있다. xml 로 구성하면 트리 구조가 되어 <부모-자식> 관계가 생기는데, 자식 element 에 icon 과 lebel 이 설정되어 있지 않다면 부모에 설정된 값이 기본값으로 들어가게 된다.


#### 권한(Permission)
앱이 시스템 또는 다른 앱의 보호된 부분에 엑세스 하기 위해 필요한 권한.
이때 다른 앱이 이 앱의 콘텐츠에 엑세스하고자 하는 경우 반드시 있어야 하는 모든 권한 역시 매니페스트에 선언되어야 한다.  
✏️ [관련 자료](../20.%20Android%20Manifest/Android_permission.md)


#### 기기 호환성(Device Compatibility)
매니페스트 파일에서 앱에 필요한 하드웨어 또는 스프트웨어 기능을 선언할 수 있다. 또한, 앱과 호환되는 기기 유형도 선언 가능하다. Google Play Store 에서는 앱에 기기 호환성에서 선언한 내용이 충족되지 않은 기기에는 앱 설치를 허용하지 않게 된다.  
>✏️ AR 기능을 사용하는 앱의 매니페스트에 AR 기능을 지원하는 기기가 필요하다고 선언한다면 AR 기능이 없는 기기는 Goolge Play Store 에서 다운로드 받을 수 없게 된다.
기기 호환성에는 Android 버전인 SDK 역시 선언할 수 있으나 Android Studio 에서는 `build.gradle` 파일에서 `minSdkVersion` 속성으로 재정의 할 수 있으므로 안드로이드 최소버전 설정은 `build.gradle` 파일에서 하는게 좋다.


[참고 자료](https://velog.io/@victorywoo/Android-Manifest%EB%9E%80)