package task.mapper;

import task.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserMapper {
    public static List<User> fromStream(InputStream is) throws IOException {
        Pattern namePattern = Pattern.compile("(.*) -> (.*)");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        List<User> result = new ArrayList<>();
        while(!(line = br.readLine()).isEmpty()){
            Matcher m = namePattern.matcher(line);
            if (m.matches()){
                String name = m.group(1);
                String remained = m.group(2);
                List<String> emails = Arrays.asList(remained.split(", "));
                result.add(new User(name, new HashSet<>(emails)));
            } else {
                throw new RuntimeException("Invalid input format");
            }
        }
        return result;
    }
}
