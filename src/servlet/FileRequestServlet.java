package servlet;

import javax.servlet.*;
import java.io.*;
import java.util.Enumeration;

/**
 * Created by admin on 2022/7/19.
 */
public class FileRequestServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String filename = servletRequest.getParameter("filename");
        System.out.println("filename = " + filename);
        if (filename != null){
            StringBuilder sb = new StringBuilder();
            try{
                File file = new File("F:\\文档\\learn_note\\notes\\Android\\adb命令.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                String temp;
                while((temp = reader.readLine()) != null){
                    System.out.println("hasNetLine: " + temp);
                    sb.append(temp);
                    sb.append("<br>");
                }
                reader.close();
                System.out.println("wcq+++++++++++++");
                System.out.println(sb.toString());
            } catch (IOException io){
                io.printStackTrace();
            }
            if (sb.length() != 0){
                servletRequest.setAttribute("filetitle", filename);
                servletRequest.setAttribute("filecontent", sb.toString());
            }
        }
        servletRequest.getRequestDispatcher("jsp/blog.jsp").forward(servletRequest, servletResponse);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
