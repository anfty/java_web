package tom.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tom.bean.Login;
import tom.bean.UpdateRegister;




public class UpdateRegisterMessage extends HttpServlet {
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
      continueDoGet(paramHttpServletRequest, paramHttpServletResponse); 
  }
  
  private void continueDoGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    HttpSession httpSession = paramHttpServletRequest.getSession(true);
    Login login = (Login)httpSession.getAttribute("login");
    String str1 = login.getLogname();
    UpdateRegister updateRegister = new UpdateRegister();
    paramHttpServletRequest.setAttribute("updateRegister", updateRegister);
    paramHttpServletRequest.setCharacterEncoding("utf-8");
    String str2 = paramHttpServletRequest.getParameter("newSex");
    int i = Integer.parseInt(paramHttpServletRequest.getParameter("newAge"));
    String str3 = paramHttpServletRequest.getParameter("newEmail");
    String str4 = paramHttpServletRequest.getParameter("newPhone");
    String str5 = paramHttpServletRequest.getParameter("newMessage");
    String str6 = paramHttpServletRequest.getParameter("aname");
    String str7 = "";
    if (str1.equals("admin")) {
      str7 = str6;
    } else {
      str7 = str1;
    } 
    try {
      String str8 = "jdbc:mysql://127.0.0.1:3306/test";
      String str9 = "test";
      String str10 = "123456";
      Connection connection = DriverManager.getConnection(str8, str9, str10);
      Statement statement = connection.createStatement();
      String str11 = "update member set sex='" + str2 + "',age='" + i + "',email='" + str3 + "',phone='" + str4 + "',message='" + str5 + "' where logname='" + str7 + "'";
      int j = statement.executeUpdate(str11);
      if (j != 0) {
        updateRegister.setLogname(str7);
        updateRegister.setNewAge(i);
        updateRegister.setNewSex(str2);
        updateRegister.setNewEmail(str3);
        updateRegister.setNewPhone(str4);
        updateRegister.setNewMessage(str5);
      } else {
        paramHttpServletResponse.setContentType("text/html;charset=GB2312");
        PrintWriter printWriter = paramHttpServletResponse.getWriter();
        printWriter.println("<html><body>");
        printWriter.println("信息填写不完整或信息中有非法字符");
        printWriter.println("重新修改注册信息:<a href='updateReg.jsp'>");
        printWriter.println("</body></html>");
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    RequestDispatcher requestDispatcher = paramHttpServletRequest.getRequestDispatcher("updateRegisterMessage.jsp");
    requestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException { doGet(paramHttpServletRequest, paramHttpServletResponse); }
}
