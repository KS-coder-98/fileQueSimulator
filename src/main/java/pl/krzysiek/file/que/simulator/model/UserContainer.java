package pl.krzysiek.file.que.simulator.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Data
public class UserContainer {

    private List<User> users = new LinkedList<>();

    public void generateUsers(){
        Random random = new Random();
        int numberOfUsers = random.nextInt(1,8);
        for (int i = 0; i< numberOfUsers; i++){
            User user = new User();
            user.initUser();
            users.add(user);
        }
    }
}
