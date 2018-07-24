package pl.coderslab.model;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private Integer userGroupId;

    /* QUERY */

    private static final String SAVE_TO_DB = "INSERT INTO Users(username, email, password, user_group_id) " +
            "VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Users SET username=?, email=?, password=?, " +
            "user_group_id=? where id = ?";
    private static final String DELETE = "DELETE FROM Users WHERE id= ?";
    private static final String LOAD_USER_BY_ID = "SELECT * FROM Users where id=?";
    private static final String LOAD_ALL_USER = "SELECT * FROM Users";
    private static final String LOAD_ALL_BY_GROUP_ID = "SELECT * FROM Users WHERE user_group_id = ?";

    /* Constructors */

    public User() {
    }

    public User(String username, String email, String password, int userGroupId) {
        this.username = username;
        this.email = email;
        this.setPassword(password);
        this.setUserGroupId(userGroupId);
    }

    /* Setters & Getters */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        if (userGroupId < 0) {
            this.userGroupId = userGroupId;
        }
    }

    /* Methods */

    public void saveToDB() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            if (this.id == 0) {
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = conn.prepareStatement(SAVE_TO_DB, generatedColumns);
                preparedStatement.setString(1, this.username);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                if (this.userGroupId == null) {
                    preparedStatement.setNull(4, java.sql.Types.INTEGER);
                } else {
                    preparedStatement.setInt(4, this.userGroupId);
                }

                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            } else {
                PreparedStatement preparedStatement;
                preparedStatement = conn.prepareStatement(UPDATE);
                preparedStatement.setString(1, this.username);
                preparedStatement.setString(2, this.email);
                preparedStatement.setString(3, this.password);
                if (this.userGroupId == null) {
                    preparedStatement.setNull(4, java.sql.Types.INTEGER);
                } else {
                    preparedStatement.setInt(4, this.userGroupId);
                }
                preparedStatement.setInt(5, this.id);
                preparedStatement.executeUpdate();
            }
        }
    }

    public void delete() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            if (this.id != 0) {
                PreparedStatement preparedStatement;
                preparedStatement = conn.prepareStatement(DELETE);
                preparedStatement.setInt(1, this.id);
                preparedStatement.executeUpdate();
                this.id = 0;
            }
        }
    }

    static public User loadById(int id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(LOAD_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User loadedUser = new User();
                loadedUser.id = resultSet.getInt("id");
                loadedUser.username = resultSet.getString("username");
                loadedUser.password = resultSet.getString("password");
                loadedUser.email = resultSet.getString("email");
                int userGroupId = resultSet.getInt("user_group_id");
                if (userGroupId == 0) {
                    loadedUser.userGroupId = null;
                } else {
                    loadedUser.userGroupId = userGroupId;
                }
                return loadedUser;
            }
            return null;
        }
    }

    static public User[] loadAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            ArrayList<User> users = new ArrayList<User>();
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(LOAD_ALL_USER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User loadedUser = new User();
                loadedUser.id = resultSet.getInt("id");
                loadedUser.username = resultSet.getString("username");
                loadedUser.password = resultSet.getString("password");
                loadedUser.email = resultSet.getString("email");
                int userGroupId = resultSet.getInt("user_group_id");
                if (userGroupId == 0) {
                    loadedUser.userGroupId = null;
                } else {
                    loadedUser.userGroupId = userGroupId;
                }
                users.add(loadedUser);
            }
            User[] uArray = new User[users.size()];
            uArray = users.toArray(uArray);
            return uArray;
        }
    }

    public static User[] loadAllByGroupId(int groupId) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            ArrayList<User> users = new ArrayList<User>();
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(LOAD_ALL_BY_GROUP_ID);
            preparedStatement.setInt(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User loadedUser = new User();
                loadedUser.id = resultSet.getInt("id");
                loadedUser.username = resultSet.getString("username");
                loadedUser.password = resultSet.getString("password");
                loadedUser.email = resultSet.getString("email");
                int userGroupId = resultSet.getInt("user_group_id");
                if (userGroupId == 0) {
                    loadedUser.userGroupId = null;
                } else {
                    loadedUser.userGroupId = userGroupId;
                }
                users.add(loadedUser);
            }
            User[] uArray = new User[users.size()];
            uArray = users.toArray(uArray);
            return uArray;
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " UserName: " + username + " Email: " + email + " Password: " + password;
    }

}