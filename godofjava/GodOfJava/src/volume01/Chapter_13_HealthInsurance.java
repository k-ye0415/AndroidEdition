package volume01;

public enum Chapter_13_HealthInsurance {
	LEVLE_ONE(1000, 1.0),
	LEVLE_TOW(2000, 2.0),
	LEVEL_THREE(3000, 3.2),
	LEVEL_FOUR(4000, 4.5),
	LEVEL_FIVE(5000, 5.6),
	LEVEL_SIX(6000, 7.1);
	
	private final int salary;
	private final double ratio;
	
	Chapter_13_HealthInsurance(int maxSalary, double ratio) {
		this.salary = maxSalary;
		this.ratio = ratio;
	}
	
	public double getRatio() {
		return ratio;
	}
	
	public static Chapter_13_HealthInsurance getHealthInsujrance(int salary) {
		if (salary < 1000) {
			return LEVLE_ONE;
		} else if (salary < 2000) {
			return LEVLE_TOW;
		} else if (salary < 3000) {
			return LEVEL_THREE;
		} else if (salary < 4000) {
			return LEVEL_FOUR;
		} else if (salary < 5000) {
			return LEVEL_FIVE;
		} else {
			return LEVEL_SIX;
		}
	}
	
	public static void main(String[] args) {
		int salay = 4522;
		Chapter_13_HealthInsurance level = Chapter_13_HealthInsurance.getHealthInsujrance(salay);
		System.out.println(level + ", " + level.getRatio());
	}
}
