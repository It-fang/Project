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

@WebServlet(name = "registerStudentUserServlet" ,urlPatterns = "/registerStudentUserServlet")
public class RegisterStudentUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String number = request.getParameter("number");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String clas = request.getParameter("clas");
        //3,判断获取到的参数是否为空
        if ("".equals(username) || "".equals(password) || "".equals(name) || "".equals(sex) || "".equals(number) || "".equals(college) || "".equals(major) || "".equals(clas) ){
            response.sendRedirect("/TeacherAppointmentSystem_war_exploded/studentregister.html");
            return;
        }
        //3,封装student对象
        Student student = new Student();
        student.setName(name);
        student.setSex(sex);
        student.setNumber(number);
        student.setCollege(college);
        student.setMajor(major);
        student.setClas(clas);
        //4,调用StudentUserService的registerStudent方法获得studentID
        StudentUserService studentUserService = new StudentUserService();
        int studentid = 0;
        try {
            studentid = studentUserService.registerStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5,封装studentUser对象
        StudentUser studentUser = new StudentUser();
        studentUser.setUsername(username);
        studentUser.setPassword(password);
        studentUser.setStudentId(studentid);
        //6,调用StudentUserService的registerStudentUser的方法完成注册
        try {
            studentUserService.registerStudentUser(studentUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/TeacherAppointmentSystem_war_exploded/studentauto.html");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
