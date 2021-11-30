package tom.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tom.bean.Login;

public class Exit extends HttpServlet {

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
		HttpSession session=request.getSession(true);
		Login login=(Login)session.getAttribute("login");//获得用户登录时的JavaBean
		boolean ok=true;
		if(login==null){
			ok=false;
			response.sendRedirect("login.jsp");//重定向到登录页面
		}
		if(ok==true){
			continueDoGet(request,response);
		}
	}
	
	

	public void continueDoGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession(true);
		session.invalidate();//销毁用户的session对象
		response.sendRedirect("index.jsp");//返回主页
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
