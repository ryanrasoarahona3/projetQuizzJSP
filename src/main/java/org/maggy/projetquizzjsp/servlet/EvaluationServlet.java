package org.maggy.projetquizzjsp.servlet;

import org.maggy.projetquizzjsp.dao.UserDAO;
import org.maggy.projetquizzjsp.model.User;
import org.maggy.projetquizzjsp.service.DatabaseManager;
import org.maggy.projetquizzjsp.service.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="evaluationServlet", value="/evaluation")
public class EvaluationServlet extends BaseServlet {
    User user = null;
    UserDAO ud = UserDAO.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DatabaseManager.getInstance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("login.jsp").include(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        try {
            DatabaseManager.getInstance().init();
            if(ud.verifyCredentials(u)){
                SessionManager.getInstance().setActiveUser(
                        UserDAO.getInstance().searchByEmail(email)
                );
                request.getRequestDispatcher("evaluation.jsp").include(request, response);
            }else{
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}