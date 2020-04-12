package com.fangcansen.www.view;

import com.fangcansen.www.po.Application;
import com.fangcansen.www.service.ApplicationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet(name = "applicationServlet",urlPatterns = "/applicationServlet")
public class ApplicationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String teacherid = request.getParameter("id");
        String teacherName = request.getParameter("name");
        //3,将参数存入request域中
        request.setAttribute("teacherId",teacherid);
        request.setAttribute("teacherName",teacherName);
        //4,将参数转发到application.jsp
        request.getRequestDispatcher("/application.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
