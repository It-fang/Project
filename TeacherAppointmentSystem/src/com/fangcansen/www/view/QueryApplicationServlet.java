package com.fangcansen.www.view;

import com.fangcansen.www.po.Application;
import com.fangcansen.www.po.Teacher;
import com.fangcansen.www.po.TeacherUser;
import com.fangcansen.www.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "queryApplicationServlet",urlPatterns = "/queryApplicationServlet")
public class QueryApplicationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String teacherId = request.getParameter("id");
        //3,调用ApplicationService查询学生预约信息
        ApplicationService applicationService = new ApplicationService();
        List<Application> applications = null;
        try {
            applications = applicationService.query(teacherId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,封装TeacherUser对象
        TeacherUser teacherUser = new TeacherUser();
        int id = Integer.parseInt(teacherId);
        teacherUser.setTeacherId(id);
        //5,将applications,teacherUser存入request域中
        request.setAttribute("applications",applications);
        request.setAttribute("teacherUser",teacherUser);
        //6,将request转发到queryapplication.jsp中
        request.getRequestDispatcher("/queryapplication.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
