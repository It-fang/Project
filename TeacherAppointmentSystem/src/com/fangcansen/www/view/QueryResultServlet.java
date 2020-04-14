package com.fangcansen.www.view;

import com.fangcansen.www.po.Application;
import com.fangcansen.www.po.Student;
import com.fangcansen.www.po.StudentUser;
import com.fangcansen.www.po.TeacherUser;
import com.fangcansen.www.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "queryResultServlet",urlPatterns = "/queryResultServlet")
public class QueryResultServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String _studentId = request.getParameter("studentId");
        int studentId = Integer.parseInt(_studentId);
        //3,调用ApplicationService查询预约结果
        ApplicationService applicationService  = new ApplicationService();
        List<Application> applications = null;
        try {
            applications = applicationService.query(studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,封装StudentUser对象
        StudentUser studentUser = new StudentUser();
        studentUser.setStudentId(studentId);
        //5,将applications,studentUser存入request域中
        request.setAttribute("applications",applications);
        request.setAttribute("studentUser",studentUser);
        //6,将request转发到queryapplication.jsp中
        request.getRequestDispatcher("/queryresult.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
