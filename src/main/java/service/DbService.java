package service;

import model.TodoModel;
import model.UserModel;
import repository.DbManager;
import util.StackList;

public class DbService extends DbManager {
    public boolean isTodoListEmpty(StackList<TodoModel> todoList) {
        return todoList == null || todoList.isEmpty();
    }
    public static boolean isAllUserEmpty(StackList<UserModel> todoList) {
        return todoList == null || todoList.isEmpty();
    }

    public StackList<TodoModel> getUserTodo(Integer id) {
        StackList<TodoModel> userTodoList = getTodo(id);
        if (isTodoListEmpty(userTodoList)) {
            return null;
        }
        return userTodoList;
    }

    public static StackList<UserModel> getAllUserService() {
        StackList<UserModel> userAllList = getAllUsers();
        if (isAllUserEmpty(userAllList)){
            return null;
        }
        return userAllList;
    }

    public static StackList<UserModel> getIdServiceService(Integer id) {
        StackList<UserModel> userAllList = getIdUsers(id);
        if (isAllUserEmpty(userAllList)){
            return null;
        }
        return userAllList;
    }

    public static String addAdminService(String nama, String email, String password) {
        if (addAdmin(nama, email, password)) {
            return"berhasil menambahkan admin";
        }
        return"gagal menamabahkan admin";
    }

//    public String registerUserService(String nama, String email, String password) {
//        if (registerUser(nama, email, password)) {
//            return"berhasil menambahkan admin";
//        }
//        return"gagal menambahkan admin";
//    }

    public static boolean registerUserService(String nama, String email, String password) {
        if (registerUser(nama, email, password)) {
            return true;
        }

        return false;
    }

    public static boolean emailAlredyValidation(String email){
//        if(isEmailAlreadyRegistered(email)){
//            return true;
//        }
//        return false;
        return isEmailAlreadyRegistered(email);
    }

    public static boolean authenticateLoginService(String username, String password) {
        if (authenticateLogin(username, password)) {
            return true;
        }
        return false;
    }

    public String statusUserService(Integer id, Boolean status) {
        if (statusUser(id, status)) {
            return"berhasil mengubah status";
        }
        return"gagal mengubah status";
    }

    public String addTodoService(String todo, Integer user_id){
        if(addTodo(todo, user_id)){
            return"berhasil menambahkan todo";
        }
        return"gagal menambahkan todo";
    }

    public String deleteTodoService(Integer user_id){
        if(deleteTodo(user_id)){
            return "berhasil menghapus todo";
        }
        return "gagal menghapus todo";
    }
}
