package org.maggy.projetquizzjsp.servlet;

import org.maggy.projetquizzjsp.dao.QuizzDAO;
import org.maggy.projetquizzjsp.model.Quizz;
import org.maggy.projetquizzjsp.service.DatabaseManager;
import org.maggy.projetquizzjsp.service.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="quizzServletDelete", value="/quizz-modify")
public class QuizzServletModify extends BaseServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            DatabaseManager.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = Integer.valueOf(request.getParameter("id"));
        try {
            Quizz q = QuizzDAO.getInstance().get(id);
            SessionManager.getInstance().setActiveQuizz(q);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("crud-quizz-modify.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            DatabaseManager.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}