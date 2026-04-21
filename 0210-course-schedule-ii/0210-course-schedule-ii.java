import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        
        // init graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        // build graph + indegree
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
            indegree[p[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        // add all nodes with indegree 0
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] result = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result[index++] = curr;
            
            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        // if not all courses processed → cycle
        if (index != numCourses) {
            return new int[0];
        }
        
        return result;
    }
}