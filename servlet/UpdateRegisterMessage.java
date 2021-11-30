package tom.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tom.bean.Login;
import tom.bean.UpdateRegister;

public class UpdateRegisterMessage extends HttpServlet {
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
			continueDoGet(request, response);
		}
	}

	private void continueDoGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		String logname = login.getLogname();// 获得登录名称
		UpdateRegister updateRegister = new UpdateRegister();// 获取UpdateRegister的JavaBean
		request.setAttribute("updateRegister", updateRegister);
		// 获取UpdateReg.jsp页面中表单的内容
		request.setCharacterEncoding("utf-8");
		String sex = request.getParameter("newSex");
		int age = Integer.parseInt(request.getParameter("newAge"));
		String email = request.getParameter("newEmail");
		String phone = request.getParameter("newPhone");
		String message = request.getParameter("newMessage");

		// 获取连接
		try {
			String uri = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ComeHere";
			String user = "xg";
			String password = "123456";
			Connection con = DriverManager.getConnection(uri, user, password);
			// 创建Statement对象
			Statement st = con.createStatement();
			//创建sql语句，修改注册信息,更新数据表的数据
			String sql="update member set sex='"+sex+"',age='"+age+"',email='"+email+"',phone='"+phone+"',message='"+message+"'";
			int m=st.executeUpdate(sql);
			if(m!=0){
				updateRegister.setLogname(logname);
				updateRegister.setNewAge(age);
				updateRegister.setNewSex(sex);
				updateRegister.setNewEmail(email);
				updateRegister.setNewPhone(phone);
				updateRegister.setNewMessage(message);
			}else{
				response.setContentType("text/html;charset=GB2312");
				PrintWriter out=response.getWriter();
				out.println("<html><body>");
				out.println("信息填写不完整或信息中有非法字符");
				out.println("重新修改注册信息:<a href='updateReg.jsp'>");
				out.println("</body></html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("updateRegisterMessage.jsp");
		dispatcher.forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
