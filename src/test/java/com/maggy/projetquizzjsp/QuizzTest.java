package com.maggy.projetquizzjsp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maggy.projetquizzjsp.dao.QuizzDAO;
import org.maggy.projetquizzjsp.model.Quizz;
import org.maggy.projetquizzjsp.service.DataSeedManager;
import org.maggy.projetquizzjsp.service.DatabaseManager;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QuizzTest {
    QuizzDAO qd;
    DatabaseManager dm;

    @BeforeEach
    public void init() throws SQLException {
        qd = QuizzDAO.getInstance();
        dm = DatabaseManager.getInstance();
        dm.init();

        qd.destroySQLTable();
        qd.buildSQLTable();
    }

    @Test
    public void testInsertion() throws SQLException {
        Quizz q = new Quizz();
        q.setQuestion("3+3 ?");
        q.setReponse1("3");
        q.setReponse2("6");
        q.setReponse3("9");
        q.setReponse4("10");
        q.setAnswer(2);

        qd.add(q);
        assertEquals(q.getId(), 1);

        ArrayList<Quizz> quizz_list = qd.getAll();
        assertEquals(1, quizz_list.size());
    }

    @Test
    public void quizzResultTest() throws SQLException {
        DataSeedManager.getInstance().sow();

        HashMap<String, String[]> rm = new HashMap<>();
        rm.put("quizz-1", new String[]{"2"});
        rm.put("quizz-2", new String[]{"2"});
        rm.put("quizz-3", new String[]{"3"});

        HashMap val = qd.evaluate(rm);
        assertTrue((Boolean) val.get("quizz-1"));
        assertFalse((Boolean) val.get("quizz-2"));
        assertTrue((Boolean) val.get("quizz-3"));
    }

    @Test
    public void testDelete() throws SQLException {
        Quizz q = new Quizz();
        q.setQuestion("3+3 ?");
        q.setReponse1("3");
        q.setReponse2("6");
        q.setReponse3("9");
        q.setReponse4("10");
        q.setAnswer(2);

        qd.add(q);
        assertEquals(q.getId(), 1);

        ArrayList<Quizz> quizz_list = qd.getAll();
        assertEquals(1, quizz_list.size());

        qd.delete(q);
        quizz_list = qd.getAll();
        assertEquals(0, quizz_list.size());
    }

    @Test
    public void testRecherche() throws SQLException {
        Quizz q = new Quizz();
        q.setQuestion("3+3 ?");
        q.setReponse1("3");
        q.setReponse2("6");
        q.setReponse3("9");
        q.setReponse4("10");
        q.setAnswer(2);

        qd.add(q);

        Quizz p = qd.get(1);
        assertEquals(q.getQuestion(), p.getQuestion());
    }

    @Test
    public void testUpdate() throws SQLException {
        Quizz q = new Quizz();
        q.setQuestion("3+3 ?");
        q.setReponse1("3");
        q.setReponse2("6");
        q.setReponse3("9");
        q.setReponse4("10");
        q.setAnswer(2);

        qd.add(q);
        q.setQuestion("3+3 = ?");

        qd.update(q);
        Quizz p = qd.get(q.getId());

        assertEquals("3+3 = ?", q.getQuestion());
    }
}
