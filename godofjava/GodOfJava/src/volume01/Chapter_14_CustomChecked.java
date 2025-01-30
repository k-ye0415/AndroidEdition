package volume01;

public class Chapter_14_CustomChecked {
	public static void main(String[] args) {
		Chapter_14_CustomChecked customChecked = new Chapter_14_CustomChecked();
		
		try {
			customChecked.doSomething();
		} catch (Chapter_14_CustomCheckedException e) {
			System.out.println("Checked Exception! " + e.getMessage());
		}
	}
	
	public void doSomething() throws Chapter_14_CustomCheckedException {
		throw new Chapter_14_CustomCheckedException("예외 발생!!!");
		// 여기서 throws 를 사용해서 예외를 던졌기 때문에 호출하는 곳에 반드시 try/catch 문이 실행되어야함.
		// 그렇지 않으면 컴파일 에러가 발
	}
}
