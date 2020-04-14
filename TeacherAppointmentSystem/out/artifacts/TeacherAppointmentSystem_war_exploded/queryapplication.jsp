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
    <h3><p class="text-center">学生预约申请列表</p></h3>
    <div style="float:left; margin: 10px;">
        <a class="btn btn-primary" href="/TeacherAppointmentSystem_war_exploded/modifyServlet?id=${teacherUser.teacherId}" role="button">修改自己信息</a>
    </div>
    <div style="float:right; margin: 5px;">
        <a class="btn btn-lg btn-success" href="/TeacherAppointmentSystem_war_exploded/queryApplicationServlet?id=${teacherUser.teacherId}" role="button">显示所有预约申请</a>
    </div>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>学生姓名</th>
            <th>学生学号</th>
            <th>申请时间</th>
            <th>是否同意</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${applications}" var="application" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${application.studentName}</td>
                <td>${application.studentNumber}</td>
                <td>${application.applyTime}</td>
                <td>${application.ifAgree}</td>
                <td>
                    <p>
                        <a href="/TeacherAppointmentSystem_war_exploded/showServlet?id=${application.teacherId}&number=${application.studentNumber}" class="btn btn-primary btn-xs active" role="button">审批</a>
                        <a href="/TeacherAppointmentSystem_war_exploded/deleteApplicationServlet?id=${application.teacherId}&number=${application.studentNumber}" class="btn btn-primary btn-xs active" role="button">删除</a>
                    </p>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
