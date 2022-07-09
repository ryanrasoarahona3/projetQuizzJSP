package org.maggy.projetquizzjsp.dao;


import org.maggy.projetquizzjsp.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO extends BaseDAO {
    private static UserDAO instance = null;
    public static UserDAO getInstance(){
        if(instance == null)
            instance = new UserDAO();
        return instance;
    }
    public void tearDown(){
        instance = null;
    }

    @Override
    public void buildSQLTable() throws SQLException {
        String sql = "" +
                "CREATE TABLE \"user\" (\n" +
                "                        user_id serial PRIMARY KEY,\n" +
                "                        user_email varchar(255),\n" +
                "                        user_pseudo varchar(255),\n" +
                "                        user_password varchar(255),\n" +
                "                        user_privilege varchar(255)\n" +
                ");";
        Statement stmt = getConnection().createStatement();
        stmt.execute(sql);
    }

    @Override
    public void destroySQLTable() throws SQLException {
        Statement stmt = getConnection().createStatement();
        stmt.execute("DROP TABLE IF EXISTS \"user\";\n");
    }

    @Override
    public User getFromResultSet(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("user_id"));
        u.setEmail(rs.getString("user_email"));
        u.setPseudo(rs.getString("user_pseudo"));
        u.setPassword(rs.getString("user_password"));
        u.setPrivilege(rs.getString("user_privilege"));
        return u;
    }

    @Override
    public void add(Object b) throws SQLException {
        User u = (User) b;
        String sql = "INSERT INTO \"user\" (user_email, user_pseudo, user_password, user_privilege) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, u.getEmail());
        stmt.setString(2, u.getPseudo());
        stmt.setString(3, u.getPassword());
        stmt.setString(4, u.getPrivilege());
        stmt.execute();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        generatedKeys.next();
        int auto_id = generatedKeys.getInt(1);
        u.setId(auto_id);
    }

    @Override
    public ArrayList getAll() throws SQLException {
        PreparedStatement stmt = null;
        ArrayList<User> output = new ArrayList<>();
        stmt = getConnection().prepareStatement("SELECT * from \"user\"");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            output.add(getFromResultSet(rs));
        }
        return output;
    }
}
