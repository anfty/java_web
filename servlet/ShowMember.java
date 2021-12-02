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
import tom.bean.ShowResult;


public class ShowMember extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver")
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
		ShowResult showResult = new ShowResult();// 获取MemberInformation的JavaBean
		request.setAttribute("showResult", showResult);
		StringBuffer str=new StringBuffer();
		// 获取连接
		try {
			String uri = "jdbc:mysql://127.0.0.1:3306/test";
			String user = "test";
			String password = "123456";
			Connection con = DriverManager.getConnection(uri, user, password);
			// 创建Statement对象
			Statement st = con.createStatement();
			//写sql语句，查询member表中的所有数据
			String sql="select * from member";
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				str.append("<tr>");
				str.append("<td>"+rs.getString(1)+"</td>");
				str.append("<td>"+rs.getString(3)+"</td>");
				str.append("<td>"+rs.getInt(4)+"</td>");
				str.append("<td>"+rs.getString(5)+"</td>");
				str.append("<td>"+rs.getString(6)+"</td>");
				str.append("<td>"+rs.getString(7)+"</td>");
				String s="<img src=image1/"+rs.getString(8)+"/"+rs.getString(8)+" "+"width=100 height=100/>";
				str.append("<td>"+s+"</td>");
				str.append("</tr>");
			}
		} catch (Exception e) {
			response.setContentType("text/html;charset=GB2312");
			PrintWriter out=response.getWriter();
			out.println("<html><body>");
			out.println("查询不到结果");
			out.println("回到浏览会员页面:<a href='lookMember.jsp'>");
			out.println("</body></html>");
		}
		showResult.setResult(new String(str));
		System.out.println(showResult.getResult());
		RequestDispatcher dispatcher=request.getRequestDispatcher("showMember.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
