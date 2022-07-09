package org.maggy.projetquizzjsp.servlet;


import org.maggy.projetquizzjsp.model.User;
import org.maggy.projetquizzjsp.service.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="quizzServlet", value="/quizz")
public class QuizzServlet extends BaseServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        try {
            DatabaseManager.getInstance().init();
        }catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("crud-quizz.jsp").include(request, response);
    }
}