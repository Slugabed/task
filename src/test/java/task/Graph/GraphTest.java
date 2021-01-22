package task.Graph;

import org.junit.Assert;
import org.junit.Test;
import task.graph.Graph;

import java.util.*;

public class GraphTest {
    @Test
    public void testConnectivityTwoComponents(){
        List<List<Integer>> connectivity = new ArrayList<>();
        //0
        connectivity.add(new ArrayList<>(Arrays.asList(0, 1, 5)));
        //1
        connectivity.add(new ArrayList<>(Arrays.asList(0, 1)));
        //2
        connectivity.add(new ArrayList<>(Arrays.asList(2, 3)));
        //3
        connectivity.add(new ArrayList<>(Arrays.asList(2,3)));
        //4
        connectivity.add(new ArrayList<>(Collections.singletonList(4)));
        connectivity.add(new ArrayList<>(Arrays.asList(5, 2)));
        Set<Set<Integer>> expected = new HashSet<>();
        expected.add(new HashSet<>(Arrays.asList(0, 1, 2, 3, 5)));
        expected.add(new HashSet<>(Collections.singletonList(4)));
        Set<Set<Integer>> actual = Graph.connectedComponents(connectivity);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testConnectivityOneComponent(){
        List<List<Integer>> connectivity = new ArrayList<>();
        //0
        connectivity.add(new ArrayList<>(Arrays.asList(0, 1, 2)));
        //1
        connectivity.add(new ArrayList<>(Arrays.asList(0, 1, 2)));
        //2
        connectivity.add(new ArrayList<>(Arrays.asList(0, 1, 2)));
        //3
        Set<Set<Integer>> actual = Graph.connectedComponents(connectivity);
        Set<Set<Integer>> expected = new HashSet<>();
        expected.add(new HashSet<>(Arrays.asList(0, 1, 2)));
        Assert.assertEquals(expected, actual);
    }
}
