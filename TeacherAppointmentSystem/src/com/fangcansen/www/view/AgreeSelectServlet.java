package com.fangcansen.www.view;

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

@WebServlet(name = "agreeSelectServlet",urlPatterns = "/agreeSelectServlet")
public class AgreeSelectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String _teacherId = request.getParameter("id");
        String[] studentNumbers = request.getParameterValues("studentNumbers");
        int teacherId = Integer.parseInt(_teacherId);
        //3,调用ApplicationService将所选申请表设置成同意
        ApplicationService applicationService = new ApplicationService();
        try {
            applicationService.agreeSelect(teacherId,studentNumbers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,封装teacherUser对象
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setTeacherId(teacherId);
        //5,将teacherUser存入request域中
        request.setAttribute("teacherUser",teacherUser);
        //6,将request 转发到queryapplication.jsp中
        request.getRequestDispatcher("/queryapplication.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
