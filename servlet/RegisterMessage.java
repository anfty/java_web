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
import tom.bean.Register;


public class RegisterMessage extends HttpServlet {
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
    paramHttpServletRequest.setCharacterEncoding("utf-8");
    String str1 = paramHttpServletRequest.getParameter("logname");
    String str2 = paramHttpServletRequest.getParameter("password");
    String str3 = paramHttpServletRequest.getParameter("password1");
    String str4 = paramHttpServletRequest.getParameter("sex");
    String str5 = paramHttpServletRequest.getParameter("age");
    String str6 = paramHttpServletRequest.getParameter("email");
    String str7 = paramHttpServletRequest.getParameter("phone");
    String str8 = paramHttpServletRequest.getParameter("message");
    if (str1 == null || str1.equals("")) {
      showMessage("会员名称不能为空", paramHttpServletResponse);
    } else if (str2 == null || str2.equals("")) {
      showMessage("设置密码不能为空", paramHttpServletResponse);
    } else if (!str2.equals(str3)) {
      showMessage("两次输入密码不一致", paramHttpServletResponse);
    } else {
      try {
        String str9 = "jdbc:mysql://127.0.0.1:3306/test";
        String str10 = "test";
        String str11 = "123456";
        Connection connection = DriverManager.getConnection(str9, str10, str11);
        Statement statement = connection.createStatement();
        int i = 0;
        if (str5 != null && !str5.equals(""))
          i = Integer.parseInt(str5); 
        String str12 = "insert into member(logname,password,sex,age,phone,email,message) values ('" + str1 + "','" + str2 + "','" + str4 + "','" + i + "','" + str7 + "','" + str6 + "','" + str8 + "')";
        ResultSet resultSet = statement.executeQuery("select * from member where logname='" + str1 + "'");
        if (resultSet.next()) {
          showMessage("重复注册", paramHttpServletResponse);
        } else {
          int j = statement.executeUpdate(str12);
          if (j != 0) {
            Register register = new Register();
            register.setLogname(str1);
            register.setPassword(str3);
            register.setSex(str4);
            register.setAge(i);
            register.setPhone(str7);
            register.setEmail(str6);
            register.setMessage(str8);
            paramHttpServletRequest.setAttribute("register", register);
            RequestDispatcher requestDispatcher = paramHttpServletRequest.getRequestDispatcher("registerMessage.jsp");
            requestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
          } else {
            showMessage("数据异常，请稍后重试", paramHttpServletResponse);
          } 
        } 
      } catch (Exception exception) {
        exception.printStackTrace();
      } 
    } 
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException { doGet(paramHttpServletRequest, paramHttpServletResponse); }
  
  private void showMessage(String paramString, HttpServletResponse paramHttpServletResponse) throws IOException {
    paramHttpServletResponse.setContentType("text/html;charSet=GB2312");
    PrintWriter printWriter = paramHttpServletResponse.getWriter();
    printWriter.println("<html><body>");
    printWriter.println(paramString + ",");
    printWriter.println("<a href='register.jsp'>继续注册</a>");
    printWriter.println("</body></html>");
  }
}
