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
  public void init(ServletConfig paramServletConfig) throws ServletException {
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
        .newInstance();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    super.init(paramServletConfig);
  }
  
  protected void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    HttpSession httpSession = paramHttpServletRequest.getSession(true);
    Login login = (Login)httpSession.getAttribute("login");
    boolean bool = true;
    if (login == null) {
      bool = false;
      paramHttpServletResponse.sendRedirect("login.jsp");
    } 
    if (bool == true)
      continueWork(paramHttpServletRequest, paramHttpServletResponse); 
  }
  
  private void continueWork(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    HttpSession httpSession = paramHttpServletRequest.getSession(true);
    Login login = (Login)httpSession.getAttribute("login");
    String str = login.getLogname();
    Register register = new Register();
    paramHttpServletRequest.setAttribute("register", register);
    try {
      String str1 = "jdbc:mysql://127.0.0.1:3306/test";
      String str2 = "test";
      String str3 = "123456";
      Connection connection = DriverManager.getConnection(str1, str2, str3);
      Statement statement = connection.createStatement();
      String str4 = "select * from member where logname='" + str + "'";
      ResultSet resultSet = statement.executeQuery(str4);
      if (resultSet.next()) {
        register.setLogname(resultSet.getString(2));
        register.setSex(resultSet.getString(4));
        register.setAge(resultSet.getInt(5));
        register.setPhone(resultSet.getString(6));
        register.setEmail(resultSet.getString(7));
        register.setMessage(resultSet.getString(8));
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    RequestDispatcher requestDispatcher = paramHttpServletRequest.getRequestDispatcher("updateReg.jsp");
    requestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException { doGet(paramHttpServletRequest, paramHttpServletResponse); }
}
