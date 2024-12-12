# Tasks and Back Stack

### What is `Task` ?
- 사용자에 의해 실행된 Activity 들의 모음.
- Activity 들은 생성되면서 "`Back Stack`" 이라 불리는 Stack 에 쌓이게 됨.

![task1](../Android_image/task_1.png)

1. User Selectes App  
사용자가 홈 화면이나 앱 런처에서 앱을 실행한다.

2. App Task Foreground  
이미 진행 중인 `task`가 있는 경우 foreground 로 이동하여 현재 Activity 를 표시한다.

3. New Task Created  
진행 중인 `task`가 없는 경우 새로운 `task` 가 생성되고 앱의 `Back Stack` 에 root activity 로 MainActivity 가 표시된다.

4. Activity Pushed to Stack  
현재 머무르는 Activity 에서 다른 Activity 를 실행 했을 때, task 의 상단에 새로운 Activity 가 추가되며, 추가 된 Activity 가 표시된다.

5. Previous Activity Stopped  
task 의 이전 Activity 는 destroy 되지 않고 stopped 상태를 유지한다. 사용자가 이전 Activity 로 돌아갈 경우를 대비하여 UI 상태가 유지된다.

![task2](../Android_image/task_2.png)

6. Back Action Performed & Current Activity Popped  
사용자가 뒤로가기 버튼을 클릭하면 현재 바라보고 있는 Activity 는 task 의 상단에서 제거되어 destroy 된다. 

7. Previous Activity Resumes  
destroy 된 Activity 의 이전 Activity 가 resume 된다. 시스템에서 이전 저장된 UI 를 복원한다.

8. Task Ends  
사용자가 계속 뒤로가기 버튼을 클릭하게 되면 root activity 까지 도달하여 제거될 때까지 activity 가 하나씩 destroy 된다.모든 Activity 가 destory 되면 해당 task 도 사라진다.

> ### Back Stack  
> Back Stack 은 LIFO (후입선출) 구조이다.  
> - Activity 는 시작 될 때에만 task 에 추가 된다.
> - 뒤로가기 버튼으로 인해 닫히면 destroy 된다.
> - Activity 는 task 내에서 재배열 되지 않는다.

---


![task3](../Android_image/task_3.png)
- Task Behavior  
task 는 사용자가 다른 task 를 시작하거나 홈 화면으로 이동 할 때 background 로 이동할 수 있는 Activity 그룹이다.  
task 의 back stack 은 background 에 있는 동안 유지된다.

![task4](../Android_image/task_4.png)

- Foreground Task  
Activity 가 foreground 로 표시되면 모든 Activity 와 상태가 복원되고 사용자는 중단한 부분부터 계속 진행할 수 있다.

- Task Switching  
사용자는 홈화면 또는 최근 화면을 사용하여 Activity 간에 전환할 수 있다.  
✏️ 예시. Task B 가 시작되면 Task A 는 background 로 이동. 사용자는 나중에 Task A 로 다시 전환 할 수 있다.

- Multiple Tasks  
여러개의 Task 가 Background 에서 동시에 존재할 수 있다. 하지만, 시스템에 메모리가 부족하면 일부 background task 가 삭제될 수 있다.

- Memory Management  
삭제된 task 는 Activity 상태 값을 잃게 되므로 사용자는 Activity 를 실행할 때 다시 시작해야한다.


----
![task5](../Android_image/task_5.png)


- Activity Instances 는 재배열되지 않는다.  
  - Back Stack 의 Activity 는 절대 재배열되지 않는다. 앱에서 사용자가 여러 Activity 에서 동일한 Activity 를 시작할 수 있도록 허용하는 경우 해당 Activity 는 새 인스턴스가 생성되어 매번 Stack 에 추가된다.  
  - 이미 존재하는 Activity 를 새로운 인스턴스로 생성되어도 Stack 맨 위로 가져오지 않는다.

![task6](../Android_image/task_6.png)
- 동일한 Activity 의 여러 인스턴스
  - 재배열되지 않기 때문에 동일한 Activity 의 여러 인스턴스가 생성될 수 있다.
  - 각 인스턴스는 Back Stack 에 독립적으로 존재한다.
  - 각각 고유한 UI 상태와 데이터가 있다.

- Navigating Back
  - 사용자가 뒤로가기 버튼이나 동작을 사용하여 뒤로가는 경우
    - Activity 는 추가된 역순(후입선출)로 표시된다.
    - 각 인스턴스는 마지막으로 활성화되었을 때의 상태로 나타난다.

- Customizing Behavior
  - 동일한 Activity 가 여러번 실행되는 것을 방지하려면 다음 동작을 수정하면 된다.
    - `FLAG_ACTIVITY_SINGLE_TOP` 또는 `FLAG_ACTIVITY_CLEAR_TOP`와 같은 인텐트 플래그를 사용.
    - `AndroidManifest.xml` 파일의 Activity manifest (`singleTask`, `singleInstance` 등)에서 `launchMode`를 구성.


서로 다른 Context 에서 시작된 경우 동일한 Activity 가 여러 task 에 존재할 수 있으므로 인스턴스의 독립성이 더욱 강조된다.


### Activity and Task Lifecycle
1. Task 내에서 Activity 전환
  - 새로운 Activity
    - Activity A -> Activity B 로 시작.
      - Activity A 는 중지되었지만 시스템은 마지막 상태(✏️ 스크롤 위치, 입력 필드 등) 을 유지.
      - 사용자는 뒤로가기 버튼 또는 동작을 사용하여 Activity A 로 돌아갈 수 있으며 이때 마지막 상태값을 복원.
- 뒤로 가기
  - 사용자가 Activity B 에 있는 동안 뒤로가기 버튼을 누르는 경우
    - Activity B는 destroy 되어 stack 에서 제거된다.
    - Activity A 는 이전 상태가 복원되어 표시.

2. Task 종료
- 홈 버튼 또는 제스처 사용
  - 사용자가 현재 task 를 종료하는 경우 (✏️홈 누르기)
    - 현재 Activity 가 중지되고 task 가 background 로 이동된다.
    - 시스템은 task 의 모든 Activity 상태를 유지한다.
- task 재개
  - 사용자가 나중에 다시 앱아이콘을 선택하는 경우
    - task 가 foreground 표시된다.
    - stack 상단의 activity은 task 가 background 로 들어가기 전 상태 그대로 다시 시작된다.

3. Back Stack 동작
- 사용자가 뒤로가기 동작을 수행할 때마다 현재 표시되고 있는 Activity 상태
  - Back Stack 에서 제거된다.
  - 제거되기때문에 유지하지 않는다.
  - Stack 의 이전 Activity 가 표시된다.

4. 여러개의 Activity 인스턴스
- Activity 는 다른 task 내에서도 여러번 인스턴스화될 수 있다.
- 각 인스턴스는 자체 상태와 Back Stack 위치를 사용하여 독립적으로 동작한다.
- `launchMode` 또는 Intent flag 를 사용하여 맞춤설정할 수 있다.


### Manage tasks
- Task 및 Back Stack 사용자 정의  
매니페스트 속성 및 인텐트 플래그를 사용하여 기본 동작을 중단할 수 있음.

  - 매니페스트 속성
  1. `taskAffinity`
     - 특정 task와 Activity 의 `affinity` 또는 연관성을 정의
     - Activity 가 시작된 task 와 다른 task 에 속하도록 하는 경우에 사용.
  2. `launchMode`  
  새로운 Activity 인스턴스가 시작되는 방법을 지정.
     - `standard`(default) : 항상 새로운 인스턴스를 생성
     - `singleTop` : 인스턴스가 이미 stack 맨 위에 있는 경우 인스턴스를 재사용
     - `singleTask` : 다른 task 에서 기존 인스턴스를 재사용하고 그 위에 있는 다른 Activity 를 지움.
     - `singleInstance` : Activity 가 해당 task 에서 유일한 Activity 로 보장.
  3. `allowTaskReparenting`
     - 필요한 경우 Activity 가 다른 task 로 이동할 수 있도록 혀용.
  4. `clearTaskOnLaunch`
     - task 가 다시 시작하면 root Activity 를 제외한 task 의 모든 Activity 를 제거.
  5. `alwaysRetainTaskState`
     - 사용자가 task 를 떠났다 다시 돌아와도 task 상태를 유지.
  6. `finishOnTaskLaunch`
     - task 가 다시 시작될 때 Activity 가 완료되고 task 에서 제거.
  <br>
  <br>
  - 인텐트 플래그
  1. `FLAG_ACTIVITY_NEW_TASK`
     - 현재 task 에 Activity 를 추가하는 대신 새로운 task 에서 Activity 를 시작.
  2. `FLAG_ACTIVITY_CLEAR_TOP`
     - 기존 Activity 를 stack 맨 위로 배치.
  3. `FLAG_ACTIVITY_SINGLE_TOP`
     - Activity 가 이미 stack 상단에 있는 경우 새로운 인스턴스를 생성하는 대신 해당 인스턴스를 재사용.