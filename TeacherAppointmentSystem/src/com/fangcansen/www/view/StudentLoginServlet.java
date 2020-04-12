package com.fangcansen.www.view;

import com.fangcansen.www.dao.StudentUserDao;
import com.fangcansen.www.po.StudentUser;

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
@WebServlet(name = "studentLoginServlet",urlPatterns = "/studentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获得请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3,获得学生用户对象
        StudentUserDao studentUserDao = new StudentUserDao();
        StudentUser studentUser = null;
        try {
            studentUser = studentUserDao.get(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,判断用户是否存在
        if(studentUser == null){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户不存在");
        }
        //5,判断密码是否输入正确
        if(studentUser.getPassword().equals(password)){
            response.sendRedirect("/TeacherAppointmentSystem_war_exploded/function.html");
        }else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("密码不正确，请重新输入");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
