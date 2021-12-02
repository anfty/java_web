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
import tom.bean.UpdatePassword;

public class UpdatePwd extends HttpServlet {

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

	public void continueWork(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");// 获取用户登录时的JavaBean
		String logname = login.getLogname();// 获得用户登录时的登录名
		UpdatePassword passwordBean = new UpdatePassword();// 获取修改密码的JavaBean
		request.setAttribute("password", passwordBean);
		// 获取表单内容
		String oldpassword = request.getParameter("password");
		String newPassword1 = request.getParameter("newpassword1");
		String newPassword2 = request.getParameter("newpassword2");
		// 获取连接
		try {
			String uri = "jdbc:mysql://127.0.0.1:3306/test";
			String user = "test";
			String password = "123456";
			Connection con = DriverManager.getConnection(uri, user, password);
			// 创建Statement对象，用于发送和执行SQL语句
			Statement st = con.createStatement();
			String sql1="select * from member where logname='"+logname+"'and password='"+oldpassword+"'";
			ResultSet rs=st.executeQuery(sql1);
			if(rs.next()){
				if(newPassword1==newPassword2 || newPassword1.equals(newPassword1)){
					String sql2="update member set password='"+newPassword1+"'where logname='"+logname+"'";
					int m=st.executeUpdate(sql2);
					if(m!=0){
						passwordBean.setBackNews("密码更新成功");
						passwordBean.setPassword(oldpassword);
						passwordBean.setNewPassword1(newPassword1);
					}else{
						passwordBean.setBackNews("密码更新失败");
					}
				}else{
					response.setContentType("text/html;charset=GB2312");
					PrintWriter out=response.getWriter();
					out.println("<html><body>");
					out.print("密码不一致");
					out.println("<a href='updatePwd.jsp'>重新输入信息,修改密码</a>");
					out.println("</body></html>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("updatePwdMessage.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
