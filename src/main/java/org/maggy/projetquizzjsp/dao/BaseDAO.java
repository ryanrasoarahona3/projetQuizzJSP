package org.maggy.projetquizzjsp.dao;


import org.maggy.projetquizzjsp.service.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseDAO<Bean> {

    /**
     * Get Database connection
     * @return
     */
    protected Connection getConnection() throws SQLException {
        return DatabaseManager.getInstance().getConnection();
    }

    /**
     * Build SQL Table structure inside database
     * @throws SQLException
     */
    public abstract void buildSQLTable() throws SQLException;

    /**
     * Destroy table
     * @throws SQLException
     */
    public abstract void destroySQLTable() throws SQLException;

    /**
     * Add an entity
     * @param b The entity to append
     * @throws SQLException
     */
    public abstract void add(Bean b) throws SQLException;

    /**
     * Get List
     * @return The list retrieved from the database
     * @throws SQLException
     */
    public abstract ArrayList<Bean> getAll() throws SQLException;

    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    public abstract Bean getFromResultSet(ResultSet rs) throws SQLException;
}