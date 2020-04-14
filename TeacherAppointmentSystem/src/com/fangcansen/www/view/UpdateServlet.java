package com.fangcansen.www.view;

import com.fangcansen.www.po.Teacher;
import com.fangcansen.www.po.TeacherUser;
import com.fangcansen.www.service.TeacherUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "updateServlet",urlPatterns = "/updateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String _id = request.getParameter("id");
        String name = request.getParameter("name");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String clas = request.getParameter("clas");
        String freetime = request.getParameter("freetime");
        int id = Integer.parseInt(_id);
        //3,封装teacher对象
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setCollege(college);
        teacher.setMajor(major);
        teacher.setClas(clas);
        teacher.setFreetime(freetime);
        //4,调用TeacherUserService存入数据库
        TeacherUserService teacherUserService = new TeacherUserService();
        try {
            teacherUserService.update(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5,设置TeacherUser
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setTeacherId(id);
        //6,将teacherUser对象存入request域中
        request.setAttribute("teacherUser",teacherUser);
        //7,将request转发到queryapplication.jsp
        request.getRequestDispatcher("/queryapplication.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
