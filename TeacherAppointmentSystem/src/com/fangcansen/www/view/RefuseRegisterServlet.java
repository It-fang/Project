package com.fangcansen.www.view;

import com.fangcansen.www.service.StudentUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "refuseRegisterServlet",urlPatterns = "/refuseRegisterServlet")
public class RefuseRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String _studentId = request.getParameter("studentId");
        int studentId = Integer.parseInt(_studentId);
        //3,将registerapplication表中对应studentId的数据删除
        StudentUserService studentUserService = new StudentUserService();
        try {
            studentUserService.delete(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,跳转到registerapplication.jsp去
        response.sendRedirect("/TeacherAppointmentSystem_war_exploded/registerapplication.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
