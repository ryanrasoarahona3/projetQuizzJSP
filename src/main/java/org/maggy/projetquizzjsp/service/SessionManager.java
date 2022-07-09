package org.maggy.projetquizzjsp.service;

import org.maggy.projetquizzjsp.dao.QuizzDAO;
import org.maggy.projetquizzjsp.model.Quizz;
import org.maggy.projetquizzjsp.model.User;

import java.util.Map;

public class SessionManager {

    private static SessionManager instance = null;
    public static SessionManager getInstance(){
        if(instance == null)
            instance = new SessionManager();
        return instance;
    }
    public void tearDown(){
        instance = null;
    }

    private User activeUser;
    private Map<String, Boolean> quizzResult;
    private Quizz activeQuizz; // For modification

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public Map<String, Boolean> getQuizzResult() {
        return quizzResult;
    }

    public void setQuizzResult(Map<String, Boolean> quizzResult) {
        this.quizzResult = quizzResult;
    }

    public Quizz getActiveQuizz() {
        return activeQuizz;
    }

    public void setActiveQuizz(Quizz activeQuizz) {
        this.activeQuizz = activeQuizz;
    }
}
