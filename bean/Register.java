package tom.bean;

public class Register {
	private String logname;// 声明会员名称变量
	private String password;// 声明会员密码变量
	private String sex;// 声明会员性别变量
	private int age; // 声明会员年龄变量
	private String email;// 声明电子邮件变量
	private String phone;// 声明联系电话变量
	private String message;// 声明个人简历变量

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
