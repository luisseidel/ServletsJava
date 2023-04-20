package com.seidelsoft.util;

import com.seidelsoft.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbConnection {

    public Author getConnection() {
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = getConnection();
            statement = conn.prepareStatement("");
            statement.setLong();
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Author a = createAuthor(resultSet);
                resultSet.close();
                return a;
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

}
