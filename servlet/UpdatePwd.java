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
  
  public void continueWork(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    HttpSession httpSession = paramHttpServletRequest.getSession(true);
    Login login = (Login)httpSession.getAttribute("login");
    String str1 = login.getLogname();
    UpdatePassword updatePassword = new UpdatePassword();
    paramHttpServletRequest.setAttribute("password", updatePassword);
    String str2 = paramHttpServletRequest.getParameter("password");
    String str3 = paramHttpServletRequest.getParameter("newpassword1");
    String str4 = paramHttpServletRequest.getParameter("newpassword2");
    try {
      String str5 = "jdbc:mysql://127.0.0.1:3306/test";
      String str6 = "test";
      String str7 = "123456";
      Connection connection = DriverManager.getConnection(str5, str6, str7);
      Statement statement = connection.createStatement();
      String str8 = "select * from member where logname='" + str1 + "'and password='" + str2 + "'";
      ResultSet resultSet = statement.executeQuery(str8);
      if (resultSet.next())
        if (str3 == str4 || str3.equals(str3)) {
          String str = "update member set password='" + str3 + "'where logname='" + str1 + "'";
          int i = statement.executeUpdate(str);
          if (i != 0) {
            updatePassword.setBackNews("密码更新成功");
            updatePassword.setPassword(str2);
            updatePassword.setNewPassword1(str3);
          } else {
            updatePassword.setBackNews("密码更新失败");
          } 
        } else {
          paramHttpServletResponse.setContentType("text/html;charset=GB2312");
          PrintWriter printWriter = paramHttpServletResponse.getWriter();
          printWriter.println("<html><body>");
          printWriter.print("密码不一致");
          printWriter.println("<a href='updatePwd.jsp'>重新输入信息,修改密码</a>");
          printWriter.println("</body></html>");
        }  
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    RequestDispatcher requestDispatcher = paramHttpServletRequest.getRequestDispatcher("updatePwdMessage.jsp");
    requestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException { doGet(paramHttpServletRequest, paramHttpServletResponse); }
}
