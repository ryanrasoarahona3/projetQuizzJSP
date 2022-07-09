package org.maggy.projetquizzjsp.service;

import org.maggy.projetquizzjsp.dao.QuizzDAO;
import org.maggy.projetquizzjsp.dao.UserDAO;
import org.maggy.projetquizzjsp.model.Quizz;
import org.maggy.projetquizzjsp.model.User;

import java.sql.SQLException;

// Initialiser données
public class DataSeedManager {
    private static DataSeedManager instance = null;
    public static DataSeedManager getInstance(){
        if(instance == null)
            instance = new DataSeedManager();
        return instance;
    }
    public void tearDown(){
        instance = null;
    }

    UserDAO ud = UserDAO.getInstance();
    QuizzDAO qd = QuizzDAO.getInstance();
    public DataSeedManager(){

    }

    public void sow() throws SQLException {
        ud.destroySQLTable();
        ud.buildSQLTable();
        qd.destroySQLTable();
        qd.buildSQLTable();

        User u1 = new User();
        u1.setEmail("maggy@gmail.com");
        u1.setPseudo("maggy");
        u1.setPassword("motdepasse");
        u1.setPrivilege("user");

        User u2 = new User();
        u2.setEmail("ryan@gmail.com");
        u2.setPseudo("ryan");
        u2.setPassword("motdepasse2");
        u2.setPrivilege("user");

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPseudo("admin");
        admin.setPassword("motdepasseAdmin");
        admin.setPrivilege("admin");

        ud.add(u1);
        ud.add(u2);
        ud.add(admin);


        Quizz q1 = new Quizz();
        q1.setQuestion("3+3 ?");
        q1.setReponse1("3");
        q1.setReponse2("6");
        q1.setReponse3("9");
        q1.setReponse4("10");
        q1.setAnswer(2);
        qd.add(q1);

        Quizz q2 = new Quizz();
        q2.setQuestion("Plus grand pays ?");
        q2.setReponse1("Russie");
        q2.setReponse2("Brésil");
        q2.setReponse3("Chine");
        q2.setReponse4("USA");
        q2.setAnswer(1);
        qd.add(q2);

        Quizz q3 = new Quizz();
        q3.setQuestion("Capitale de Madagascar ?");
        q3.setReponse1("Moscou");
        q3.setReponse2("Paris");
        q3.setReponse3("Antananrivo");
        q3.setReponse4("Pekin");
        q3.setAnswer(3);
        qd.add(q3);



    }
}
