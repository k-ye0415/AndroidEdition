# LiveData and LiveData Observer

### 📌 LiveData

- `LiveData` 는 관찰 가능한 데이터 홀더 클래스이다.
- 관찰 가능한 일반 클래스와 달리 `LiveData`는 **수명주기를 인식**한다.
    - Activity, Fragment, Service 등 다른 앱 컴포넌트의 생명주기를 고려한다.
- `LiveData`는 활성중인 Lifecycle 상태에서 컴포넌트의 변화상태를 인지한다.
- 비활동 상태에서는 `LiveData` 데이터는 변화를 인지하지 않는다.
    - Destroyed 된 상태의 lifecycle 에서는 `LiveData` 객체를 Observe 하지 않음.
- `Observer` 클래스로 표현되는 `Observer`의 생명주기가 `STARTED` 또는 `RESUMED` 상태이면 `LiveData`는 `Observer`를 활성 상태로 간주한다.
- `LiveData`는 활성 `Observer`에게만 업데이트 정보를 알린다.
- `LiveData` 객체를 보기 위해 등록된 비활성 `Observer`는 변경사항에 관한 알림을 받지 않는다.
- LifecycleOwner 인터페이스를 구현하는 객체와 페어링된 `Observer`를 등록할 수 있다.
- 이로 인해 `Observer`에 대응하는 Lifecycle 객체의 상태가 `DESTROYED` 로 변경될 때 `Observer`를 삭제할 수 있다.
    - **특히 Activity 및 Fragment에 유용**하며 `DESTROYED` 상태로 변경 되면 `Observer`가 즉시 구독이 취소되므로 메모리 누수에 대한 걱정이 필요
      없다.

### 📌 LiveData 장점

💡 UI와 데이터 상태의 일치

- `LiveData`는 데이터가 변경될 때 `Observer` 객체에 알려준다.
- 데이터가 변경될 때 마다 `Observer`가 구독하고 있는 UI 를 업데이트 한다.

💡 메모리 누수 없음

- `Observer`는 Lifecycle 객체에 결합되어 있으며 연결된 생명주기가 끝나면 자동으로 삭제된다.

💡 중지된 활동으로 인한 비정상 종료 없음

- 활성상태가 아닌 `Observer`는 어떤 `LiveData`이벤트도 받지 않는다.

💡 생명주기를 수동으로 처리하지 않음

- UI 컴포넌트는 관련 데이터를 관찰하기만 할 뿐 관찰을 중지하거나 다시 시작하지 않는다.
- `LiveData`는 관찰하는 동안 관련 수명주기상태의 변경을 인식하므로 모든 것을 자동으로 관리한다.

💡 최신 데이터 유지

- 생명주기가 비활성화 되고, 다시 활성화 될 때 최신 데이터를 수신한다.

💡 적절한 구성 변경

- 기기회전과 같은 구성 변경으로 인해 Activity 또는 Fragment 가 다시 생성되면 사용 가능한 최신 데이터를 즉시 수신 받는다.

💡 리소스 공유

- 앱에서 시스템 서비스를 공유할 수 있도록 싱글톤 패턴을 사용하는 `LiveData` 객체를 확장하여 시스템 서비스를 랩핑할 수 있다.
- `LiveData` 객체가 시스템 서비스에 한 번 연결되면 리로스가 필요한 모든 `Observer`가 `LiveData` 객체를 볼 수 있다.

### 📌 LiveData Observer

- `LiveData` 는 `Observer` 인터페이스를 사용하여 관찰한다.
- `Observer` 인터페이스는 유일한 메서드인 `onChanged()`가 있다.
- `onChanged()` 메서드는 `LiveData` 의 값이 변경되었을 때 호출되는 콜백 메서드로 이 메서드 안에서 `LiveData` 값을 받아 사용하면 된다.
- 대부분의 경우 컴포넌트의 생명주기 중 `onCreate()` 메서드 안에서 `LiveData` 에 `Observer`를 달아 관찰한다.
    - 시스템이 Activity 또는 Fragment 의 `onResume()` 메서드에서 중복 호출을 하지 않도록 하기 위해서.
    - Activity 또는 Fragment 가 활성화 되는 즉시 표시할 데이터가 있는지 확인.  
      컴포넌트가 `STARTED` 상태에 있는 즉시 관찰 중인 `LiveData` 객체로부터 가장 최근 값을 수신.  
      (이 것은 `LiveData` 객체가 이미 `onCreate()`에서 설정된 경우에만 발생)

[[참고 사이트 #1]]  
[[참고 사이트 #2]]  
[[참고 사이트 #3]]

[참고 사이트 #1]: https://developer.android.com/topic/libraries/architecture/livedata?hl=ko

[참고 사이트 #2]: https://jutole.tistory.com/14

[참고 사이트 #3]: https://velog.io/@changhee09/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-LiveDat