package servlet;

import data.BlogFile;
import data.NavigationData;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 * Created by admin on 2022/7/19.
 */
public class FileRequestServlet extends HttpServlet {

    private static final String fileDirectory = "F:\\文档\\";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //添加目录信息
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("navigation");
        List<BlogFile> list =  NavigationData.getInstance().getBlogFiles();
        if (obj == null || !(obj instanceof List)) {
            session.setAttribute("navigation", list);
        }

        String idStr = req.getParameter("fileId");
        int fileId = 1;
        if (idStr != null) {
            fileId = Integer.valueOf(idStr);
        }
        String filepath = null;
        BlogFile blogFile = null;
        for (BlogFile file : NavigationData.getInstance().getBlogFiles()){
            if (fileId == file.getId()) {
                blogFile = file;
                break;
            }
        }
        if (blogFile != null) {
            filepath = fileDirectory + blogFile.getFilePath() + blogFile.getFileName();
            if (!blogFile.isFile()) {
                filepath += "/readme.md";
            }
        }
        if (filepath == null) {
            return;
        }
        System.out.println("filePath = " + filepath);
        StringBuilder sb = new StringBuilder();
        try{
            File file = new File(filepath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                String temp;
                while((temp = reader.readLine()) != null){
                    sb.append(temp);
                    sb.append("<br>");
                }
                reader.close();
            }
            System.out.println(sb.toString());
        } catch (IOException io){
            io.printStackTrace();
        }
        if (sb.length() != 0){
            String filename = blogFile.getFileName();
            req.setAttribute("filetitle", filename.contains(".md")
                    ? filename.substring(0, filename.length() - 3) : filename);
            req.setAttribute("filecontent", sb.toString());
        }
        req.getRequestDispatcher("jsp/blog.jsp").forward(req, resp);
    }

}
