package org.maggy.projetquizzjsp.dao;

import org.maggy.projetquizzjsp.model.Quizz;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void delete(Quizz q) throws SQLException {
        String sql = "DELETE FROM quizz WHERE quizz_id=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, q.getId());
        stmt.execute();
    }

    public Quizz get(int id) throws SQLException {
        String sql = "SELECT * FROM quizz WHERE quizz_id=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if(!rs.next()) {
            return null;
        }
        return getFromResultSet(rs);
    }

    public void update(Quizz q) throws SQLException {
        String sql = "UPDATE quizz SET quizz_question=?, quizz_reponse1=?, quizz_reponse2=?, quizz_reponse3=?," +
                " quizz_reponse4=?, quizz_reponse=? WHERE quizz_id=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, q.getQuestion());
        stmt.setString(2, q.getReponse1());
        stmt.setString(3, q.getReponse2());
        stmt.setString(4, q.getReponse3());
        stmt.setString(5, q.getReponse4());
        stmt.setInt(6, q.getAnswer());
        stmt.setInt(7, q.getId());
        stmt.execute();
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

    public HashMap evaluate(Map<String, String[]> rm) throws SQLException {
        HashMap<String, Boolean> output = new HashMap<String, Boolean>();
        ArrayList<Quizz> quizz_list = getAll();
        for(int i = 0; i < quizz_list.size(); i++){
            Quizz q = quizz_list.get(i);
            if(rm.containsKey("quizz-" + q.getId())){

                String rmValue = rm.get("quizz-" + q.getId())[0];
                String answer = ""+q.getAnswer();
                if(rmValue.equals(answer)){
                    output.put("quizz-" + q.getId(), true);
                }else{
                    output.put("quizz-" + q.getId(), false);
                }

            }
        }
        return output;
    }
}
