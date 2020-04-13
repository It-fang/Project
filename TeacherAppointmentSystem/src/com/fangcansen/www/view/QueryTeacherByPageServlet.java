package com.fangcansen.www.view;

import com.fangcansen.www.po.Page;
import com.fangcansen.www.po.Teacher;
import com.fangcansen.www.service.TeacherUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "queryTeacherByPageServlet",urlPatterns = "/queryTeacherByPageServlet")
public class QueryTeacherByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        //获取查询条件参数
        Map<String, String[]> condition = request.getParameterMap();
        //3,调用service
        TeacherUserService teacherUserService = new TeacherUserService();
        Page<Teacher> page = null;
        try {
            page = teacherUserService.findUserByPage(currentPage,rows,condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4,将Page存入request域中
        request.setAttribute("page",page);
        request.setAttribute("condition",condition);
        //5,转发Page到queryteacher.jsp
        request.getRequestDispatcher("/queryteacher.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
