import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        
        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }
        
        for (int[] e : redEdges) {
            redGraph[e[0]].add(e[1]);
        }
        
        for (int[] e : blueEdges) {
            blueGraph[e[0]].add(e[1]);
        }
        
        int[] res = new int[n];
        Arrays.fill(res, -1);
        
        boolean[][] visited = new boolean[n][2]; // 0 = red, 1 = blue
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0}); // start with red
        q.offer(new int[]{0, 1}); // start with blue
        
        visited[0][0] = true;
        visited[0][1] = true;
        
        int steps = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int node = curr[0];
                int color = curr[1];
                
                if (res[node] == -1) {
                    res[node] = steps;
                }
                
                if (color == 0) { // last edge was red → use blue
                    for (int next : blueGraph[node]) {
                        if (!visited[next][1]) {
                            visited[next][1] = true;
                            q.offer(new int[]{next, 1});
                        }
                    }
                } else { // last edge was blue → use red
                    for (int next : redGraph[node]) {
                        if (!visited[next][0]) {
                            visited[next][0] = true;
                            q.offer(new int[]{next, 0});
                        }
                    }
                }
            }
            
            steps++;
        }
        
        return res;
    }
}