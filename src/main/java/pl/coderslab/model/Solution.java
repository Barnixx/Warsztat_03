package pl.coderslab.model;


import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.ArrayList;

public class Solution {

    private int id;
    private Timestamp created;
    private Timestamp updated;
    private String description;
    private Integer exercise_id;
    private Integer users_id;

    /* QUERY */

    private static final String SAVE_TO_DB = "INSERT INTO Solution(created, updated, description, exercise_id, users_id) " +
            "VALUE (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE Solution SET created = ?, updated = ?, description = ?, exercise_id = ?, users_id = ?  WHERE id = ?";
    private static final String DELETE = "DELETE FROM Solution WHERE id = ?";
    private static final String LOAD_BY_ID = "SELECT * FROM Solution WHERE id = ?";
    private static final String LOAD_ALL = "SELECT * FROM Solution";
    private static final String LOAD_ALL_BY_USER_ID = "SELECT * FROM Solution WHERE users_id = ?";
    private static final String LOAD_ALL_BY_EXERCISE_ID = "SELECT * FROM Solution WHERE exercise_id = ? ORDER BY created";
    private static final String LOAD_ALL_WITH_LIMIT = "SELECT * FROM solution ORDER BY updated DESC LIMIT ?";

    /* Constructors */

    public Solution() {
    }

    public Solution(String created, String updated, String description, int exercise_id, int users_id) {
        this.created = Timestamp.valueOf(created);
        this.updated = Timestamp.valueOf(updated);
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    /* Getter & Setter */

    public int getId() {
        return id;
    }

    public String getCreated() {
        return String.valueOf(created);
    }

    public void setCreated(String created) {
        this.created = Timestamp.valueOf(created);
    }

    public String getUpdated() {
        return String.valueOf(updated);
    }

    public void setUpdated(String updated) {
        this.updated = Timestamp.valueOf(updated);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(Integer exercise_id) {
        this.exercise_id = exercise_id;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Integer users_id) {
        this.users_id = users_id;
    }

    /* Methods */


    public void saveToDB() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            if (this.id == 0) {
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = conn.prepareStatement(SAVE_TO_DB, generatedColumns);
                preparedStatement.setTimestamp(1, this.created);
                preparedStatement.setTimestamp(2, this.updated);
                preparedStatement.setString(3, description);
                if (this.exercise_id == null) {
                    preparedStatement.setNull(4, Types.INTEGER);
                } else {
                    preparedStatement.setInt(4, this.exercise_id);
                }
                if (this.users_id == null) {
                    preparedStatement.setNull(5, Types.INTEGER);
                } else {
                    preparedStatement.setInt(5, this.users_id);
                }

                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    this.id = resultSet.getInt(1);
                }
            } else {
                PreparedStatement preparedStatement;
                preparedStatement = conn.prepareStatement(UPDATE);
                preparedStatement.setTimestamp(1, this.created);
                preparedStatement.setTimestamp(2, this.updated);
                preparedStatement.setString(3, this.description);
                if (this.exercise_id == null) {
                    preparedStatement.setNull(4, Types.INTEGER);
                } else {
                    preparedStatement.setInt(4, this.exercise_id);
                }
                if (this.users_id == null) {
                    preparedStatement.setNull(5, Types.INTEGER);
                } else {
                    preparedStatement.setInt(5, this.users_id);
                }
                preparedStatement.setInt(6, this.id);
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

    public static Solution loadById(int id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(LOAD_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.id = resultSet.getInt("id");
                loadedSolution.created = resultSet.getTimestamp("created");
                loadedSolution.updated = resultSet.getTimestamp("updated");
                loadedSolution.description = resultSet.getString("description");
                int exercise_id = resultSet.getInt("exercise_id");
                if (exercise_id == 0) {
                    loadedSolution.exercise_id = null;
                } else {
                    loadedSolution.exercise_id = exercise_id;
                }
                int users_id = resultSet.getInt("users_id");
                if (users_id == 0) {
                    loadedSolution.users_id = null;
                } else {
                    loadedSolution.users_id = users_id;
                }
                return loadedSolution;
            }
            return null;
        }
    }

    public static Solution[] loadAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            ArrayList<Solution> solutions = new ArrayList<>();
            Statement statement;
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(LOAD_ALL);
            while (resultSet.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.id = resultSet.getInt("id");
                loadedSolution.created = resultSet.getTimestamp("created");
                loadedSolution.updated = resultSet.getTimestamp("updated");
                loadedSolution.description = resultSet.getString("description");
                int exercise_id = resultSet.getInt("exercise_id");
                if (exercise_id == 0) {
                    loadedSolution.exercise_id = null;
                } else {
                    loadedSolution.exercise_id = exercise_id;
                }
                int users_id = resultSet.getInt("users_id");
                if (users_id == 0) {
                    loadedSolution.users_id = null;
                } else {
                    loadedSolution.users_id = users_id;
                }
                solutions.add(loadedSolution);
            }
            Solution[] sArray = new Solution[solutions.size()];
            sArray = solutions.toArray(sArray);
            return sArray;
        }
    }

    public static Solution[] loadAll(int limit) throws SQLException {

        try (Connection conn = DbUtil.getConn()) {
            ArrayList<Solution> solutions = new ArrayList<>();
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(LOAD_ALL_WITH_LIMIT);
            preparedStatement.setInt(1, limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.id = resultSet.getInt("id");
                loadedSolution.created = resultSet.getTimestamp("created");
                loadedSolution.updated = resultSet.getTimestamp("updated");
                loadedSolution.description = resultSet.getString("description");
                int exercise_id = resultSet.getInt("exercise_id");
                if (exercise_id == 0) {
                    loadedSolution.exercise_id = null;
                } else {
                    loadedSolution.exercise_id = exercise_id;
                }
                int users_id = resultSet.getInt("users_id");
                if (users_id == 0) {
                    loadedSolution.users_id = null;
                } else {
                    loadedSolution.users_id = users_id;
                }
                solutions.add(loadedSolution);
            }
            Solution[] sArray = new Solution[solutions.size()];
            sArray = solutions.toArray(sArray);
            return sArray;
        }
    }

    public static Solution[] loadAllByUserId(int user_id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            ArrayList<Solution> usersSolutions = new ArrayList<>();
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(LOAD_ALL_BY_USER_ID);
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution loadedUserSolution = new Solution();
                loadedUserSolution.id = resultSet.getInt("id");
                loadedUserSolution.created = resultSet.getTimestamp("created");
                loadedUserSolution.updated = resultSet.getTimestamp("updated");
                loadedUserSolution.description = resultSet.getString("description");
                int exercise_id = resultSet.getInt("exercise_id");
                if (exercise_id == 0) {
                    loadedUserSolution.exercise_id = null;
                } else {
                    loadedUserSolution.exercise_id = exercise_id;
                }
                int users_id = resultSet.getInt("users_id");
                if (users_id == 0) {
                    loadedUserSolution.users_id = null;
                } else {
                    loadedUserSolution.users_id = users_id;
                }
                usersSolutions.add(loadedUserSolution);
            }
            Solution[] sArray = new Solution[usersSolutions.size()];
            sArray = usersSolutions.toArray(sArray);
            return sArray;
        }
    }

    public static Solution[] loadAllByExerciseId(int exerciseId) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            ArrayList<Solution> exerciseSolutions = new ArrayList<>();
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(LOAD_ALL_BY_EXERCISE_ID);
            preparedStatement.setInt(1, exerciseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution loadedSolution = new Solution();
                loadedSolution.id = resultSet.getInt("id");
                loadedSolution.created = resultSet.getTimestamp("created");
                loadedSolution.updated = resultSet.getTimestamp("updated");
                loadedSolution.description = resultSet.getString("description");
                int exercise_id = resultSet.getInt("exercise_id");
                if (exercise_id == 0) {
                    loadedSolution.exercise_id = null;
                } else {
                    loadedSolution.exercise_id = exercise_id;
                }
                int users_id = resultSet.getInt("users_id");
                if (users_id == 0) {
                    loadedSolution.users_id = null;
                } else {
                    loadedSolution.users_id = users_id;
                }
                exerciseSolutions.add(loadedSolution);
            }
            Solution[] sArray = new Solution[exerciseSolutions.size()];
            sArray = exerciseSolutions.toArray(sArray);
            return sArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("ID: ")
                .append(id)
                .append(" | Created: ")
                .append(created.toString())
                .append(" | updated: ")
                .append(updated)
                .append(" Description: ")
                .append(description)
                .append(" | Exercise ID: ")
                .append(exercise_id)
                .append(" | User ID: ")
                .append(users_id)
                .toString();
    }
}
