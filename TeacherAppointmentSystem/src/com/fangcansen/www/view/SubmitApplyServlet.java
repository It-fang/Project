package com.fangcansen.www.view;

import com.fangcansen.www.po.Application;
import com.fangcansen.www.po.Student;
import com.fangcansen.www.po.StudentUser;
import com.fangcansen.www.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet(name = "submitApplyServlet",urlPatterns = "/submitApplyServlet")
public class SubmitApplyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String _teacherId = request.getParameter("teacherId");
        int teacherId = Integer.parseInt(_teacherId);
        String _studentId = request.getParameter("studentId");
        int studentId = Integer.parseInt(_studentId);
        String teacherName = request.getParameter("teacherName");
        String studentName = request.getParameter("name");
        String studentNumber = request.getParameter("number");
        String applyTime = request.getParameter("time");
        //3,封装申请表对象
        Application application = new Application();
        application.setTeacherId(teacherId);
        application.setTeacherName(teacherName);
        application.setStudentId(studentId);
        application.setStudentName(studentName);
        application.setStudentNumber(studentNumber);
        application.setApplyTime(applyTime);
        //4,调用applicationService将申请表存入数据库中
        ApplicationService applicationService = new ApplicationService();
        try {
            applicationService.add(application);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //6,封装studentUser对象
        StudentUser studentUser = new StudentUser();
        studentUser.setStudentId(studentId);
        //7,将studentUser存入request域中
        request.setAttribute("studentUser",studentUser);
        //8,转发到queryteacher.jsp
        request.getRequestDispatcher("/queryteacher.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
