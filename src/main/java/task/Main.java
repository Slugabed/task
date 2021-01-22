package task;

import task.mapper.UserMapper;
import task.model.User;
import task.service.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        try {
            List<User> inputUsers = UserMapper.fromStream(System.in);
            Set<User> uniqueUsers = userService.makeUnique(inputUsers);
            for(User user: uniqueUsers){
                System.out.println(user);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
