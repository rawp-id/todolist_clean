package view;

import model.TodoModel;
import util.StackList;

public class TodoView {
//    public void displayTodoDetail(T id, U todo, T user_id) {
//        System.out.println(
//                "todo{" +
//                        "id=" + id +
//                        ", todo=" + todo +
//                        ", use_id=" + user_id +
//                        '}'
//        );
//    }
    public void todo(StackList<TodoModel> todoList) {
        for (TodoModel todo : todoList) {
            System.out.println("ID : " + todo.getId());
            System.out.println("Task : " + todo.getTodo() + "\n");
        }
    }
}
