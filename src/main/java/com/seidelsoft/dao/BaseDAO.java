package com.seidelsoft.dao;

import com.seidelsoft.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class BaseDAO<T> {

    private Connection connection;
    private final String tableName;

    public BaseDAO(String tableName) {
        getConnection();
        this.tableName = tableName;
    }

    public abstract T getById(Long id);
    protected abstract List<T> getList();
    protected abstract List<T> prepareListOf(ResultSet result) throws SQLException;
    public abstract T save(T obj) throws SQLException;
    public abstract void delete(Long id) throws SQLException;

    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed())
                this.connection = DbConnection.getConnection();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    public ResultSet executeQueryForGet(PreparedStatement stmt) throws SQLException {
        ResultSet result = stmt.executeQuery();
        getConnection().close();
        return result;
    }

    public ResultSet verifyBeforeInsert(Map<String, String> columnsValues) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(existsQuery(columnsValues));
        return stmt.executeQuery();
    }

    public void executeInsert(Map<String, String> columnsValues) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(insertQuery(columnsValues));
        stmt.executeUpdate();
    }

    public void executeDelete(Long id) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(deleteById(id));
        stmt.executeUpdate();
    }

    public String selectById(Long id) {
        return "select * from " + getTableName() + " where id = " + id;
    }

    public String getListQuery() {
        return "select * from " + getTableName() + " order by id asc limit 50";
    }

    public String deleteById(Long id) {
        return "delete from " + getTableName() + " where id = " + id;
    }

    public String existsQuery(Map<String, String> columnsValues) {
        StringBuilder compares = new StringBuilder();
        String select = "select id from " + getTableName() + " where {compare}";
        boolean firstWhere = true;

        for (Map.Entry<String, String> entry : columnsValues.entrySet()) {
            String column = entry.getKey();
            String value = entry.getValue();

            if (firstWhere) {
                compares.append(column + " = '" + value + "'");
            } else {
                compares.append(" and " + column + " = '" + value + "'");
            }
            firstWhere = false;
        }

        select = select.replace("{compare}", compares);

        return select;
    }

    public String insertQuery(Map<String, String> columnsValues) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        String insert = "insert into " + getTableName() + "(id, {cols}) values ("+getNewIdSubQuery()+"{values})";

        for (Map.Entry<String, String> entry : columnsValues.entrySet()) {
            String column = entry.getKey();
            String value = entry.getValue();

            columns.append(", " + column);
            values.append(", '" + value + "'");
        }
        insert = insert.replace(", {cols}", columns);
        insert = insert.replace("{values}", values);

        return insert;
    }

    public String getMaxId() {
        return "select max(id) as id from " + getTableName();
    }

    public String getNewIdSubQuery() {
        return "(select max(id) + 1 from " + getTableName() + ")";
    }

    public String getTableName() {
        return tableName;
    }


    private void readDBConfigs() {}

}
