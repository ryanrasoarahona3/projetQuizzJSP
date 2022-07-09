package com.maggy.projetquizzjsp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maggy.projetquizzjsp.dao.UserDAO;
import org.maggy.projetquizzjsp.model.User;
import org.maggy.projetquizzjsp.service.DataSeedManager;
import org.maggy.projetquizzjsp.service.DatabaseManager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserTest {
    UserDAO ud;
    DatabaseManager dm;

    @BeforeEach
    public void init() throws SQLException {
        ud = UserDAO.getInstance();
        dm = DatabaseManager.getInstance();
        dm.init();

        ud.destroySQLTable();
        ud.buildSQLTable();
    }

    @Test
    public void testInsertion() throws SQLException {
        User u = new User();
        u.setEmail("maggy@gmail.com");
        u.setPseudo("maggy");
        u.setPassword("password");
        u.setPrivilege("user");

        ud.add(u);
        assertEquals(u.getId(), 1);

        ArrayList<User> user_list = ud.getAll();
        assertEquals(user_list.size(), 1);
    }


    @Test
    void verifyCredentials() throws SQLException {
        User u1 = new User();
        u1.setEmail("john@gmail.com");
        u1.setPassword("password");
        ud.add(u1);
        User u = new User();

        u.setEmail("john@gmail.com");
        u.setPassword("pass");
        assertEquals(false, ud.verifyCredentials(u));

        u.setEmail("john@gmail.co");
        u.setPassword("password");
        assertEquals(false, ud.verifyCredentials(u));

        u.setEmail("john@gmail.com");
        u.setPassword("password");
        assertEquals(true, ud.verifyCredentials(u));
    }
    
    @Test
    void sowSeed() throws SQLException {
        DataSeedManager.getInstance().sow();
    }

    @AfterEach
    public void tearDown(){

    }
}
