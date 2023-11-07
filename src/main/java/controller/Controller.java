package controller;

import model.TodoModel;
import model.UserModel;
import service.DbService;
import util.StackList;
import util.Validation;
import view.AdminView;
import view.TodoView;
import view.UserView;

import java.util.Scanner;

public class Controller {
    public static UserView viewUser = new UserView();
    public static AdminView viewAdmin = new AdminView();
    public static DbService service = new DbService();
    public static TodoView viewTodo = new TodoView();
    public static Validation validation = new Validation();
    public static Scanner scanner = new Scanner(System.in);
    public static void home() {
        System.out.println("\n----------To Do List App----------");
        System.out.println("1. login");
        System.out.println("2. Registrasi");
        System.out.println("3. Exit");
        System.out.print("Pilihan : ");
        String pilih = scanner.nextLine();
        try {
            if (validation.msg("Is it already correct? y/n")) {
                int option = Integer.parseInt(pilih);
                switch (option) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        System.out.println("Exit.....");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                        home();
                        break;

                }
            } else {
                home();
            }
        } catch (NumberFormatException e) {
            System.out.println("Bukan angka. Tolong input angka.");
            home();
        }
    }
    public static void roleMenu(Integer role) {
        if (role == 1) {
            System.out.println("\nHello " + service.user.getName() + "!");
            menuAdmin();
        }else if(role==2){
            System.out.println("\nHello " + service.user.getName() + "!");
            menuUser();
        }
    }
    public static void login() {
        System.out.println("\nLOGIN");
        System.out.print("Email : ");
        String user = scanner.nextLine();
        String pass = validation.inputPassword();
        if (service.authenticateLoginService(user, pass)) {
            if (service.user.isStatus()) {
                roleMenu((Integer) service.user.getRole_id());
            }else {
                System.out.println("Akun tidak aktif");
                home();
            }
        } else {
            System.out.println("Gagal Login");
            home();
        }
    }

    public static void register(){
        System.out.println("\nREGISTRASI");
        System.out.print("Nama : ");
        String nama = scanner.nextLine();
        String email = validation.emailValidation();
        String pass = validation.inputPassword();
        if (service.registerUserService(nama,email,pass)){
            System.out.println("Registrasi berhasil");
            home();
        }else {
            System.out.println("Registrasi gagal");
        }
    }
    //todo
    public static void displayTodo(Integer id) {
        StackList<TodoModel> todoList = service.getUserTodo(id);
        if (todoList == null) {
            System.out.println("data kosong");
        }
        viewTodo.todo(todoList);
    }

    //user
    public static void menuUser() {
        System.out.println("\n----------To Do List App----------");
        System.out.println(viewUser.menu());
        System.out.print("Pilihan : ");
        String choice = scanner.nextLine();
        try {
            if (validation.msg("Is it already correct? y/n")) {
                int option = Integer.parseInt(choice);
                choiceUser(option);
            } else {
                menuUser();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            menuUser();
        }
    }

    public static void choiceUser(Integer choices) {
        switch (choices) {
            case 1:
                System.out.println("\n---To Do List---");
                displayTodo((Integer) service.user.getId());
                menuUser();
            case 2:
                System.out.println("\n---Input To Do List---");
                System.out.print("Task : ");
                String task = scanner.nextLine();
                System.out.println(service.addTodoService(task, (Integer) service.user.getId()));
                menuUser();
            case 3:
                System.out.println("\n---Delete To Do List---");
                System.out.println("To Do List : ");
                displayTodo((Integer) service.user.getId());
                System.out.print("Pilih ID : ");
                int id = scanner.nextInt();
                displayIdUser(id);
                if (validation.msg("Is it already correct? y/n")) {
                    System.out.println(service.deleteTodoService(id));
                }else {
                    choiceUser(3);
                }
                menuUser();
            case 4:
                System.out.println(viewUser.profile((Integer) service.user.getId(), (String) service.user.getName(), (String) service.user.getEmail(), (String) service.user.getPassword()));
                menuUser();
            case 5:
                System.out.println("Logout.......");
                Controller.home();
            default:
                System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                menuUser();
        }
    }

    public static void displayAllUser() {
        StackList<UserModel> userList = service.getAllUserService();
        if (userList == null) {
            System.out.println("data kosong");
        }
        viewUser.AllUsers(userList);
    }

    public static void displayIdUser(Integer id) {
        StackList<UserModel> userList = service.getIdServiceService(id);
        if (userList == null) {
            System.out.println("data kosong");
        }
        viewUser.AllUsers(userList);
    }

    public void detailUserDetail() {
        System.out.println("id :" + service.user.getId());
        System.out.println("nama :" + service.user.getName());
        System.out.println("email :" + service.user.getEmail());
    }

    //admin
    public static void menuAdmin() {
        System.out.println("\n----------To Do List App----------");
        System.out.println(viewAdmin.menu());
        System.out.print("Pilihan : ");
        String choice = scanner.nextLine();
        try {
            if (validation.msg("Is it already correct? y/n")) {
                int option = Integer.parseInt(choice);
                choiceAdmin(option);
            } else {
                menuAdmin();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            menuAdmin();
        }
    }

    public static void choiceAdmin(Integer choices) {
        switch (choices) {
            case 1:
                System.out.println("\n---Input Data Admin---");
                System.out.print("Nama : ");
                String nama = scanner.nextLine();
                System.out.print("Email : ");
                String email = scanner.nextLine();
                String password = validation.inputPassword();
                if(validation.msg("Apakah data sudah benar? (y/n)")) {
                    System.out.println(service.addAdminService(nama, email, password));
                }else{
                    choiceAdmin(1);
                }
                menuAdmin();
            case 2:
                System.out.println("\n---Data User---");
                displayAllUser();
                menuAdmin();
            case 3:
                System.out.println("\n---User Status---");
                System.out.println("Data User :");
                displayAllUser();
                System.out.print("Pilih ID : ");
                int id = scanner.nextInt();
                displayIdUser(id);
                System.out.print("Status (true/false) : ");
                boolean status = scanner.nextBoolean();
                if (validation.msg("Apakah data sudah benar? (y/n)")) {
                    System.out.println(service.statusUserService(id, status));
                }else {
                    choiceAdmin(3);
                }
                menuAdmin();
            case 4:
                System.out.println(viewAdmin.profile((Integer) service.user.getId(), (String) service.user.getName(), (String) service.user.getEmail(), (String) service.user.getPassword(), service.user.isStatus(), (Integer) service.user.getRole_id()));
                menuAdmin();
            case 5:
                System.out.println("Logout.......");
                Controller.home();
            default:
                System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                menuAdmin();
        }
    }

    public void detailUserDetailAdmin() {
        System.out.println("id : " + service.user.getId());
        System.out.println("nama : " + service.user.getName());
        System.out.println("email : " + service.user.getEmail());
        System.out.println("password : " + service.user.getPassword());
        System.out.println("status : " + service.user.getPassword());
    }
}
