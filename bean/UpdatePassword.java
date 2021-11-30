package tom.bean;

public class UpdatePassword {
	private String password;// 声明当前密码变量
	private String newPassword1;// 声明新密码变量
	private String backNews;// 声明返回信息变量

	public String getNewPassword1() {
		return newPassword1;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getBackNews() {
		return backNews;
	}

	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}

}
