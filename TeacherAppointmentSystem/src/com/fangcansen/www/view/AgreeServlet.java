package com.fangcansen.www.view;

import com.fangcansen.www.po.Teacher;
import com.fangcansen.www.po.TeacherUser;
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
 */
@WebServlet(name = "agreeServlet",urlPatterns = "/agreeServlet")
public class AgreeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String _teacherId = request.getParameter("teacherId");
        String studentNumber = request.getParameter("number");
        String ifAgree = request.getParameter("ifAgree");
        int teacherId = Integer.parseInt(_teacherId);
        //3,调用ApplicationService将ifAgree更新到对应字段中去
        ApplicationService applicationService = new ApplicationService();
        try {
            applicationService.update(teacherId,studentNumber,ifAgree);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,封装teacherUser对象
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setTeacherId(teacherId);
        //5,将teacherUser存入request域中
        request.setAttribute("teacherUser",teacherUser);
        //6,将request转发到queryapplication.jsp
        request.getRequestDispatcher("/queryapplication.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
