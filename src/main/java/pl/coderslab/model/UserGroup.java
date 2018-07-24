package pl.coderslab.model;

import pl.coderslab.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserGroup {

    private int id;
    private String name;

    /* Query */
    private static final String LOAD_BY_ID = "SELECT * FROM Users_Groups WHERE id = ?";
    private static final String LOAD_ALL = "SELECT * FROM Users_Groups";
    private static final String DELETE = "DELETE FROM Users_Groups WHERE id = ?";
    private static final String SAVE = "INSERT INTO Users_Groups(name) VALUES (?)";
    private static final String UPDATE = "UPDATE Users_Groups SET name = ? WHERE id = ?";

    /* Constructors */

    public UserGroup() {
    }

    public UserGroup(String name) {
        this.name = name;
    }

    /* Setter & Getter */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    /* Methods */

    public static UserGroup loadById(int id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement preparedStatement = conn.prepareStatement(LOAD_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserGroup loadedGroup = new UserGroup();
                loadedGroup.id = resultSet.getInt("id");
                loadedGroup.name = resultSet.getString("name");
                return loadedGroup;
            }
            return null;
        }
    }

    public static UserGroup[] loadAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            List<UserGroup> groups = new ArrayList<UserGroup>();
            PreparedStatement preparedStatement = conn.prepareStatement(LOAD_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserGroup loadedGroup = new UserGroup();
                loadedGroup.id = resultSet.getInt("id");
                loadedGroup.name = resultSet.getString("name");
                groups.add(loadedGroup);
            }
            UserGroup[] gArray = new UserGroup[groups.size()];
            gArray = groups.toArray(gArray);
            return gArray;
        }
    }

    public void delete() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            if (this.id != 0) {
                PreparedStatement preparedStatement = conn.prepareStatement(DELETE);
                preparedStatement.setInt(1, this.id);
                preparedStatement.executeUpdate();
                this.id = 0;
            }
        }
    }

    public void saveToDB() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            if (this.id == 0) {
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement = conn.prepareStatement(SAVE, generatedColumns);
                preparedStatement.setString(1, this.name);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
            } else {
                PreparedStatement preparedStatement = conn.prepareStatement(UPDATE);
                preparedStatement.setString(1, this.name);
                preparedStatement.setInt(2, this.id);
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", name: " + name;
    }
}