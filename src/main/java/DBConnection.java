import bookstore.entity.Users;

import java.sql.*;
public class DBConnection {
    public static void main(String[] args) {
        String host = "jdbc:mysql://localhost:3306/bookstoredb";
        String uName = "root";
        String uPass = "password";
        try {
            Connection connectionUsersWrite = DriverManager.getConnection(host, uName, uPass);
            String sqlUsersWrite = "INSERT INTO users (email, password, full_name) VALUES (?, ?, ?)";
            PreparedStatement statement = connectionUsersWrite.prepareStatement(sqlUsersWrite);
            Users user1 = new Users();
            user1.setEmail("Yurii@gmail.com");
            user1.setFullName("Yurii Panasiuk");
            user1.setPassword("password");
            statement.setString(1, user1.getEmail());
            statement.setString(2, user1.getPassword());
            statement.setString(3, user1.getFullName());
            statement.executeUpdate();
            System.out.println("User was persisted");
            connectionUsersWrite.close();
        }
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        try {
            Connection connectionUSersRead = DriverManager.getConnection(host, uName, uPass);
            String sqlUsersRead = "SELECT * FROM users";
            Statement statement = connectionUSersRead.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery(sqlUsersRead);
            while (resultSet.next()) {
                int user_ID = resultSet.getInt("user_id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                String row = user_ID + " " + email + " " + password + " " + fullName;
                System.out.println(row);
            }
            connectionUSersRead.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }
}
