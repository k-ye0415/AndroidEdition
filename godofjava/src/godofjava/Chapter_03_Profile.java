package godofjava;
/*
 * Chapter03. 자바를 제대로 알려면 객체가 무엇인지를 알아야 해
 * */
public class Chapter_03_Profile {
	String name;
	int age;
	
	public static void main(String[] args) {
		Chapter_03_Profile profile = new Chapter_03_Profile();
		profile.setName("hello");
		profile.setAge(123);
	
		profile.printName();
		profile.printAge();
	}
	
	public void setName(String str) {
		this.name = str;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void printName() {
		System.out.println(this.name);
	}
	
	public void printAge() {
		System.out.println(this.age);
	}
}


/*
 * 1. 클래스와 객체의 차이점
 * 	클래스는 껍데기, 객체는 일을 하는 것
 * 		-> 클래스를 통해서 객체를 생성할 수 있다. 즉, 하나의클래스를 만들면 그 클래스의 모습을 갖는 여러 객체들을 생성할 수 있다.
 * 			그러므로 일반적인 경우 클래스의 메소드나 변수들을 사용하려면 객체를 생성하여 사용해야 한다.
 * 
 * 2. 객체를 생성하기 위해 꼭 사용해야 하는 예약어
 * 	new
 * 
 * 3. 객체를 생성하기 위해 사용하는 메소드 같이 생긴 클래스 이름에 소괄호가 있는 것이 무엇인가
 * 	생성자
 * 
 * 4. 객체의 메소드를 사용하려면 어떤 기호를 객체 이름과 메소드 이름 사이 너어야하는가
 * 	.
 * 
 * 5. 
 * */
 