package task.model;

import java.util.Set;

public class User {
    String name;
    Set<String> emails;

    public User(String name, Set<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public Set<String> getEmails() {
        return emails;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" -> ");
        for(String email: emails){
            sb.append(email).append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        return sb.toString();
    }
}
