package volume01;
/*
 * Chapter10. 자바는 상속이라는 것이 있어요
 * */
public class Chapter_10_Animal {
	String name;
	String kind;
	int legCountg;
	boolean hasWing;
	
	public void move() {
		System.out.println("Move");
	}
	
	public void eatFood() {
		System.out.println("냠냠");
	}
}

/*
 * 1. 상속을 받는 클래스의 선언문에 사용하는 예약어는 무엇인가요?
 * 	extends
 * 
 * 2. 상속을 받은 클래스의 생성자를 수행하면 부모의 생성자도 자동으로 수행된다.
 * 	Y
 * 
 * 3. 부모 클래스의 생성자를 자식 클래스에서 직접 선택하려고 할 때 사용하는 예약어는 무엇인가요?
 * 	super
 * 
 * 4. 메소드 Overriding과 Overloading을 정확하게 설명해 보세요.
 * 	Overriding 은 상속받은 부모 클래스의 변수, 메소드를 동일한 형태로 재정의할 때 사용.
 * 	Overloading 은 같은 이름의 메소드이지만 매개변수 타입이 다르거나 매개변수 갯수가 다르게 사용하는 것.
 * 
 * 5. A가 부모, B가 자식 클래스라면 A a=new B(); 의 형태로 객체 생성이 가능한가요?
 * 	Y
 * 
 * 6. 명시적으로 형변환을 하기 전에 타입을 확인하려면 어떤 예약어를 사용해야 하나요?
 * 	instanceOf
 * 
 * 7. 위의 문제에서 사용한 예약어의 좌측에는 어떤 값이, 우측에는 어떤 값이 들어가나요?
 * 	좌측에는 변수 또는 객체, 우측에는 클래스(타입)
 * 
 * 8. instanceof 예약어의 수행 결과는 어떤 타입으로 제공되나요?
 * 	boolean
 * 
 * 9. Polymorphism이라는 것은 뭔가요?
 * 	하나의 부모 클래스가 여러 자식 클래스를 가질 수 있는 것.
 * 
 * */