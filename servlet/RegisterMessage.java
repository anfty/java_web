package tom.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tom.bean.Register;

public class RegisterMessage extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 加载驱动
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获取表单传递过来的参数
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("logname");
		String pwd = request.getParameter("password");
		String pwd1 = request.getParameter("password1");
		String s = request.getParameter("sex");
		String a = request.getParameter("age");
		String e = request.getParameter("email");
		String p = request.getParameter("phone");
		String m = request.getParameter("message");

		if (name == null || name.equals("")) {
			showMessage("会员名称不能为空", response);
		} else if (pwd == null || pwd.equals("")) {
			showMessage("设置密码不能为空", response);
		} else if (!pwd.equals(pwd1)) {
			showMessage("两次输入密码不一致", response);
		} else {
			// 获取连接
			try {
				String uri = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ComeHere";
				String user = "xg";
				String password = "123456";
				Connection con = DriverManager.getConnection(uri, user,
						password);
				// 创建Statement对象，用于发送和执行SQL语句
				Statement st = con.createStatement();
				int age=0;
				if(!(a==null || a.equals(""))){
					age=Integer.parseInt(a);
				}
				
				// 创建sql语句
				String sql = "insert into member(logname,password,sex,age,phone,email,message) values ('"+name+"','"+pwd+"','"+s+"','"+age+"','"+p+"','"+e+"','"+m+"')";
				// 执行sql语句，插入数据
				int i = st.executeUpdate(sql);
				if(i!=0){
					Register register=new Register();//获取Register对象
					//设置相应的属性
					register.setLogname(name);
					register.setPassword(pwd1);
					register.setSex(s);
					register.setAge(age);
					register.setPhone(p);
					register.setEmail(e);
					register.setMessage(m);
					/*将JavaBean写入request对象中*/
					request.setAttribute("register", register);
					//注册成功，跳转重定向
					RequestDispatcher dispatcher=request.getRequestDispatcher("registerMessage.jsp");
					dispatcher.forward(request, response);
				}else{
					showMessage("数据异常，请稍后重试",response);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void showMessage(String message, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charSet=GB2312");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println(message + ",");
		out.println("<a href='register.jsp'>继续注册</a>");
		out.println("</body></html>");
	}
}
