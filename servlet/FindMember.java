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
      continueDoGet(paramHttpServletRequest, paramHttpServletResponse, login.getLogname()); 
  }
  
  private void continueDoGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString) throws ServletException, IOException {
    MemberInformation memberInformation = new MemberInformation();
    paramHttpServletRequest.setAttribute("memberInfo", memberInformation);
    String str = paramHttpServletRequest.getParameter("logname");
    if (str.equals("")) {
      HttpSession httpSession = paramHttpServletRequest.getSession(true);
      Login login = (Login)httpSession.getAttribute("login");
      str = login.getLogname();
    } 
    try {
      String str1 = "jdbc:mysql://127.0.0.1:3306/test";
      String str2 = "test";
      String str3 = "123456";
      Connection connection = DriverManager.getConnection(str1, str2, str3);
      Statement statement = connection.createStatement();
      String str4 = "select * from member where logname='" + str + "'";
      ResultSet resultSet = statement.executeQuery(str4);
      if (resultSet.next()) {
        String str5 = resultSet.getString("sex");
        int i = resultSet.getInt("age");
        String str6 = resultSet.getString("phone");
        String str7 = resultSet.getString("email");
        String str8 = resultSet.getString("message");
        String str9 = resultSet.getString("pic");
        memberInformation.setLogname(str);
        if (paramString.equals("admin") || paramString.equals(str)) {
          memberInformation.setSex(str5);
          memberInformation.setAge(i);
          memberInformation.setPhone(str6);
          memberInformation.setMessage(str8);
        } else {
          memberInformation.setSex("保密");
          memberInformation.setAge(0);
          memberInformation.setPhone("保密");
          memberInformation.setMessage("保密");
        } 
        memberInformation.setEmail(str7);
        memberInformation.setPic(str9);
        RequestDispatcher requestDispatcher = paramHttpServletRequest.getRequestDispatcher("findMember.jsp");
        requestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
      } else {
        paramHttpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = paramHttpServletResponse.getWriter();
        printWriter.println("<html><body>");
        printWriter.println("查询不到结果");
        printWriter.println("<a href='lookMember.jsp'>回到浏览会员页面</a>");
        printWriter.println("</body></html>");
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException { doGet(paramHttpServletRequest, paramHttpServletResponse); }
}
