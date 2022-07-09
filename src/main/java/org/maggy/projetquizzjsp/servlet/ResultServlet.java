package org.maggy.projetquizzjsp.servlet;

import org.maggy.projetquizzjsp.model.User;
import org.maggy.projetquizzjsp.service.SessionManager;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name="resultServlet", value="/result")
public class ResultServlet extends BaseServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = SessionManager.getInstance().getActiveUser();
        Map quizzAnswer = request.getParameterMap();



    }
}