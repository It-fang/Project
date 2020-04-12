package com.fangcansen.www.view;

import com.fangcansen.www.po.Application;
import com.fangcansen.www.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet(name = "submitApplyServlet",urlPatterns = "/submitApplyServlet")
public class SubmitApplyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String Id = request.getParameter("teacherId");
        int teacherId = Integer.parseInt(Id);
        String teacherName = request.getParameter("teacherName");
        String studentName = request.getParameter("name");
        String studentNumber = request.getParameter("number");
        String applyTime = request.getParameter("time");
        //3,封装申请表对象
        Application application = new Application();
        application.setTeacherId(teacherId);
        application.setTeacherName(teacherName);
        application.setStudentName(studentName);
        application.setStudentNumber(studentNumber);
        application.setApplyTime(applyTime);
        //4,调用applicationService
        ApplicationService applicationService = new ApplicationService();
        try {
            applicationService.add(application);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5,跳转回查询列表
        response.sendRedirect("/TeacherAppointmentSystem_war_exploded/queryTeacherServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
