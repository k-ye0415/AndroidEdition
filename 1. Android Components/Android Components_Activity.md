# Android 4대 컴포넌트

## 1. Activity

- UI 화면을 담당하는 컴포넌트
- [Android 공식 사이트 - Activity]
    - `main()` 메소드를 사용하여 앱을 실행하는 프로그래밍 패러다임과 달리 `Activity` 는 생명 주기에 따라 콜백 메서드를 호출하여 코드를 시작.
      > ✏️ 예시
      >```dart
        >import 'package:flutter/material.dart';
        >
        >void main() => runApp(MyApp());
        >
        >class MyApp extends StatelessWidget {
        >const MyApp({ Key? key }) : super(key: key);
        >
        >@override
        >Widget build(BuildContext context) {
        >    return MaterialApp(
        >    title: 'First app',
        >    theme: ThemeData(
        >        primarySwatch: Colors.blue
        >    ),
        >    home: MyHomePage(),
        >    );
        >   }
        >}
        >```
      > 상단 예시는 옛날에 Flutter 공부하던 것이 생각나서 비교가 가능할 것 같아 가져왔다.  
      > `void main()` 함수가 없다면 하단의 코드를 다 작성하여도 앱이 실행하지 않았던 것 같다.
      >
      > 안드로이드의 생명주기
      > `onCreate()` ➡️ `onStart()` ➡️ `onResume()` ➡️ `onPause()` ➡️ `onStop()` ➡️ `onDestroy()`

    - `Activity`는 manifest 에 선언해야지 사용할 수 있다.
      > ```xml
        ><manifest ... >
        >   <application ... >
        >       <activity android:name=".ExampleActivity" />
        >       ...
        >   </application ... >
        >   ...
        ></manifest >
        >```
      >
      > 앞서 Flutter 의 `main()` 함수처럼 안드로이드도 처음으로 실행 될 `Activity` 를 `manifest`에 선언 해줘야 한다.
      >```xml
        ><application
        >   ...
        >   <activity
        >    android:name=".MainActivity"
        >    android:exported="true">
        >    <intent-filter>
        >        <action android:name="android.intent.action.MAIN" />
        >        <category android:name="android.intent.category.LAUNCHER" />
        >    </intent-filter>
        >   </activity>
        ></application>
        >```

[Android 공식 사이트 - Activity]: https://developer.android.com/guide/components/activities/intro-activities?hl=ko