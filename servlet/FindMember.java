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
import tom.bean.MemberInformation;

public class FindMember extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 加载驱动
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();
		} catch (Exception e) {
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
		MemberInformation memberInfo = new MemberInformation();// 获取MemberInformation的JavaBean
		request.setAttribute("memberInfo", memberInfo);
		String logname=request.getParameter("logname");//获取表单内容
		// 获取连接
		try {
			String uri = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=ComeHere";
			String user = "xg";
			String password = "123456";
			Connection con = DriverManager.getConnection(uri, user, password);
			// 创建Statement对象
			Statement st = con.createStatement();
			//创建sql语句，根据会员名查询会员信息
			String sql="select * from member where logname='"+logname+"'";
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()){
				String sex=rs.getString("sex");
				int age=rs.getInt("age");
				String phone=rs.getString("phone");
				String email=rs.getString("email");
				String message=rs.getString("message");
				String pic=rs.getString("pic");
				
				memberInfo.setLogname(logname);
				memberInfo.setSex(sex);
				memberInfo.setAge(age);
				memberInfo.setPhone(phone);
				memberInfo.setEmail(email);
				memberInfo.setMessage(message);
				memberInfo.setPic(pic);
			}else{
				response.setContentType("text/html;charset=GB2312");
				PrintWriter out=response.getWriter();
				out.println("<html><body>");
				out.println("查询不到结果");
				out.println("回到浏览会员页面:<a href='lookMember.jsp'>");
				out.println("</body></html>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("findMember.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
