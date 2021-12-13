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
  public void init(ServletConfig paramServletConfig) throws ServletException {
    try {
      Class.forName("com.mysql.jdbc.Driver")
        .newInstance();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    super.init(paramServletConfig);
  }
  
  protected void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    String str1 = paramHttpServletRequest.getParameter("logname");
    String str2 = paramHttpServletRequest.getParameter("password");
    try {
      String str3 = "jdbc:mysql://127.0.0.1:3306/test";
      String str4 = "test";
      String str5 = "123456";
      Connection connection = DriverManager.getConnection(str3, str4, str5);
      Statement statement = connection.createStatement();
      String str6 = "select * from member where logname='" + str1 + "'and password='" + str2 + "'";
      ResultSet resultSet = statement.executeQuery(str6);
      if (resultSet.next()) {
        Login login = new Login();
        login.setLogname(str1);
        login.setPassword(str2);
        HttpSession httpSession = paramHttpServletRequest.getSession(true);
        httpSession.setAttribute("login", login);
        RequestDispatcher requestDispatcher = paramHttpServletRequest.getRequestDispatcher("loginMessage.jsp");
        requestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
      } else {
        paramHttpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = paramHttpServletResponse.getWriter();
        printWriter.println("<html><body>");
        printWriter.println("会员登录失败");
        printWriter.println("<a href='login.jsp'>重新登录</a>");
        printWriter.println("</body></html>");
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException { doGet(paramHttpServletRequest, paramHttpServletResponse); }
}
