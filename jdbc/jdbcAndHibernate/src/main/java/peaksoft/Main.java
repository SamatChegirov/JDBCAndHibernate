package peaksoft;

import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();


        userService.createUsersTable();
        userService.dropUsersTable();
        userService.saveUser("Marat", "Mataev", (byte) 30);
        userService.getAllUsers();
        userService.removeUserById(1L);
        userService.cleanUsersTable();


    }
}
