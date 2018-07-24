package pl.coderslab.model;

import pl.coderslab.DbUtil;

import java.sql.*;
import java.util.ArrayList;

public class Exercise {
    private int id;
    private String title;
    private String description;

    private static final String SAVE_TO_DB = "INSERT INTO Exercise(title, description) VALUE (?, ?)";
    private static final String UPDATE = "UPDATE Exercise SET title = ?, description = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Exercise WHERE id = ?";
    private static final String LOAD_EXERCISE_BY_ID = "SELECT * FROM Exercise WHERE id = ?";
    private static final String LOAD_ALL_EXERCISE = "SELECT * FROM Exercise";

    /* Setters & Getters*/
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* Constructors */

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Exercise() {

    }

    /* Methods */

    public void saveToDB() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            if (this.id == 0) {
                String generatedColumns[] = {"ID"};
                PreparedStatement preparedStatement;
                preparedStatement = conn.prepareStatement(SAVE_TO_DB, generatedColumns);
                preparedStatement.setString(1, this.title);
                preparedStatement.setString(2, this.description);

                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()) {
                    this.id = resultSet.getInt(1);
                }
            } else {
                PreparedStatement preparedStatement;
                preparedStatement = conn.prepareStatement(UPDATE);
                preparedStatement.setString(1, this.title);
                preparedStatement.setString(2, this.description);
                preparedStatement.setInt(3, this.id);

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

    static public Exercise loadById(int id) throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(LOAD_EXERCISE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Exercise loadedExercise = new Exercise();
                loadedExercise.id = resultSet.getInt("id");
                loadedExercise.title = resultSet.getString("title");
                loadedExercise.description = resultSet.getString("description");

                return loadedExercise;
            }
            return null;
        }
    }

    static public Exercise[] loadAll() throws SQLException {
        try (Connection conn = DbUtil.getConn()) {
            ArrayList<Exercise> exercises = new ArrayList<>();
            Statement statement;
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(LOAD_ALL_EXERCISE);

            while (resultSet.next()) {
                Exercise loadedExercise = new Exercise();
                loadedExercise.id = resultSet.getInt("id");
                loadedExercise.title = resultSet.getString("title");
                loadedExercise.description = resultSet.getString("description");

                exercises.add(loadedExercise);
            }
            Exercise[] eArray = new Exercise[exercises.size()];
            eArray = exercises.toArray(eArray);
            return eArray;
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " Title: " + title + " Description: " + description;
    }


}
