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
    String str1 = "", str2 = "admin";
    if (login == null) {
      paramHttpServletResponse.sendRedirect("login.jsp");
    } else {
      str1 = login.getLogname();
      if (str2.equals(str1)) {
        continueDoGet(paramHttpServletRequest, paramHttpServletResponse);
      } else {
        showMessage("仅限管理员使用！", paramHttpServletResponse);
      } 
    } 
  }
  
  private void continueDoGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    ShowResult showResult = new ShowResult();
    paramHttpServletRequest.setAttribute("showResult", showResult);
    StringBuffer stringBuffer = new StringBuffer();
    try {
      String str1 = "jdbc:mysql://127.0.0.1:3306/test";
      String str2 = "test";
      String str3 = "123456";
      Connection connection = DriverManager.getConnection(str1, str2, str3);
      Statement statement = connection.createStatement();
      String str4 = "select * from member";
      ResultSet resultSet = statement.executeQuery(str4);
      while (resultSet.next()) {
        stringBuffer.append("<tr>");
        stringBuffer.append("<td>" + resultSet.getString(2) + "</td>");
        stringBuffer.append("<td>" + resultSet.getString(4) + "</td>");
        stringBuffer.append("<td>" + resultSet.getInt(5) + "</td>");
        stringBuffer.append("<td>" + resultSet.getString(6) + "</td>");
        stringBuffer.append("<td>" + resultSet.getString(7) + "</td>");
        stringBuffer.append("<td>" + resultSet.getString(8) + "</td>");
        String str = "<img src=/upload/" + resultSet.getString(9) + "/" + resultSet.getString(9) + " width=100 height=100/>";
        stringBuffer.append("<td>" + str + "</td>");
        stringBuffer.append("</tr>");
      } 
    } catch (Exception exception) {
      paramHttpServletResponse.setContentType("text/html;charset=utf-8");
      PrintWriter printWriter = paramHttpServletResponse.getWriter();
      printWriter.println("<html><body>");
      printWriter.println("查询不到结果");
      printWriter.println("回到浏览会员页面:<a href='lookMember.jsp'>");
      printWriter.println("</body></html>");
    } 
    showResult.setResult(new String(stringBuffer));
    System.out.println(showResult.getResult());
    RequestDispatcher requestDispatcher = paramHttpServletRequest.getRequestDispatcher("showMember.jsp");
    requestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
  }
  
  private void showMessage(String paramString, HttpServletResponse paramHttpServletResponse) throws IOException {
    paramHttpServletResponse.setContentType("text/html;charSet=utf-8");
    PrintWriter printWriter = paramHttpServletResponse.getWriter();
    printWriter.println("<html><body>");
    printWriter.println(paramString + ",");
    printWriter.println("<a href='lookMember.jsp'>继续查看</a>");
    printWriter.println("</body></html>");
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException { doGet(paramHttpServletRequest, paramHttpServletResponse); }
}
