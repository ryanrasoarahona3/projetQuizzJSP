package org.maggy.projetquizzjsp.servlet;

import org.maggy.projetquizzjsp.dao.QuizzDAO;
import org.maggy.projetquizzjsp.model.User;
import org.maggy.projetquizzjsp.service.DatabaseManager;
import org.maggy.projetquizzjsp.service.SessionManager;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="resultServlet", value="/result")
public class ResultServlet extends BaseServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DatabaseManager.getInstance().init();
        }catch (Exception e) {
            e.printStackTrace();
        }


        User u = SessionManager.getInstance().getActiveUser();
        try {
            Map quizzResult = QuizzDAO.getInstance().evaluate(request.getParameterMap());
            SessionManager.getInstance().setQuizzResult(quizzResult);
            request.getRequestDispatcher("result.jsp").include(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}