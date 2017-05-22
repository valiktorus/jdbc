import by.gsu.epamlab.NumLen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        String className = "org.gjt.mm.mysql.Driver";
        String dbUrl = "jdbc:mysql://localhost/segments";
        String user = "root";
        String password = "";
        try {
            Class.forName(className);
            Connection connection = null;
            Statement statement = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                List<NumLen> numLens = new ArrayList<>();
                connection = DriverManager.getConnection(dbUrl, user, password);
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT FLOAR(ABS(x2-x1) + 0.5) AS len, COUNT (*) AS num\n" +
                        "FROM coordinates GROUP BY len ORDER BY len;");
                while (resultSet.next()) {
                    NumLen numLen = new NumLen(resultSet.getInt(1), resultSet.getInt(2));
                    numLens.add(numLen);
                }
                statement.execute("TRUNCATE TABLE frequencies;");
                preparedStatement = connection.prepareStatement("INSERT INTO frequencies(len, num) VALUES (?,?);");
                for (NumLen numlen: numLens) {
                    preparedStatement.setInt(1, numlen.getLen());
                    preparedStatement.setInt(2, numlen.getNum());
                    preparedStatement.executeUpdate();
                }
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM frequencies WHERE len > num;");
                System.out.println("required values:");
                while (resultSet.next()){
                    System.out.println(resultSet.getInt(1) + ";" + resultSet.getInt(2));

                }
            } finally {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
