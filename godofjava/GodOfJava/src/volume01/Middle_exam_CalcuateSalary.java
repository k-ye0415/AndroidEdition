package volume01;

public class Middle_exam_CalcuateSalary {
	public static void main(String[] args) {
		Middle_exam_CalcuateSalary calcuateSalary = new Middle_exam_CalcuateSalary();
		calcuateSalary.calculateSalaries();
	}

	public long getSalaryIncrease(Middle_exam_Employee employee) {
		int type = employee.getType();
		long salary = employee.getSalay();
		long result = salary;
		switch (type) {
		case 1: {
			result += salary * -0.95;
			break;
		}

		case 2: {
			result += salary * 0.1;
			break;
		}
		case 3: {
			result += salary * 0.2;
			break;
		}
		case 4: {
			result += salary * 0.3;
			break;
		}
		case 5: {
			result += salary * 1;
			break;
		}
		}
		return result;
	}

	public void calculateSalaries() {

		Middle_exam_Employee[] employees = new Middle_exam_Employee[5];
		employees[0] = new Middle_exam_Employee("LeeDaeRi", 1, 1000000000);
		employees[1] = new Middle_exam_Employee("KimManager", 2, 100000000);
		employees[2] = new Middle_exam_Employee("WhangDesign", 3, 70000000);
		employees[3] = new Middle_exam_Employee("ParkArchi", 4, 80000000);
		employees[4] = new Middle_exam_Employee("LeeDevelop", 5, 60000000);

		for (Middle_exam_Employee item : employees) {
			long increase = getSalaryIncrease(item);
			item.setSalay(increase);
			System.out.println(item.getName() + "의 연봉은말야~ " + item.getSalay());
		}
	}
}
