# Context

### 📌 Context 란
- 어플리케이션의 현재 상태를 나타낸다.
- Activity와 어플리케이션의 정보를 얻기 위해 사용할 수 있다.
- 리소스, 데이터베이스, Shared preference 등 접근하기 위해 사용할 수 있다.
- Activity와 어플리케이션 클래스는 Context 클래스를 확장한 클래스이다.

### 📌 `Application Context`
- Application Context 는 싱글톤 인스턴스이며, Activity에서 `getApplicationContext()`를 통해 접근할 수 있다.
- 해당 Context는 Application 라이프사이클에 묶여있으며, 현재 Context가 종료된 이후에도 Context가 필요한 작업이나 **Activity 스코프를 벗어난 Context가 필요한 작업에 적합하다.**
- 💡 `ContentProvide`를 상속한 클래스에서 `getContext()`를 통해 불러올 수 있는 Context 는 `Application Context`이다. 
>✏️ Note  
> **어플리케이션에 싱글톤 오브젝트를 생성한 후 해당 오브젝트에 Context가 필요하다면  Application Context 를 전달 해야 한다.**  
> Activity Context를 전달하게 된다면, 해당 오브젝트가 Activity를 참조하기 때문에 Activity가 화면에 표시되지 않는 순간에도 [`Garbage Collection`](https://coding-factory.tistory.com/829)이 진행되지 않아 메모리 누수가 발생한다.   
> 또한, 어플리케이션 전체에서 사용할 라이브러리를 특정 Activity에서 초기화 할 때에도 Application Context 을 전달하는 것이 좋다.

>📍 Example  
> Database 를 담당하는 싱글톤 클래스에서는 `Application Context` 를 사용해야 한다.  
> Activity Context 를 전달하게 된다면, Activity 를 사용하지 않는 경우에도 불필요하게 Activity 를 참조하며 메모리 누수가 발생한다.

> 🚨 `getApplicationContext()` 쓰면 안되는 경우  
> - Application Context 는 Activity Context 가 제공하는 기능 전체를 제공하지 않는다.   
> (GUI 와 관련된 Context 조작은 실패할 확률이 높다.)  
> - Application Context 가 사용자 호출로 생성된, clean up 되지 않은 객체를 가지고 있다면 메모리 누수가 발생할 수 있다.   
> (Activity 객체는 `Garbage Collection`이 가능하지만 Application 오브젝트는 프로세스가 살아있는동안 남아 있다.)

### 📌 `Activity Context`
- Activity Context 는 Activity 내에서 유효한 Context이다.
- 그러므로 Activity Lifecycle과 연결 되어있다.
- Activity Context 는 Activity와 함께 소멸해야 하는 경우에 사용한다.
> ✏️ Note
> Activity 와 Lifecycle이 같은 오브젝트를 생성해야 할 때 Activity Context를 사용한다.

>📍 Example  
> `Toast`, `Dialog` 등 UI operation 에서 Context 가 필요할 때 사용해야 한다.


### 📌 `Application Context` vs `Activity Context`
![context_img.png](https://github.com/k-ye0415/AndroidEdition/blob/01661a7148fa349c7a069c781344960f05144165/Android_default/Android_image/context_img.png)
- Application Context 는 MyApplication, MainActivity1, MainActivity2 모두 사용 가능.
- MainActivity1 의 Context는 MainActivity1 에서만 사용 가능.
- MainActivity2 의 Context는 MainActivity2 에서만 사용 가능.

[[참고사이트 #1]]  
[[참고사이트 #2]]



[참고사이트 #1]: https://amitshekhar.me/blog/context-in-android-application
[참고사이트 #2]: https://roomedia.tistory.com/entry/Android-Context%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%BC%EA%B9%8C
