package src.database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class UserAuthentication {
    private static String currentUser = null;
    public static String getCurrentUser() {
        return currentUser;
    }
    public static void logOut() {
        currentUser = null;
    }
    public static boolean userRegistration(String login, String password) {
        try {
            try {
                ResultSet resultSet = DatabaseConnection.executePreparedStatement("SELECT * FROM USERS WHERE login = ?", login);
                if (resultSet.next()) {
                    return false;
                } else {
                    resultSet.close();
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-1");
                    String salt = saltGetter();
                    byte[] hash = md.digest(("*63&^mVLC(#" + password + salt).getBytes(StandardCharsets.UTF_8));
                    DatabaseConnection.executePreparedStatement("INSERT INTO USERS (login, hash, salt) VALUES (?, ?, ?)", login, Arrays.toString(hash), salt).close();
                } catch (NoSuchAlgorithmException e1) {
                    System.out.println(e1.getMessage());
                } catch (IllegalArgumentException e1) {
                    return true;
                }
                return false;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean userLoggingIn(String login, String  password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            ResultSet resultSetSalt = DatabaseConnection.executePreparedStatement("SELECT salt FROM USERS WHERE login = ?", login);
            resultSetSalt.next();
            ResultSet resultSetHash = DatabaseConnection.executePreparedStatement("SELECT hash FROM USERS WHERE login = ?", login);
            resultSetHash.next();
            byte[] hash = md.digest(("*63&^mVLC(#" + password + resultSetSalt.getString(1)).getBytes(StandardCharsets.UTF_8));
            resultSetSalt.close();
            if (Arrays.toString(hash).equals(resultSetHash.getString(1))) {
                currentUser = login;
                resultSetHash.close();
                return true;
            } else {
                resultSetHash.close();
                return false;
            }
        } catch (SQLException | NoSuchAlgorithmException | NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    private static String saltGetter() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append((char) new Random().nextInt(33, 126));
        }
        return stringBuilder.toString();
    }
}