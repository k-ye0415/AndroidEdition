package volume01;
/*
 * Chapter12.	모든 클래스의 부모 클래스는 Object 에요
 * */
public class Chapter_12_Student {
	String name;
	String address;
	String phone;
	String email;
	
	public Chapter_12_Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Chapter_12_Student(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public Chapter_12_Student(String name, String address, String phone, String email) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return name + " " + address + " " + phone + " " + email;
	}
	
	@Override
	public boolean equals(Object obj) {
		Chapter_12_Student student = (Chapter_12_Student) obj;
		if (student.name == this.name && student.address == this.address && student.phone == this.phone && student.email == this.email) {
			return true;
		} else {
			return false;
		}
	}
	
}
