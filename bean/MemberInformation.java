package tom.bean;

public class MemberInformation {
	private String logname;// 声明登录名变量
	private String sex;// 声明性别变量
	private int age;// 声明年龄变量
	private String phone;// 声明电话变量
	private String email;// 声明电子邮件变量
	private String message;// 声明简历变量
	private String pic;// 声明图片名变量

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
