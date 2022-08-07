<%@ page import="java.util.*" %>
<%@ page import="data.BlogFile" %>
<%--
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
    <title>一只小白</title>
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
                <%
                    Object obj = session.getAttribute("navigation");
                    List<Object> list = null;
                    List<BlogFile> blogs = new ArrayList<>();
                    if (obj != null && obj instanceof List) {
                        list = (List<Object>)obj;
                        for (Object temp : list) {
                            if (temp instanceof BlogFile) {
                                blogs.add((BlogFile)temp);
                            }
                        }
                    }
                    for (BlogFile blog : blogs) {
                        String filename = blog.getFileName();
                        if (filename.contains(".md")) {
                            filename = filename.substring(0, filename.length() - 3);
                        }
                        out.write("<li><a href=\"/blogfile?fileId=" + blog.getId() + "\">" + filename + "</a></li>");
                    }
                %>
                <%--<c:forEach var="blog" items="<%=blogs%>>">--%>
                    <%--<li><a href="/blogfile?fileId=<%=blogs.%>">${blog.fileName}</a></li>--%>
                <%--</c:forEach>--%>
            </ul>
        </div>
    </div>

    <div id="content" class="horizontal">
        <%
            String title = (String) request.getAttribute("filetitle");
            if (title == null || title.length() == 0) {
                title = "说明";
            }
            String content = (String) request.getAttribute("filecontent");
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
