package org.maggy.projetquizzjsp.servlet;


import org.maggy.projetquizzjsp.dao.QuizzDAO;
import org.maggy.projetquizzjsp.model.Quizz;
import org.maggy.projetquizzjsp.model.User;
import org.maggy.projetquizzjsp.service.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="quizzServlet", value="/quizz")
public class QuizzServlet extends BaseServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        try {
            DatabaseManager.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("crud-quizz.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            DatabaseManager.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Quizz q = new Quizz();
        q.setQuestion(request.getParameter("question"));
        q.setReponse1(request.getParameter("reponse1"));
        q.setReponse2(request.getParameter("reponse2"));
        q.setReponse3(request.getParameter("reponse3"));
        q.setReponse4(request.getParameter("reponse4"));
        q.setAnswer(Integer.valueOf(request.getParameter("answer")));
        try {
            QuizzDAO.getInstance().add(q);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("quizz");
    }
}
