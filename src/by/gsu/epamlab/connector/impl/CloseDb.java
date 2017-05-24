package by.gsu.epamlab.connector.impl;

import by.gsu.epamlab.connector.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CloseDb implements AutoCloseable {
    protected static DbConnector connector;

    protected Connection connection;
    protected Statement statement;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    protected CloseDb() {
    }

    @Override
    public void close() throws Exception {
        connector = null;
        if (resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }
        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
