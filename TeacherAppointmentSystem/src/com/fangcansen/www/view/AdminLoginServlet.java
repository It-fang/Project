package com.fangcansen.www.view;

import com.fangcansen.www.dao.AdminDao;
import com.fangcansen.www.po.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "adminLoginServlet",urlPatterns = "/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3,获得超级用户对象
        AdminDao adminDao = new AdminDao();
        Admin admin = null;
        try {
            admin = adminDao.get(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,判断超级用户对象是否存在
        if(admin == null){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户不存在");
        }
        if(admin.getPassword().equals(password)){
            response.sendRedirect("/TeacherAppointmentSystem_war_exploded/teacherregister.html");
        }else{
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("密码不正确，请重新输入");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
