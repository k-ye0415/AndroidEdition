package volume01;

/*
 * Chapter04. 정보를 어디에 넣고 싶은데
 * */
public class Chapter_04_ProfilePrint {
	byte age;
	String name;
	boolean isMarried;
	
	public static void main(String[] args) {
		Chapter_04_ProfilePrint profile = new Chapter_04_ProfilePrint();
	}
	
	public void setAge(byte age) {
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMarried(boolean flag) {
		this.isMarried = flag;
	}
	
	public byte getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isMarried() {
		return isMarried;
	}

}

/*
 * 1. 네가지 종류 변수는 어떻게 구분할 수 있나요?
 * 	지역, 매개, 인스턴스, 클래스
 * 
 * 2. 일반 변수의 이름을 지을 때 대문자로 시작하는 것은 일반적인 명명규칙이다.
 * 	No.
 * 		-> 일반적인 변수는 소문자로 시작하지만, 상수같은 경우 대문자로 선언
 * 
 * 3. 자료형에는 기본 자료형과 어떤 자료형이 있나요?
 * 	참조 자료형
 * 
 * 4. 기본 자료형에는 몇가지가 있나요?
 * 	byte, int, long, short, char, double, float, boolean 총 8개
 * 
 * 5. 기본 자료형 중 정수형에는 어떤 것들이 있나요?
 * 	byte, int, long, char, short
 * 
 * 6. byte는 몇 비트(bit)로 되어 있나요?
 * 	8bit
 * 
 * 7. byte 타입은 왜 만들었을까요?
 * 	적은 공간에 더 많은 내용을 담기 위해서
 * 
 * 8. int와 long중 어떤 타입이 더 큰 숫자를 처리할 수 있나요?
 * 	long
 * 
 * 9. 소수점을 처리하는 타입은 어떤 것이 있나요?
 * 	double, float
 * 
 * 10. char는 정수형인가요?=
 * 	Yes
 * 
 * 11. a라는 값을 char로 정의할 때 어떤 기호로 감싸주어야 하나요? (기호를 입력하세요)
 * 	''
 * 
 * 12. true와 false 두개의 값만을 가지는 타입은 어떤 것인가요?
 * 	boolean
 * */
 