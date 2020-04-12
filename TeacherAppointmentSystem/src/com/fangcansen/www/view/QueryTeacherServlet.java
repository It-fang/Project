package com.fangcansen.www.view;

import com.fangcansen.www.dao.TeacherDao;
import com.fangcansen.www.po.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "queryTeacherServlet",urlPatterns = "/queryTeacherServlet")
public class QueryTeacherServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,设置编码
        request.setCharacterEncoding("utf-8");
        //2,获取请求参数
        String term = request.getParameter("term");
        //3,获得数据库教师列表
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> teachers = null;
        if (term == null){
            try {
                teachers = teacherDao.queryAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                teachers = teacherDao.query(term);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //4,将list存入request域中
        request.setAttribute("teachers",teachers);
        //5,转发到queryteacher.jsp中去
        request.getRequestDispatcher("/queryteacher.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
