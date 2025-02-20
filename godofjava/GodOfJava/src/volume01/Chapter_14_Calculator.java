package volume01;
/*
 * Chapter14. 다 배운 것 같지만, 예외라는 중요한 것이 있어요
 * */
public class Chapter_14_Calculator {
	public static void main(String[] args) {
		Chapter_14_Calculator calculator = new Chapter_14_Calculator();
		try {
			calculator.printDivide(1, 2);
			calculator.printDivide(1, 0);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void printDivide(double d1, double d2) throws Exception {
		if (d2 == 0) throw new Exception("Second value can't be Zero");
		
		double result = d1 / d2;
		System.out.println(result);	
	}
}
/*
 * Chapter 16 exception
1. 예외를 처리하기 위한 세가지 블록에는 어떤 것이 있나요?
	예외는 try - catch - finally 블록으로 처리한다.

2. 첫번째 문제의 답 중에서 "여기에서 예외가 발생할 것이니 조심하세요"라고 선언하는 블록은 어떤 블록인가요?
	예외가 발생 가능한 부분을 try 블록으로 묶어 준다.

3. 첫번째 문제의 답 중에서 "예외가 발생하던 안하던 얘는 반드시 실행되어야 됩니다."라는 블록은 어떤 블록인가요?
	finally 블록은 예외 발생 여부와 상관 없이 무조건 실행하도록 할 때 사용한다.

4. 예외의 종류 세가지는 각각 무엇인가요?
- checked exception
- error
- runtime exception 혹은 unchecked exception

5. 프로세스에 치명적인 영향을 주는 문제가 발생한 것을 무엇이라고 하나요?
	error는 치명적인 오류를 의미한다. 기본적으로는 프로그램 내에서 발생한다기 보다는 JVM 이나 시스템에서 문제가 발생했을 때 error가 발생한다.

6. try나 catch 블록 내에서 예외를 발생시키는 예약어는 무엇인가요?
	throw를 사용하여 새로운 예외를 발생시키면, 해당 예외를 호출한 메소드로 던진다.

7. 메소드 선언시 어떤 예외를 던질 수도 있다고 선언할 때 사용하는 키워드는 무엇인가요?
	throw가 메소드 내에 있다면 메소드 선언시 throws 를 사용하여 던질 예외의 종류를 명시하는 것이 좋다.

8. 직접 예외를 만들 때 어떤 클래스의 상속을 받아서 만들어야만 하나요?
	Exception클래스를 확장하여 예외 클래스를 만들 수 있다.
	하지만, 이렇게 되면 무조건 해당 예외를 던지는 메소드에서 try-catch로 묶어야 한다는 단점이 있다.
	따라서, RuntimeException 클래스를 확장하여 선언하는 것을 권장한다.
 * 
 * */