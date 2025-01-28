package volume01;
/*
 * Chapter08. 참조 자료형에 대해서 더 자세히 알아봅시다.
 * */
class Chapter_08_MangeStudent {
	public static void main(String[] args) {
		Chapter_08_Student[] students = null;
		Chapter_08_MangeStudent mangeStudent = new Chapter_08_MangeStudent();
		students = mangeStudent.addStudent();
		mangeStudent.printStudents(students);
	}
	
	public Chapter_08_Student[] addStudent() {
		Chapter_08_Student[] students = new Chapter_08_Student[3];
		students[0] = new Chapter_08_Student("Lim");
		students[1] = new Chapter_08_Student("Min");
		students[2] = new Chapter_08_Student("Sook", "Seoul", "12321321", "111@2222.com");
		return students;
	}
	
	public void printStudents(Chapter_08_Student[] students) {
		for (Chapter_08_Student student : students) {
			System.out.println(student);
		}
	}
}


/*
 *
 * 1. 생성자는 반드시 만들어야 하나요?
 * 	No, 하지만 매개변수가 있는 생성자를 만들 경우 기본 생성자는 반드시 필요하다.
 * 
 * 2. 만약 매개변수가 있는 생성자를 만들고, 매개변수가 없는 기본 생성자를 호출하면 어떻게 될까요?
 * 	기본 생성자를 생성하지 않았음으로 에러 발생.
 * 
 * 3. 생성자의 개수는 제한이 있나요?
 * 	없음
 * 
 * 4. 인스턴스의 변수와 매개변수나 메소드 내에서 생성한 변수와 구분하기 위해서 사용하는 키워드는 무엇인가요?
 * 	this
 * 
 * 5. 메소드 선언시 리턴 타입으로 지정한 데이터를 넘겨줄 때 사용하는 키워드는 무엇인가요?
 * 	return
 * 
 * 6. 메소드 선언시 아무 데이터도 리턴 타입으로 넘겨주지 않겠다는 것을 지정하는 키워드는 무엇인가요?
 * 	void
 * 
 * 7. 메소드 선언에 static 이 있는 것과 없는 것의 차이는 무엇인가요?
 * 	객체를 생성하지 않고 사용할 수 있음.
 * 
 * 8. 필자가 엄청나게 중요하다고 한 것 중 메소드의 이름은 같으나 매개변수를 다르게 하는 것의 명칭은 무엇인가요?
 * 	overload
 * 
 * 9. 기본 자료형을 매개변수로 넘겨 줄 때 Pass by value인가요? 아니면 Pass by reference인가요?
 * 	pass by value
 * 
 * 10. 참조 자료형을 매개변수로 넘겨 줄 때 Pass by value인가요? 아니면 Pass by reference인가요?
 * 	pass by reference
 * 
 * 11. 매개변수의 수가 가변적일 때 메소드 선언시 타입과 변수 이름 사이에 어떤 것을 적어줘야 하나요?
 * 	...
 * */
 