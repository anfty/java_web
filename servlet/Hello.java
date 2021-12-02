package tom.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Hello extends HttpServlet{
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }

    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=GB2312");
        PrintWriter out=response.getWriter();
        out.println("hi servlet");

    }
}