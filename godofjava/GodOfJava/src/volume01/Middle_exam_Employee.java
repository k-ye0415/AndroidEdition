package volume01;

public class Middle_exam_Employee {
	private String name;
	private int type;
	private long salay;

	public Middle_exam_Employee() {
	}

	public Middle_exam_Employee(String name, int type, long salay) {
		this.name = name;
		this.type = type;
		this.salay = salay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getSalay() {
		return salay;
	}

	public void setSalay(long salay) {
		this.salay = salay;
	}
	
	@Override
	public String toString() {
		return "Name : " + this.name + ", type : " + this.type + ", salay : " + this.salay;
	}

}
