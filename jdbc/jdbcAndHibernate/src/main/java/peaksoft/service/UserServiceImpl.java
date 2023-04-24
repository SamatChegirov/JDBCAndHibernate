package peaksoft.service;

import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import javax.sound.midi.Soundbank;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJdbcImpl userDaoImpl = new UserDaoJdbcImpl();

    public void createUsersTable() {
        userDaoImpl.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoImpl.cleanUsersTable();
    }
}
