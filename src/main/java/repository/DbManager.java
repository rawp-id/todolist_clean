package repository;


import model.TodoModel;
import model.UserModel;
import util.StackList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbManager {
    public static UserModel user;
    public static TodoModel todo;
    public static UserModel listUser;

    protected static boolean addAdmin(String nama, String email, String password) {
        try {
            String sql = "INSERT INTO `user`(`name`, `email`, `password`, `status`, `role_id`) VALUES (?,?,?,'1','1')";

            PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean isEmailAlreadyRegistered(String email) {
        try {
            String sql = "SELECT COUNT(*) FROM `user` WHERE `email` = ?";
            PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("COUNT(*)");
                if(count>0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean registerUser(String nama, String email, String password) {
        try {
            String sql = "INSERT INTO `user`(`name`, `email`, `password`, `status`, `role_id`) VALUES (?,?,?,'0','2')";

            PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean authenticateLogin(String username, String password) {
        try {
            String sql = "SELECT * FROM `user` WHERE email = ? AND password = ?";

            PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                user = new UserModel<Integer, String>(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getBoolean("status"), result.getInt("role_id"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean statusUser(Integer id, Boolean status) {
        try {
            String sql = "UPDATE `user` SET `status`= ? WHERE id = ?";

            PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setBoolean(1, status);
            preparedStatement.setInt(2, id);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean addTodo(String todo, Integer user_id) {
        try {
            String sql = "INSERT INTO `todo`(`todo`, `user_id`) VALUES (?,?)";

            PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, todo);
            preparedStatement.setInt(2, user_id);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean deleteTodo(Integer id) {
        try {
            String sql = "DELETE FROM `todo` WHERE id = ?";

            PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setInt(2, id);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static StackList<TodoModel> getTodo(Integer id) {
        StackList<TodoModel> todoList = new StackList<>();
        try {
            String sql = "SELECT * FROM `todo` WHERE user_id = ?";

            PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                todo = new TodoModel<Integer, String>(result.getInt("id"), result.getString("todo"), result.getInt("user_id"));
                todoList.insertLast(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todoList;
    }

    protected static StackList<UserModel> getAllUsers() {
        StackList<UserModel> userList = new StackList<>();
        try {
            String sql = "SELECT * FROM `user` WHERE role_id = 2";

            PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(sql);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                listUser = new UserModel<Integer, String>(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getBoolean("status"), result.getInt("role_id"));
                userList.insertLast(listUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    protected static StackList<UserModel> getIdUsers(Integer id) {
        StackList<UserModel> userList = new StackList<>();
        try {
            String sql = "SELECT * FROM `user` WHERE id = ?";

            PreparedStatement preparedStatement
                    = DbConnection.getConnection().prepareStatement(sql);

            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                listUser = new UserModel<Integer, String>(result.getInt("id"), result.getString("name"), result.getString("email"), result.getString("password"), result.getBoolean("status"), result.getInt("role_id"));
                userList.insertLast(listUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
