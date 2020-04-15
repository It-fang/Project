package com.fangcansen.www.view;

import com.fangcansen.www.po.Student;
import com.fangcansen.www.po.StudentUser;
import com.fangcansen.www.service.StudentUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "queryRegisterApplicationServlet",urlPatterns = "/queryRegisterApplicationServlet")
public class QueryRegisterApplicationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,调用StudentUserService获取studentUsers对象
        StudentUserService studentUserService = new StudentUserService();
        List<StudentUser> studentUsers = null;
        try {
            studentUsers = studentUserService.queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //2,将studentUsers存入request域中
        request.setAttribute("studentUsers",studentUsers);
        //3,将request转发到registerapplication.jsp
        request.getRequestDispatcher("/registerapplication.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
