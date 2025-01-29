package volume01;

public class Chapter_12_ManageStudent {
	public static void main(String[] args) {
		Chapter_12_ManageStudent manageStudent = new Chapter_12_ManageStudent();
		manageStudent.checkEquals();
	}
	
	public void checkEquals() {
		Chapter_12_Student student1 = new Chapter_12_Student("Min", "Seoul", "123213", "123@123.com");
		Chapter_12_Student student2 = new Chapter_12_Student("Min", "Seoul", "123213", "123@123.com");
		
		if (student1.equals(student2)) {
			System.out.println("Equal");
		} else {
			System.out.println("Not Equal");
		}
	}
}

/*
 * 1. 모든 클래스의 최상위 부모 클래스인 Object 클래스는 어떤 패키지에 선언되어 있나요 ?
 * 	java.lang
 * 
 * 2. 클래스가 어떻게 선언되어 있는지 확인할 수 있는 명령어(실행파일)의 이름은 무엇인가요?
 * 	javap
 * 
 * 3. Object 클래스에 선언되어 있는 모든 메소드를 Overriding해야 하나요?
 * 	No
 * 
 * 4. Object 클래스의 clone() 메소드의 용도는 무엇인가요?
 * 	객체를 복사하는 용도
 * 
 * 5. System.out.println() 메소드를 사용하여 클래스를 출력했을 때 "최종적으로" 호출되는 Object 클래스에 있는 메소드는 무엇인가요?
 * 	toString()
 * 
 * 6. 객체의 주소를 비교하는 것이 아닌, 값을 비교하려면 Object 클래스에 선언되어 있는 어떤 메소드를 overrding해야 하나요?
 * 	equals()
 * 
 * 7. Object 클래스에 선언되어 있는 hashCode()라는 메소드는 어떤 타입의 값을 리턴 하나요?
 * 	int, 16진
 * 
 * */
 