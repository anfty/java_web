package tom.servlet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tom.bean.Login;
import tom.bean.UploadPic;


public class UploadPicture extends HttpServlet {
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
    if (bool == true) {
      String str = login.getLogname();
      uploadPicture(paramHttpServletRequest, paramHttpServletResponse, str);
    } 
  }
  
  private void uploadPicture(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, String paramString) {
    UploadPic uploadPic = new UploadPic();
    String str = "";
    try {
      HttpSession httpSession = paramHttpServletRequest.getSession(true);
      paramHttpServletRequest.setAttribute("upload", uploadPic);
      String str1 = httpSession.getId();
      File file1 = new File("/home/act/test/java/glxt/apache-tomcat-8.5.72/webapps/ROOT/upload", str1);
      FileOutputStream fileOutputStream = new FileOutputStream(file1);
      ServletInputStream servletInputStream = paramHttpServletRequest.getInputStream();
      byte[] arrayOfByte1 = new byte[10000];
      int i;
      while ((i = servletInputStream.read(arrayOfByte1)) != -1)
        fileOutputStream.write(arrayOfByte1, 0, i); 
      fileOutputStream.close();
      servletInputStream.close();
      RandomAccessFile randomAccessFile1 = new RandomAccessFile(file1, "r");
      String str2 = "";
      int j;
      for (j = 1; j <= 2; j++)
        str2 = randomAccessFile1.readLine(); 
      j = str2.lastIndexOf('=');
      String str3 = str2.substring(j + 2, str2
          .length() - 1);
      byte[] arrayOfByte2 = str3.getBytes("ISO-8859-1");
      str3 = new String(arrayOfByte2);
      str3 = str3.replaceAll(" ", "");
      String str4 = str3.substring(0, str3.indexOf("."));
      boolean bool = true;
      for (byte b1 = 0; b1 < str4.length(); b1++) {
        char c = str4.charAt(b1);
        if ((c > 'z' || c < 'a') && (c > 'Z' || c < 'A') && (c > '9' || c < '0')) {
          bool = false;
          break;
        } 
      } 
      if (!bool)
        paramHttpServletResponse.sendRedirect("uploadPic.jsp"); 
      String str5 = paramString.concat(str3);
      randomAccessFile1.seek(0L);
      long l1 = 0L;
      byte b2 = 1;
      while ((i = randomAccessFile1.readByte()) != -1 && b2 <= 4) {
        if (i == 10) {
          l1 = randomAccessFile1.getFilePointer();
          b2++;
        } 
      } 
      File file2 = new File("/home/act/test/java/glxt/apache-tomcat-8.5.72/webapps/ROOT/upload", str5);
      file2.mkdir();
      File[] arrayOfFile = file2.listFiles();
      for (byte b3 = 0; b3 < arrayOfFile.length; b3++) {
        if (arrayOfFile[b3].getName().startsWith(paramString))
          arrayOfFile[b3].delete(); 
      } 
      File file3 = new File(file2, str5);
      RandomAccessFile randomAccessFile2 = new RandomAccessFile(file3, "rw");
      randomAccessFile1.seek(randomAccessFile1.length());
      long l2 = randomAccessFile1.getFilePointer();
      long l3 = l2;
      byte b4 = 1;
      while (l3 >= 0L && b4 <= 6) {
        l3--;
        randomAccessFile1.seek(l3);
        i = randomAccessFile1.readByte();
        if (i == 10) {
          l2 = randomAccessFile1.getFilePointer();
          b4++;
        } 
      } 
      randomAccessFile1.seek(l1);
      long l4 = randomAccessFile1.getFilePointer();
      while (l4 < l2 - 1L) {
        i = randomAccessFile1.readByte();
        randomAccessFile2.write(i);
        l4 = randomAccessFile1.getFilePointer();
      } 
      randomAccessFile2.close();
      randomAccessFile1.close();
      String str6 = "jdbc:mysql://127.0.0.1:3306/test";
      String str7 = "test";
      String str8 = "123456";
      Connection connection = DriverManager.getConnection(str6, str7, str8);
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from member where logname='" + paramString + "'");
      if (resultSet.next() && 
        bool) {
        int k = statement.executeUpdate("UPDATE member SET pic= '" + str5 + "' where logname = '" + paramString + "'");
        str = str3 + "成功上传";
        uploadPic.setFileName(str3);
        uploadPic.setSavedFileName(str5);
        uploadPic.setBackNews(str);
      } 
      connection.close();
      file1.delete();
    } catch (Exception exception) {
      str = "" + exception;
      uploadPic.setBackNews(str);
      exception.printStackTrace();
    } 
    try {
      RequestDispatcher requestDispatcher = paramHttpServletRequest.getRequestDispatcher("showUploadMessage.jsp");
      requestDispatcher.forward(paramHttpServletRequest, paramHttpServletResponse);
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  protected void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse) throws ServletException, IOException { doGet(paramHttpServletRequest, paramHttpServletResponse); }
}
