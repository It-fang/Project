package com.fangcansen.www.view;

import com.fangcansen.www.po.Student;
import com.fangcansen.www.po.StudentUser;
import com.fangcansen.www.po.Teacher;
import com.fangcansen.www.po.TeacherUser;
import com.fangcansen.www.service.StudentUserService;
import com.fangcansen.www.service.TeacherUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "registerTeacherUserServlet" ,urlPatterns = "/registerTeacherUserServlet")
public class RegisterTeacherUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String clas = request.getParameter("clas");
        //3,封装teacher对象
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setCollege(college);
        teacher.setMajor(major);
        teacher.setClas(clas);
        //4,调用TeacherUserService的registerTeacher方法获得teacherID
        TeacherUserService teacherUserService = new TeacherUserService();
        int teacherId = 0;
        try {
            teacherId = teacherUserService.registerTeacher(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5,封装teacherUser对象
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setUsername(username);
        teacherUser.setPassword(password);
        teacherUser.setTeacherId(teacherId);
        //6,调用TeacherUserService的registerTeacherUser的方法完成注册
        try {
            teacherUserService.registerTeacherUser(teacherUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/TeacherAppointmentSystem_war_exploded/teacherauto.html");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
