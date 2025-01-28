package volume01;
/*
 * Chapter07. 여러 데이터를 하나에 넣을 수 없을까요?
 * */
public class Chapter_07_ManageHeight {
	
	int gradeHeights[][];
	
	public static void main(String[] args) {
		Chapter_07_ManageHeight manageHeight = new Chapter_07_ManageHeight();
		manageHeight.setData();
		for (int i = 1; i <= 5; i++) {
			manageHeight.printAverage(i);
		}
	}
	
	public void setData() {
		gradeHeights = new int[5][];
		gradeHeights[0] = new int[] {170, 180, 173, 175, 177};
		gradeHeights[1] = new int[] {160, 165, 167, 186};
		gradeHeights[2] = new int[] {148, 177, 187, 176};
		gradeHeights[3] = new int[] {173, 182, 181};
		gradeHeights[4] = new int[] {170, 180, 165, 177, 172};
	}
	
	public void printHeight(int classNo) {
		System.out.println("Class Number : " + classNo);
		for (int data : gradeHeights[classNo - 1]) {
			System.out.println(data);
		}
	}
	
	public void printAverage(int classNo) {
		System.out.println("Class Number : " + classNo);
		double result = 0;
		int count = gradeHeights[classNo - 1].length;
		for (int data : gradeHeights[classNo - 1]) {
			result+=data;
		}
		System.out.println(result/count);
	}
}

/*
 * 1. 배열을 선언할 때 어떤 기호를 변수명의 앞이나 뒤에 사용해야 하나요?
 * 	[]
 * 
 * 2. 배열의 첫번째 위치는 0인가요? 1인가요?
 * 	0
 * 
 * 3. 배열을 선언할 때 boolean 배열의 크기만 지정했다면 boolean 배열의 [0] 위치에 있는 값은 무엇인가요?
 * 	false
 * 
 * 4. ArrayIndexOutOfBoundsException 이라는 것은 언제 발생하나요?
 * 	배열의 범위를 벗어났을 때
 * 
 * 5. 중괄호를 이용하여 배열을 초기화 할 때 중괄호 끝에 반드시 어떤 것을 입력해 주어야 하나요?
 * 	;
 * 
 * 6. 2차원 배열을 정의할 때에는 대괄호를 몇 개 지정해야 하나요?
 * 	2개
 * 
 * 7. 배열을 쉽게 처리해주는 for 문의 문법은 어떻게 되나요?
 * 	for (타입 변수명 : 반복 대상 객체)
 * 
 * 8. 자바 프로그램에 데이터를 전달해 주려면 클래스 이름 뒤에 어떻게 구분하여 나열하면 되나요?
 * 	공백
 * 
 * 9. 자바 프로그램이 시작할 때 전달 받는 내용은 어떤 타입의 배열인가요?
 * 	String
 * */
