package volume01;


/*
 * Chapter05. 계산을 하고 싶어요
 * */
public class Chapter_05_SalaryManager {
	public static void main(String[] args) {
		Chapter_05_SalaryManager salaryManager = new Chapter_05_SalaryManager();
		double monthly = salaryManager.getMonthlySalary(2000);
		System.out.println(monthly);
		
	}
	public double getMonthlySalary(int yearlySalary) {
		double monthly = yearlySalary / 12.0;
		
		double taxValue = calculateTax(monthly);
		double nationalPensionValue = calculateNationalPension(monthly);
		double healthInsuranceValue = calculateHealthInsurance(monthly);
		
		double totalValue = taxValue + nationalPensionValue + healthInsuranceValue;
		
		double result = monthly - totalValue;
		return result;
	}

	public double calculateTax(double monthSalary) {
		return monthSalary * (12.5 / 100);
	}
	
	public double calculateNationalPension(double monthSalary) {
		return monthSalary * (8.1 / 100);
	}
	
	public double calculateHealthInsurance(double monthSalary) {
		return monthSalary * (13.5 / 100);
	}
}

/*
 * 1. 값을 할당할 때 사용하는 연산자의 기호는 무엇인가요?
 * 	=
 * 
 * 2. 기본적인 덧셈, 뺄셈, 곱셈, 나눗셈, 나머지를 계산할 때 사용하는 연산자의 기호는 순서대로 각각 무엇인가요?
 * 	+, -, *, /, %
 * 
 * 3. += 는 무엇을 할 때 사용하는 연산자 인가요?
 * 	덧셈
 * 
 * 4. 연산의 순서를 모르거나 확실히 하고 싶을 때에는 어떤 기호를 사용해야 하나요?
 * 	소괄호 ()
 * 
 * 5. ==와 !=의 차이는 무엇인가요?
 * 	같다와, 같지 않다.
 * 
 * 6. <와 <=의 차이는 무엇인가요?
 * 	크커나, 크거나 같거나
 * 
 * 7. ! 연산자는 어떤 타입에 사용 할 수 있나요?
 * 	boolean
 * 
 * 8. ? : 로 표시하는 삼항 연산자의 ?와 : 뒤에 명시해 주는 값은 무엇을 의미 하나요?
 * 	? 뒤에는 true, : 뒤에는 false
 * 
 * 9. 자바는 형변환을 한다고 했는데, short의 값을 long에 할당할 때에는 어떤 것을 해 주어야 하나요?
 * 	short 의 값을 long 으로 형변환 할 때에는 따로 casting 작업이 필요 없다.
 * 
 * 10. 반대로 long값을 short에 할당할 때에는 어떤 것을 해 주어야 하나요?
 * 	범위가 큰 long 에서 작은 short 로 형변한 할 때에는 casting 이 꼭 필요하다.
 * 
 * 11. 위의 두 문제에서 어떤 경우가 기존 값이 사라지고, 엉뚱한 값으로 바뀔 수 있나요?
 * 	범위가 큰 쪽에서 작은 쪽으로 변환 될 때 값을 손실될 확률이 크다.
 * 
 */