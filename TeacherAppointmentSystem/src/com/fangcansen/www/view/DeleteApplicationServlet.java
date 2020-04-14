package com.fangcansen.www.view;

import com.fangcansen.www.po.TeacherUser;
import com.fangcansen.www.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author it-fang
 */
@WebServlet(name = "deleteApplicationServlet",urlPatterns = "/deleteApplicationServlet")
public class DeleteApplicationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String _teacherId = request.getParameter("id");
        String studentNumber = request.getParameter("number");
        int teacherId = Integer.parseInt(_teacherId);
        //3,调用ApplicationService
        ApplicationService applicationService = new ApplicationService();
        try {
            applicationService.delete(studentNumber,teacherId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,封装TeacherUser对象
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setTeacherId(teacherId);
        //5,将teacherUser对象存入request域中
        request.setAttribute("teacherUser",teacherUser);
        //6,将request转发到queryapplication.jsp中
        request.getRequestDispatcher("/queryapplication.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
