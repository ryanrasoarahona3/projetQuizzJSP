package org.maggy.projetquizzjsp.service;

import org.maggy.projetquizzjsp.dao.QuizzDAO;
import org.maggy.projetquizzjsp.model.User;

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

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }
}
