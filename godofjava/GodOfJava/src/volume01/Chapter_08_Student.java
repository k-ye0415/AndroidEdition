package volume01;
/*
 * Chapter08. 참조 자료형에 대해서 더 자세히 알아봅시다.
 * */
public class Chapter_08_Student {
	String name;
	String address;
	String phone;
	String email;
	
	public Chapter_08_Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Chapter_08_Student(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	
	public Chapter_08_Student(String name, String address, String phone, String email) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return name + " " + address + " " + phone + " " + email;
	}
	
	
}
