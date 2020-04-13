<%--
  Created by IntelliJ IDEA.
  User: 92540
  Date: 2020/4/10
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Teacher Appointment System</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td,th{
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>id</th>
            <th>name</th>
            <th>number</th>
            <th>major</th>
            <th>class</th>
            <th>freetime</th>
            <th>appointment</th>
        </tr>
        <c:forEach items="${page.list}" var="teacher" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${teacher.name}</td>
                <td>${teacher.college}</td>
                <td>${teacher.major}</td>
                <td>${teacher.clas}</td>
                <td>${teacher.freetime}</td>
                <td><a href="/TeacherAppointmentSystem_war_exploded/applicationServlet?id=${teacher.id}&name=${teacher.name}" class="btn btn-primary btn-sm active" role="button">预约</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
