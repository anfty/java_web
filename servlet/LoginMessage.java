package tom.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tom.bean.Login;

public class LoginMessage extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver")
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
		// 获取表单的内容
		String name = request.getParameter("logname");
		String pwd = request.getParameter("password");

		// 获取连接
		try {
			String uri = "jdbc:mysql://127.0.0.1:3306/test";
			String user = "test";
			String password = "123456";
			Connection con = DriverManager.getConnection(uri, user, password);
			// 创建Statement对象
			Statement st = con.createStatement();
			//创建sql语句，查询会员名称和密码
			String sql="select * from member where logname='"+name+"'and password='"+pwd+"'";
			//执行sql语句
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()){
				//获取JavaBean对象，封装数据
				Login login=new Login();
				login.setLogname(name);
				login.setPassword(pwd);
				HttpSession session=request.getSession(true);
				session.setAttribute("login", login);
				RequestDispatcher dispatcher=request.getRequestDispatcher("loginMessage.jsp");
				dispatcher.forward(request, response);
			}else{
				response.setContentType("text/html;charset=GB2312");
				PrintWriter out=response.getWriter();
				out.println("<html><body>");
				out.println("会员登录失败");
				out.println("<a href='login.jsp'>重新登录</a>");
				out.println("</body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
