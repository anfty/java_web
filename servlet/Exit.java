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
  
  public void continueDoGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    HttpSession httpSession = paramHttpServletRequest.getSession(true);
    httpSession.invalidate();
    paramHttpServletResponse.sendRedirect("index.jsp");
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException { doGet(paramHttpServletRequest, paramHttpServletResponse); }
}
