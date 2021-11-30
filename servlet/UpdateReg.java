package tom.servlet;

import java.io.IOException;
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
import tom.bean.Register;

public class UpdateReg extends HttpServlet {

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
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");// 获取用户登录时的JavaBean
		boolean ok = true;
		if (login == null) {
			ok = false;
			response.sendRedirect("login.jsp");
		}
		if (ok == true) {
			continueWork(request, response);
		}
	}

	private void continueWork(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		String logname = login.getLogname();
		Register register = new Register();
		request.setAttribute("register", register);
		// 获取连接
		try {
			String uri = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ComeHere";
			String user = "xg";
			String password = "123456";
			Connection con = DriverManager.getConnection(uri, user, password);
			// 创建Statement对象
			Statement st = con.createStatement();
			//创建sql语句，查询以前注册的信息
			String sql="select * from member where logname='"+logname+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()){
				register.setLogname(rs.getString(1));
				register.setSex(rs.getString(3));
				register.setAge(rs.getInt(4));
				register.setPhone(rs.getString(5));
				register.setEmail(rs.getString(6));
				register.setMessage(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("updateReg.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
