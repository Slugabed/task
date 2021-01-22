package task.mapper;

import org.junit.Assert;
import org.junit.Test;
import task.model.User;
import task.mapper.UserMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MapperTest {
    @Test
    public void testFromStream() throws IOException {
        List<User> expected = new ArrayList<>();
        expected.add(new User("user1", new HashSet<String>(){{
            add("xxx@ya.ru"); add("foo@gmail.com"); add("lol@mail.ru");
        }}));
        expected.add(new User("user2", new HashSet<String>(){{
            add("foo@gmail.com"); add("ups@pisem.net");
        }}));
        expected.add(new User("user3", new HashSet<String>(){{
            add("xyz@pisem.net"); add("vasya@pupkin.com");
        }}));
        expected.add(new User("user4", new HashSet<String>(){{
            add("ups@pisem.net"); add("aaa@bbb.ru");
        }}));
        expected.add(new User("user5", new HashSet<String>(){{
            add("xyz@pisem.net");
        }}));
        String inputString = "user1 -> xxx@ya.ru, foo@gmail.com, lol@mail.ru\n" +
                "user2 -> foo@gmail.com, ups@pisem.net\n" +
                "user3 -> xyz@pisem.net, vasya@pupkin.com\n" +
                "user4 -> ups@pisem.net, aaa@bbb.ru\n" +
                "user5 -> xyz@pisem.net\n" +
                "\n";
        InputStream targetStream = new ByteArrayInputStream(inputString.getBytes());
        List<User> result = UserMapper.fromStream(targetStream);
        Assert.assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void testRepeatElimination() throws IOException {
        List<User> expected = new ArrayList<>();
        expected.add(new User("user5", new HashSet<String>(){{
            add("xyz@pisem.net"); add("aaa@bbb.ru");
        }}));
        String inputString =
                "user5 -> xyz@pisem.net, xyz@pisem.net, aaa@bbb.ru, xyz@pisem.net, xyz@pisem.net\n" +
                "\n";
        InputStream targetStream = new ByteArrayInputStream(inputString.getBytes());
        List<User> result = UserMapper.fromStream(targetStream);
        Assert.assertEquals(expected.toString(), result.toString());
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidFormat() throws IOException {
        String inputString =
                "user5 xyz@pisem.net, xyz@pisem.net, aaa@bbb.ru, xyz@pisem.net, xyz@pisem.net\n" +
                        "\n";
        InputStream targetStream = new ByteArrayInputStream(inputString.getBytes());
        UserMapper.fromStream(targetStream);
    }
}
