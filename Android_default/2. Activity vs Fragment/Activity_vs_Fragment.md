# Activity, Fragment의 차이점

[//]: # ([[참고 사이트#1]])
[//]: # (<img src="activity_fragment_img.png" width="300"/>  <br>)

[//]: # (Activity 가 방안의 바닥이라면, View 라는 가구를 바닥 위에 배치할 수 있다.)

[//]: # (마찬가지로 Fragment 도 Activity 또는 다른 Fragment 위에 있어야 한다.)

[//]: # ()
[//]: # (하지만, Activity 와 View 만 있어도 하나의 방을 만들 수 있는데 왜 Fragment 가 필요할까?)

[//]: # ()
[//]: # (예를 들면,  )

[//]: # (새로 이사한 방&#40;Activity&#41; 에 가구 &#40;View&#41;를 채워야 한다.)

[//]: # (기존에 사용 하였던 가구&#40;View&#41;의 색상은 Black색상의 가구&#40;View&#41;들이기 때문에 도배&#40;Fragment&#41;가 되지 않은 방&#40;Activity&#41;에 배치를 하니)

[//]: # (너무 칙칙하고 아름답지가 않다.  )

[//]: # (이때 새로운 도배&#40;Fragment&#41; 를 하얀색, 파스텔 톤으로 다시 구성하니 기존 사용하던 가구&#40;View&#41; 가 더 돋보이기 시작했다.)

[//]: # ()
[//]: # (위에 예시는 상당히 View 에 종속되어 있는 예시이지만, 기능적으로 따지고 봐도 별 차이가 없어보인다.)



## 1. Activity [[Andorid 4대 컴퍼넌트 - Activity]]

- Activity는 `main()`메서드를 사용하여 앱을 실행하는 프로그래밍 패러다임과 다른 안드로이드 시스템의 특수성에 의해 생긴 개념.
- Activity는 앱과 사용자의 상호작용을 위한 진입점 역할과 동시에 하나의 UI 화면을 그리는 Container 역할을 수행.

## 2. Fragment

- Fragment는 독립적으로 존재 할 수 없다.
- 반드시 Activity 또는 다른 Fragment 에 정의 되어있어야 한다.
- Fragment 는 Activity에 종속되어 있으면서, 자체적으로 생명주기를 가지고 있다.
- Fragment 는 다양한 UI 를 모듈화하여 재사용하고 화면 구성을 더욱 쉽게 할 수 있도록 도와준다.

[참고 사이트#1]: https://milkye.tistory.com/60
[Andorid 4대 컴퍼넌트 - Activity]: https://gitlab.com/y_e/androidapplab/-/blob/main/JINA/Theory/1.%20Android/1.%20Android%20Components/Android%20Components_Activity.md?ref_type=heads