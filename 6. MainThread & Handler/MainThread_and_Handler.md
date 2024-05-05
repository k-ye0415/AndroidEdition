# Main Thread + Handler

## 📌 일반적인 Main Thread
- 프로세스 실행 중의 필요에 따라 `Thread`가 생성 및 실행된다.
- `Thread`는 기존에 이미 실행되어있는 다른 `Thread`에 의해 생성 및 실행한다.
- **최초의 `Thread`를 `Main Thread`라고 부른다.** 이 `Thread`가 생성되고 시작되는 곳을 `main()` 함수라고 부른다.
- 프로세스가 시작되어 프로세스의 시작점인 `main()`함수에서 실행되는 최초의 `Thread`가 `Main Thread`가 된다.

## 📌 Android의 메인스레드
- 앱에 포함된 액티비티 중 하나를 런처로 지정함으로써 앱의 시작점, 즉 앱 프로세스의 시작점을 지정해줄 수 있다.
- `AndroidManifest.xml` 에 작성되는 런처를 지정하는 부분.
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
       ...
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
- 안드로이드 앱의 `main()`은 안드로이드 프레임워크에 존재한다.
  - 안드로이드 프래임워크 내부 클래스인 `android.app.ActivityThread`가 애플리케이션의 메인 클래스다.
  - `ActivityThread`의 `main()`메서드가 애플리케이션의 시작 지점이다.
  - `ActivityThread`는 어떤 것도 상속하지 않은 클래스이다.
  - Activity만 관련되어있는 것이 아닌 모든 컴포넌트들이 다 관련되어 있다.
  ```kotlin
    // ActivityThread.java
    // AppCompatActivity → FragmentActivity → ComponentActivity → androidx.core.app.ComponentActivity → Activity → ActivityThread

    public static void main(String[] args) {
        /* ..*/
        Looper.prepareMainLooper();  // 1번
        /* .. */
        ActivityThread thread = new ActivityThread();
        thread.attach(false, startSeq);

        if (sMainThreadHandler == null) {
            sMainThreadHandler = thread.getHandler();
        }
		/* .. */   
		Looper.loop(); // 2번

        throw new RuntimeException("Main thread loop unexpectedly exited");
    }
  ```
- 안드로이드의 메인스레드의 주요 업무 중엔 UI 작업도 포함된다.
- 안드로이드 메인스레드가 UI 작업을 할 땐, **단일 스레드 모델**이 적용된다.


## 📌 단일 스레드 모델
- UI 작업에 있어 경합상태, 교착 상태를 방지하고자 메인스레드엔 단일 스레드 모델이 적용된다.
  - 하나의 위젯에 멀티 스레드를 사용한다고 하면 해당 상태의 문제가 발생 할 수 있다.
  - 하나의 Activity에 N개의 위젯이 있고 각 위젯에 대한 작업을 위해 N개의 멀티 스레드를 사용한다고 가정하면, 각 위젯이 그려지거나 업데이트 되는 순서를 보장할 수 없게 된다.
  > 단일 스레드 모델은 자원 접근에 대한 동기화를 신경쓰지 않아도 되고 작업전환(Context switching) 비용을 요구하지 않음으로 경합 상태와 교착 상태를 방지할 수 있다.

- 안드로이드에서 단일 스레드 모델이란 안드로이드 화면을 구성하는 뷰나 뷰그룹을 하나의 스레드에서만 담당하는 원칙을 말한다.
> 단일 스레드 모델의 규칙
> 1. 메인스레드(UI 스레드)를 블럭하지 말 것
> 2. 안드로이드 UI 툴킷은 오직 메인스레드(UI 스레드)에서만 접근할 수 있도록 할것

- UI 작업은 단일 스레드 환경에서만 이뤄져야하며 UI 작업을 맡고 있는 메인스레드가 UI 작업을 할 땐, 단일 스레드 원칙에 맞게 동작해야 한다.
  - UI 작업은 메인스레드에서만 이뤄져야 한다. 메인스레드가 UI작업을 할 수 있는 유일한 스레드 인것 ️

## 📌 Looper 와 Handler 의 사용 목적
예를 들어 메인스레드, 다른 스레드 두 개 이상의 스레드가 동시에 같은 TextView에 `setText()`를 시도하는 경우
두 개의 스레드 중 `setText()`가 적용될 지 예측할 수 없고, 사용자는 둘 중 하나의 값만을 볼 수 있어 다른 한 스레드의 결과는 버려진다.
이와 같이 두 개 이상의 스레드를 사용할 때의 동기화 이슈를 차단하기 위해서 Looper 와 Handler 를 사용한다.  
![thread_img_1.png](https://github.com/k-ye0415/AndroidEdition/blob/main/Android_image/thread_img_1.png)


## 📌 Looper 와 Handler 작동 원리
- 메인스레드는 내부적으로 Looper 를 가지며 그 안에는 `Message Queue`가 포함된다.
- `Message Queue`는 스레드가 다른 스레드나 혹은 자기 자신으로부터 전달받은 `Message`를 기본적으로 선입선출 형식으로 보관하는 `Queue`이다.
- Looper 는 `Message Queue`에서 `Message`나 `Runnable` 객체를 차례로 꺼내 `Handler`가 처리하도록 전달한다.
- `Handler`는 Looper 로 부터 받은 `Message`를 실행, 처리하거나 다른 스레드로부터 메세지를 받아 `Message Queue`에 넣는 역할을 한다.
  ![thread_img_2.png](https://github.com/k-ye0415/AndroidEdition/blob/main/Android_image/thread_img_2.png)

## 📌 Handler
- Handler 는 스레드의 `Message Queue`와 연계하여 `Message`나 `Runnable` 객체를 받거나 처리하여 스레드 간의 통신을 할 수 있다.
- Handler 객체는 하나의 스레드와 해당 스레드의 `Message Queue`에 종속된다.
- 새로 Handler 객체를 만든 경우 이를 만든 스레드와 해당 스레드의 `Message Queue`에 바인드 된다.
- 다른 스레드가 특정 스레드에게 메시지를 전달하려면  특정 스레드에 속한 `Handler`의 `post`나 `sendMessage`등의 메서드를 호출하면 된다.
- 외부, 혹은 자기 스레드로부터 받은 메시지를 어떤 식으로 처리할 지는 `handleMessage()`메서드를 구현하여 정한다.
- `sendMessage()` 나 `post()`로 특정 Handler 에게 메세지를 전달할 수 있고, 재귀적인 호출도 가능하므로 딜레이를 이용한 타이머나 스케줄링 역할도 할 수 있다.

💡 전달 시점에 다른 메서드를 사용하여 `Queue`의 맨 위로 보내거나, 원하는 만큼 `Message`나 `Runnable` 객체의 전송을 지연 시킬 수 있다.
<table>
<tr>
<td>리턴</td>
<td>메소드</td>
<td>인자</td>
<td>설명</td>
</tr>
<tr>
<td>void</td>
<td>handleMessage</td>
<td>Message msg</td>
<td><code>Looper</code>가 <code>Message Queue</code>에서 꺼내준 <code>Message</code>나 <code>Runnable</code> 객체를 처리.
<br><strong>(상속 시 구현 필수)</strong></td>
</tr>
<tr>
<td>final boolean</td>
<td>post</td>
<td>Runnable r</td>
<td>
<code>Message Queue</code> 에 <code>Runnable r</code>을 전달.
</td>
</tr>
<tr>
<td>final boolean</td>
<td>sendMessage</td>
<td>Message msg</td>
<td><code>Message Queue</code> 에 <code>Message msg</code>을 전달.</td>
</tr>
<tr>
<td>final boolean</td>
<td>postAtFrontOfQueue</td>
<td>Runnable r</td>
<td>
<code>Message Queue</code>의 <strong>맨 앞</strong>에 <code>Runnable r</code>을 전달.
</td>
</tr>
<tr>
<td>final boolean</td>
<td>sendMessageAtFrontOfQueue</td>
<td>Message msg</td>
<td>
<code>Message Queue</code>의 <strong>맨 앞</strong>에 <code>Message msg</code>을 전달.
</td>
</tr>
<tr>
<td>final boolean</td>
<td>postDelayed</td>
<td>Runnable r, long delayMillis</td>
<td>
<strong>delayMillis 만큼 지연 후</strong><code>Message Queue</code> 에 <code>Runnable r</code>을 전달.
</td>
</tr>
<tr>
<td>final boolean</td>
<td>sendMessageDelayed</td>
<td>Message msg, long delayMillis</td>
<td>
<strong>delayMillis 만큼 지연 후</strong><code>Message Queue</code> 에 <code>Message msg</code>을 전달.
</td>
</tr>
</table>

## 📌 Looper 와 Message Queue
- Looper 는 무한히 루프를 돌며 자신이 속한 스레드의 `Message Queue`에 들어온 `Message` 나 `Runnable` 객체를 차례로 꺼내서 이를 처리할 `Handelr`에 전달하는 역할이다.
- 메인스레드는 Looper가 기본적으로 생성돼 있지만, 새로 생성한 스레드는 기본적으로 Looper를 가지고 있지 않고 단지 `run`메서드만 실행한 후 종료하기 때문에 메시지를 받을 수 없다.
- 기본 스레드에서 메세지를 전달받으려면 `prepare()` 메서드를 통해 Looper를 생성하고 `loop()`메서드를 통해 Looper가 무한히 루프를 돌며 `Message Queue`에 쌓인 `Message` 나 `Runnable` 객체를 꺼내 Handler에 전달하도록 한다.
- 활성화된 Looper는 `quit()` 나 `quitSafely()`메소드로 중단할 수 있다.
- `quit()`메소드가 호출되면 Looper 는 즉시 종료되고, `quitSafely()` 메소드가 호출되면 현재 `Message Queue`에 쌓인 메세지들을 처리 한 후 종료한다.

![thread_img_3.png](https://github.com/k-ye0415/AndroidEdition/blob/main/Android_image/thread_img_3.png)

## 📌 Message 와 Runnable
- Message 란 
  - 스레드 간 통신할 내용을 담는 객체이자 `Queue`에 들어갈 일감의 단위로 Handler를 통해 보낼 수 있다.
  - 일반적으로 Message 가 필요할 때 새 Message 객체를 생성하면 성능 이슈가 생길 수 있으므로 안드로이드가 시스템에 만들어 둔 `Message Pool` 객체를 재사용한다.
  - `obtain()` 메소드는 handler와 다른 인자들을 담은 Message 객체를 리턴한다.
- Runnable 생성 방법
  - 새 스레드는 `Thread()` 생성자로 만들어서 내부적으로 `run()` 구현
  - `Tread(Runnable runable)`생성자로 만들어서 Runnable 인터페이스를 구현한 객체를 생성하여 전달.  
  (Runnable로 스레드의 `run()`메서드를 분리 한것. 따라서 Runnable 인터페이스는 `run()` 추상 메서드를 가지고 있으므로 상속받는 클래스는 `run()` 코드를 반드시 구현해야한다.)

💡 Message가 `int` 나 `Object` 같이 **스레드간 통신할 내용**을 담는다면, Runnable은 실행할 **`run()`메소드와 그 내부에서 실행될 코드**를 담는다는 차이가 있다.

## 📌 HandlerThread
- HandlerThread 는 Looper를 기본으로 가지지 않는 불편함을 개선하기 위해 생성할 때 Looper를 자동으로 보유한 클래스이다. 
- HandlerThread 는 일반적인 스레드를 확장한 클래스로 내부에 반복해서 루프를 도는 Looper를 가진다.
- 자동으로 Looper 내부의 Message Queue도 생성되므로 이를 통해 스레드로 `Message`나 `Runnable`을 전달 받을 수 있다.

[[참고사이트 #1]]  
[[참고사이트 #2]]  
[[참고사이트 #3]]


[참고사이트 #1]: https://velog.io/@sery270/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EB%A9%94%EC%9D%B8%EC%8A%A4%EB%A0%88%EB%93%9C
[참고사이트 #2]: https://academy.realm.io/kr/posts/android-thread-looper-handler/
[참고사이트 #3]: https://developer.android.com/reference/android/os/Message