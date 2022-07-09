package org.maggy.projetquizzjsp.dao;

import org.maggy.projetquizzjsp.model.Quizz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QuizzDAO extends BaseDAO{
    private static QuizzDAO instance = null;
    public static QuizzDAO getInstance(){
        if(instance == null)
            instance = new QuizzDAO();
        return instance;
    }
    public void tearDown(){
        instance = null;
    }


    @Override
    public void buildSQLTable() throws SQLException {
        String sql = "" +
                "CREATE TABLE \"quizz\" (\n" +
                "                        quizz_id serial PRIMARY KEY,\n" +
                "                        quizz_question varchar(255),\n" +
                "                        quizz_reponse1 varchar(255),\n" +
                "                        quizz_reponse2 varchar(255),\n" +
                "                        quizz_reponse3 varchar(255),\n" +
                "                        quizz_reponse4 varchar(255),\n" +
                "                        quizz_reponse integer" +
                ");";
        Statement stmt = getConnection().createStatement();
        stmt.execute(sql);
    }

    @Override
    public void destroySQLTable() throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("DROP TABLE IF EXISTS \"quizz\";\n");
    }

    @Override
    public Quizz getFromResultSet(ResultSet rs) throws SQLException {
        Quizz u = new Quizz();
        u.setId(rs.getInt("quizz_id"));
        u.setQuestion(rs.getString("quizz_question"));
        u.setReponse1(rs.getString("quizz_reponse1"));
        u.setReponse2(rs.getString("quizz_reponse2"));
        u.setReponse3(rs.getString("quizz_reponse3"));
        u.setReponse4(rs.getString("quizz_reponse4"));
        u.setAnswer(rs.getInt("quizz_reponse"));
        return u;
    }

    @Override
    public void add(Object b) throws SQLException {
        Quizz u = (Quizz) b;
        String sql = "INSERT INTO \"quizz\" (quizz_question, quizz_reponse1, quizz_reponse2, quizz_reponse3, quizz_reponse4, quizz_reponse) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, u.getQuestion());
        stmt.setString(2, u.getReponse1());
        stmt.setString(3, u.getReponse2());
        stmt.setString(4, u.getReponse3());
        stmt.setString(5, u.getReponse4());
        stmt.setInt(6, u.getAnswer());
        stmt.execute();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        generatedKeys.next();
        int auto_id = generatedKeys.getInt(1);
        u.setId(auto_id);
    }

    @Override
    public ArrayList getAll() throws SQLException {
        PreparedStatement stmt = null;
        ArrayList<Quizz> output = new ArrayList<>();
        stmt = getConnection().prepareStatement("SELECT * from \"quizz\"");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            output.add(getFromResultSet(rs));
        }
        return output;
    }
}
