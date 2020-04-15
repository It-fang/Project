package com.fangcansen.www.view;

import com.fangcansen.www.po.StudentUser;
import com.fangcansen.www.service.StudentUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "agreeRegisterServlet",urlPatterns = "/agreeRegisterServlet")
public class AgreeRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String _studentId = request.getParameter("studentId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int studentId = Integer.parseInt(_studentId);
        //3,封装studentUser对象
        StudentUser studentUser = new StudentUser();
        studentUser.setUsername(username);
        studentUser.setPassword(password);
        studentUser.setStudentId(studentId);
        //4,调用studentUserService对象将studentUser存入studentuser表中
        StudentUserService studentUserService = new StudentUserService();
        try {
            studentUserService.add(studentUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5,将registerapplication表中对应studentId的数据删除
        try {
            studentUserService.delete(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //6,跳转到registerapplication.jsp去
        response.sendRedirect("/TeacherAppointmentSystem_war_exploded/registerapplication.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
