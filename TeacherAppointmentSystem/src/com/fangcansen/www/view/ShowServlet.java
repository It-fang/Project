package com.fangcansen.www.view;

import com.fangcansen.www.po.Student;
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
 * 展示申请学生的具体信息
 */
@WebServlet(name = "showServlet" , urlPatterns = "/showServlet")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String teacherId = request.getParameter("id");
        String studentNumber = request.getParameter("number");
        //3,获取学生对象
        ApplicationService applicationService = new ApplicationService();
        Student student = null;
        try {
            student = applicationService.get(studentNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,将student对象,teacherId存入request域中
        request.setAttribute("student",student);
        request.setAttribute("teacherId",teacherId);
        //5,转发request到agree.jsp
        request.getRequestDispatcher("/agree.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
