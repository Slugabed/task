package task.service;

import task.model.User;
import task.graph.Graph;

import java.util.*;

public class UserService {
    public Set<User> makeUnique(List<User> users) {
        int N = users.size();
        List<List<Integer>> connectivity = new ArrayList<>();
        for(int i=0; i<N; i++){
            connectivity.add(new ArrayList<>());
        }
        Map<String, Set<String>> emailToUser = new HashMap<>();
        Map<String, Integer> userToIndex = new HashMap<>();
        for(int i=0; i< users.size(); i++){
            userToIndex.put(users.get(i).getName(), i);
        }

        for(int i=0; i< users.size(); i++){
            User user = users.get(i);
            for(String email: user.getEmails()){
                emailToUser.putIfAbsent(email, new HashSet<>());
                for(String userName: emailToUser.get(email)){
                    connectivity.get(userToIndex.get(userName)).add(i);
                    connectivity.get(i).add(userToIndex.get(userName));
                }
                emailToUser.get(email).add(user.getName());
            }
        }

        Set<Set<Integer>> connectedComponents = Graph.connectedComponents(connectivity);
        Set<User> result = new HashSet<>();
        for(Set<Integer> component: connectedComponents){
            Set<String> emails = new HashSet<>();
            for(Integer index: component){
                emails.addAll(users.get(index).getEmails());
            }
            String randomName = users.get(component.iterator().next()).getName();
            result.add(new User(randomName, emails));
        }
        return result;
    }
}
