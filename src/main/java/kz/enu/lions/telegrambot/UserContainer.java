package kz.enu.lions.telegrambot;

import java.util.ArrayList;
import java.util.List;

public class UserContainer {
    public List<User> users;
    public DaoObject daoObject;

    UserContainer(DaoObject daoObject) {
        users = new ArrayList<>();
        this.daoObject = daoObject;
        users = this.daoObject.getAllUsers();
    }


    public boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        User user = new User();
        user.setUsername("NOUSERSCECIFIED");
        return user;
    }

    public void addUserToDB(String username) {
        User user = getUser(username);
        daoObject.addNewUser(user);
        user.setLocation(Location.MAIN);
    }
}
