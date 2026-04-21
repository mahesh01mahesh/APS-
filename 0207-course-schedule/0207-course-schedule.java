import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        // build graph
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
        }
        
        int[] state = new int[numCourses]; // 0=unvisited,1=visiting,2=done
        
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, state, i)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean hasCycle(List<List<Integer>> graph, int[] state, int node) {
        if (state[node] == 1) return true;   // cycle
        if (state[node] == 2) return false;  // already processed
        
        state[node] = 1; // mark visiting
        
        for (int neighbor : graph.get(node)) {
            if (hasCycle(graph, state, neighbor)) {
                return true;
            }
        }
        
        state[node] = 2; // mark done
        return false;
    }
}