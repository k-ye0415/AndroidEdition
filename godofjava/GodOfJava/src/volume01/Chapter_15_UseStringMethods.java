package volume01;

public class Chapter_15_UseStringMethods {
	
	public static void main(String[] args) {
		Chapter_15_UseStringMethods useStringMethods = new Chapter_15_UseStringMethods();
		String apiString = "The String class reprersents character strings.";
		useStringMethods.printWords(apiString);
		useStringMethods.findString(apiString, "string");
		useStringMethods.findAnyCaseString(apiString, "string");
		useStringMethods.countChar(apiString, 's');
		useStringMethods.printContainWords(apiString, "ss");
	}
	
	public void printWords(String str) {
		String[] results = str.split(" ");
		for (String words : results) {
			System.out.println(words);
		}
	}
	
	public void findString(String str, String findStr) {
		int index = str.indexOf(findStr);
		if (index != -1) {
			System.out.println(findStr + " is appeared at " + index);
		} else {
			System.out.println("It does not include " + findStr);
		}
	}
	
	public void findAnyCaseString(String str, String findStr) {
		int index = str.toLowerCase().indexOf(findStr);
		if (index != -1) {
			System.out.println(findStr + " is appeared at " + index);
		} else {
			System.out.println("It does not include " + findStr);
		}
	}
	
	public void countChar(String str, char c) {
		char[] charArray = str.toCharArray();
		int count = 0;
		for (char temp : charArray) {
			if (temp == c) {
				count++;
			}
		}
		System.out.println("char '" + c + "' count is " + count);
	}
	
	public void printContainWords(String str, String findStr) {
		String[] strArray = str.split(" ");
		for (String word : strArray) {
			if (word.contains(findStr)) {
				System.out.println(word + " contains " + findStr);
			}
		}
	}
}

/*
 * Chapter 14 String
1. String 클래스는 final 클래스인가요? 만약 그렇다면, 그 이유는 무엇인가요?
	자바의 String클래스는 final로 선언되어 있으며, 더 이상 확장해서는 안된다. 

2. String 클래스가 구현한 인터페이스에는 어떤 것들이 있나요?
	String 클래스는 Serializable, Comparable, CharSequence 인터페이스를 구현(implements) 했다.

3. String 클래스의 생성자 중에서 가장 의미없는 (사용할 필요가 없는) 생성자는 무엇인가요?
	new String() 생성자는 가장 의미가 없는 String 클래스의 생성자이다. 왜냐하면, 생성된 객체는 해당 변수에 새로운 값이 할당되자마자 GC의 대상이 되어버리기 때문이다. 

4. String 문자열을 byte 배열로 만드는 메소드의 이름은 무엇인가요?
	String클래스의 getBytes() 메소드는 문자열을 바이트의 배열로 전환한다.

5. String 문자열의 메소드를 호출하기 전에 반드시 점검해야 하는 사항은 무엇인가요?
	String 객체의 메소드를 호출하기 전에 반드시 null인지 체크를 하는 습관을 가져야 한다. 그렇지 않으면, 실행시에 생각지도 못한 예외가 발생할 수 있다.

6. String 문자열의 길이를 알아내는 메소드는 무엇인가요?
	length() 메소드를 사용하면 문자열의 길이를 알아낼 수 있다.

7. String 클래스의 equals() 메소드와 compareTo() 메소드의 공통점과 차이점은 무엇인가요?
	equals()메소드와 compareTo()메소드의 공통점은 두 개의 문자열을 비교한다는 것이고, 다른 점은 리턴 타입이 다르다는 것이다.
	equals() 메소드는 boolean 타입의 리턴을, compareTo() 메소드는 int 타입의 리턴값을 제공한다.

8. 문자열이 "서울시"로 시작하는지를 확인하려면 String의 어떤 메소드를 사용해야 하나요?
	startsWith()메소드를 사용하면 해당 문자열이 원하는 문자열로 시작하는지를 확인할 수 있다. 

9. 문자열에 "한국"이라는 단어의 위치를 찾아내려고 할 때에는 String의 어떤 메소드를 사용해야 하나요?
	contains()나 matches()메소드를 사용하여 원하는 문자열이 존재하는지 확인할 수 있다. 고전적인 방법으로는 indexOf() 메소드를 사용할 수도 있다.

10. 위의 문제의 답에서 "한국"이 문자열에 없을 때 결과값은 무엇인가요?
	contains()나 matches() 메소드의 리턴타입은 boolean 이다.

11. 문자열의 1번째부터 10번째 위치까지의 내용을 String으로 추출하려고 합니다. 어떤 메소드를 사용해야 하나요?
	substring()이나 subSequence()메소드를 사용하면 원하는 위치에 있는 문자열을 자를 수 있다. 

12. 문자열의 모든 공백을 * 표시로 변환하려고 합니다. 어떤 메소드를 사용하는 것이 좋을까요?
	replace()나 replaceAll() 메소드를 사용하면 문자열의 특정 부분을 바꿀 수 있다. 
	여기서 중요한 것은 원본 문자열은 변경되지 않는다는 것이다. 변경된 값을 사용하려면 해당 메소드의 리턴값을 사용해야만 한다.

13. String의 단점을 보완하기 위한 두개의 클래스는 무엇인가요?
	String의 단점을 보완하기 위한 클래스로 StringBuilder와 StringBuffer가 있다. 

14. 문제의 답에서 문자열을 더하기 위한 메소드의 이름은 무엇인가요?
	StringBuilder와 StringBuffer클래스의 append() 메소드를 사용하여 문자열을 더할 수 있다. 
 * 
 * */
