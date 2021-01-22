package task.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
    public static Set<Set<Integer>> connectedComponents(List<List<Integer>> connectivity){
        assert connectivity.size() > 0;
        int N = connectivity.size();

        Set<Integer> remained = new HashSet<>();
        for(int i=0; i<N; i++){
            remained.add(i);
        }
        Set<Set<Integer>> result = new HashSet<>();
        while(!remained.isEmpty()){
            int start = remained.iterator().next();
            Set<Integer> fired = new HashSet<>();
            Set<Integer> burned = new HashSet<>();
            fired.add(start);
            boolean[] isVisited = new boolean[N];
            while(!fired.isEmpty()){
                int firing = fired.iterator().next();
                fired.remove(firing);
                burned.add(firing);
                isVisited[firing] = true;
                for(int connected: connectivity.get(firing)){
                    if (!isVisited[connected]){
                        fired.add(connected);
                    }
                }
            }
            result.add(burned);
            remained.removeAll(burned);
        }
        return result;
    }
}
