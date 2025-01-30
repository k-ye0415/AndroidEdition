package volume01;

public class Chapter_14_CustomRuntime {
	public static void main(String[] args) {
		Chapter_14_CustomRuntime customRuntime = new Chapter_14_CustomRuntime();
	
	try {
		customRuntime.doSomething();
	} catch (Chapter_14_CustomRuntimeException e) {
		System.err.println("만약에 이렇게 try/catch 문으로 감싸게 되면 모든 Log 다 출력 될 것임! " + e.getMessage());
	}
		
		
		System.out.println("컴파일 시에는 정상적으로 되지만, 해당 main 메소드가 실행 될 때 throw 에 걸려 해당 log 는 출력되지 않을 것임.");
	}
	
	public void doSomething() {
		throw new Chapter_14_CustomRuntimeException("런타임 에러 발생!!!");
	}
}
