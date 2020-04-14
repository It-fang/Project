package com.fangcansen.www.view;

import com.fangcansen.www.po.Teacher;
import com.fangcansen.www.service.TeacherUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "modifyServlet",urlPatterns = "/modifyServlet")
public class ModifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String id = request.getParameter("id");
        //3,获取教师对象
        TeacherUserService teacherUserService = new TeacherUserService();
        Teacher teacher = null;
        try {
            teacher = teacherUserService.query(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,将teacher对象存入request域中
        request.setAttribute("teacher",teacher);
        //5,将request转发到modify.jsp中
        request.getRequestDispatcher("/modify.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
