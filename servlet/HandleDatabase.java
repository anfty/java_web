package tom.servlet;

import com.sun.rowset.CachedRowSetImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tom.bean.Login;
import tom.bean.ShowByPage;


public class HandleDatabase extends HttpServlet {
  CachedRowSetImpl rowSet = null;
  
  public void init(ServletConfig paramServletConfig) throws ServletException {
    super.init(paramServletConfig);
    try {
      Class.forName("com.mysql.jdbc.Driver")
        .newInstance();
    } catch (Exception exception) {}
  }
  
  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    HttpSession httpSession = paramHttpServletRequest.getSession(true);
    Login login = (Login)httpSession.getAttribute("login");
    boolean bool = true;
    String str = "admin";
    if (login == null) {
      bool = false;
      paramHttpServletResponse.sendRedirect("login.jsp");
    } else {
      String str1 = login.getLogname();
      if (str1.equals(str)) {
        continueDoPost(paramHttpServletRequest, paramHttpServletResponse);
      } else {
        showMessage("仅限管理员使用！", paramHttpServletResponse);
      } 
    } 
  }
  
  public void continueDoPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    HttpSession httpSession = paramHttpServletRequest.getSession(true);
    Connection connection = null;
    StringBuffer stringBuffer = new StringBuffer();
    ShowByPage showByPage = null;
    try {
      showByPage = (ShowByPage)httpSession.getAttribute("show");
      if (showByPage == null) {
        showByPage = new ShowByPage();
        httpSession.setAttribute("show", showByPage);
      } 
    } catch (Exception exception) {
      showByPage = new ShowByPage();
      httpSession.setAttribute("show", showByPage);
    } 
    showByPage.setPageSize(3);
    int i = Integer.parseInt(paramHttpServletRequest.getParameter("showPage"));
    if (i > showByPage.getPageAllCount())
      i = 1; 
    if (i <= 0)
      i = showByPage.getPageAllCount(); 
    showByPage.setShowPage(i);
    int j = showByPage.getPageSize();
    String str = "jdbc:mysql://127.0.0.1:3306/test";
    try {
      connection = DriverManager.getConnection(str, "test", "123456");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM member");
      this.rowSet = new CachedRowSetImpl();
      this.rowSet.populate(resultSet);
      connection.close();
      showByPage.setRowSet(this.rowSet);
      this.rowSet.last();
      int k = this.rowSet.getRow();
      int m = j;
      int n = (k % m == 0) ? (k / m) : (k / m + 1);
      showByPage.setPageAllCount(n);
      stringBuffer = show(i, j, this.rowSet);
      showByPage.setPresentPageResult(stringBuffer);
    } catch (SQLException sQLException) {}
    RequestDispatcher requestDispatcher = paramHttpServletRequest.getRequestDispatcher("showAllMember.jsp");
    requestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
  }
  
  public StringBuffer show(int paramInt1, int paramInt2, CachedRowSetImpl paramCachedRowSetImpl) {
    StringBuffer stringBuffer = new StringBuffer();
    try {
      paramCachedRowSetImpl.absolute((paramInt1 - 1) * paramInt2 + 1);
      for (byte b = 1; b <= paramInt2; b++) {
        stringBuffer.append("<tr>");
        stringBuffer.append("<td>" + paramCachedRowSetImpl.getString(2) + "</td>");
        stringBuffer.append("<td>" + paramCachedRowSetImpl.getString(4) + "</td>");
        stringBuffer.append("<td>" + paramCachedRowSetImpl.getString(5) + "</td>");
        stringBuffer.append("<td>" + paramCachedRowSetImpl.getString(6) + "</td>");
        stringBuffer.append("<td>" + paramCachedRowSetImpl.getString(7) + "</td>");
        stringBuffer.append("<td>" + paramCachedRowSetImpl.getString(8) + "</td>");
        String str = "<img src=/upload/" + paramCachedRowSetImpl.getString(9) + "/" + paramCachedRowSetImpl.getString(9) + " width=100 height=100/>";
        stringBuffer.append("<td>" + str + "</td>");
        stringBuffer.append("</tr>");
        paramCachedRowSetImpl.next();
      } 
    } catch (SQLException sQLException) {}
    return stringBuffer;
  }
  
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException {
    HttpSession httpSession = paramHttpServletRequest.getSession(true);
    Login login = (Login)httpSession.getAttribute("login");
    boolean bool = true;
    if (login == null) {
      bool = false;
      paramHttpServletResponse.sendRedirect("login.jsp");
    } 
    if (bool == true)
      doPost(paramHttpServletRequest, paramHttpServletResponse); 
  }
  
  private void showMessage(String paramString, HttpServletResponse paramHttpServletResponse) throws IOException {
    paramHttpServletResponse.setContentType("text/html;charSet=utf-8");
    PrintWriter printWriter = paramHttpServletResponse.getWriter();
    printWriter.println("<html><body>");
    printWriter.println(paramString + ",");
    printWriter.println("<a href='lookMember.jsp'>继续查看</a>");
    printWriter.println("</body></html>");
  }
}
