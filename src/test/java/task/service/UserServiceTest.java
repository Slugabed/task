package task.service;

import org.junit.Assert;
import org.junit.Test;
import task.model.User;

import java.util.*;

public class UserServiceTest {
    private UserService service = new UserService();
    @Test
    public void testUnique(){
        List<User> users = new ArrayList<>();
        users.add(new User("user1", new HashSet<>(Arrays.asList("user1@email.ru", "user11@email.ru"))));
        users.add(new User("user2", new HashSet<>(Arrays.asList("user2@email.ru", "user22@email.ru"))));
        users.add(new User("user3", new HashSet<>(Arrays.asList("user3@email.ru", "user33@email.ru"))));
        users.add(new User("user_common", new HashSet<>(Arrays.asList("user11@email.ru",
                "user22@email.ru", "user33@email.ru", "common@email.ru"))));
        Set<User> result = service.makeUnique(users);
        Assert.assertEquals(1, result.size());
        Set<String> expectedEmails = new HashSet<>(Arrays.asList("user1@email.ru",
                "user2@email.ru", "user3@email.ru", "common@email.ru",
                "user11@email.ru", "user22@email.ru", "user33@email.ru"));
        User uniqueUser = result.iterator().next();
        Assert.assertEquals(expectedEmails,uniqueUser .getEmails());
        Assert.assertTrue(
                new HashSet<>(Arrays.asList("user1", "user2", "user3", "user_common"))
                .contains(uniqueUser.getName()));
    }
}
