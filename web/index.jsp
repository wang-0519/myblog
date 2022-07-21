<%@ page import="java.util.Scanner" %>
<%@ page import="java.io.*" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/7/17
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
  <head>
    <title>超前还在努力</title>
    <link rel="stylesheet" type="text/css" href="css/mystyle.css" charset="UTF-8">
  </head>
  <body style="background-color: aquamarine">
    <div id="page">
      <div id="info" class="horizontal">
        <div id="information" class="left_range">
          <img id="head" src="res/headpicture.jpg">
          <div id="name">一只小白</div>
        </div>
        <div id="navigation" class="left_range">
          <ul>
            <li><a href="/blogfile?filename=<%="adb"%>">adb命令</a></li>
            <li><a href="/blogfile">adb命令</a></li>
          </ul>
        </div>
      </div>

      <div id="content" class="horizontal">
          <%
              String title = (String) request.getAttribute("filetitle");
              String content = (String) request.getAttribute("filecontent");
              System.out.println("filecontent = " + content);
              if(content == null){
                  StringBuilder sb = new StringBuilder();
                  try{
                      File file = new File("F:\\item\\myblog\\web\\res\\readme.txt");
                      BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                      String temp;
                      while((temp = reader.readLine()) != null){
                          System.out.println("hasNetLine: " + temp);
                          sb.append(temp);
                          sb.append("<br>");
                      }
                      reader.close();
                      content = sb.toString();
                  } catch (IOException io){
                      io.printStackTrace();
                  }
              }
          %>
        <div id="content_title" class="right_range">
          <h2><%=title%></h2>
        </div>
        <div id="content_info" class="right_range">
          <h4><%=content%></h4>
        </div>
      </div>
    </div>
  </body>
</html>
