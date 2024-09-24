// Path in Directed Graph(Find whether a path exists from node 1 to node A)

public class Solution{
    public int solve(int A, int[][] B){
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= A; i++){list.add(new ArrayList<>());}
        for(int[] edge : B){list.get(edge[0]).add(edge[1]);}
        Queue<Integer> neighbor = new LinkedList<>();
        boolean[] visited = new boolean[A+1];
        neighbor.offer(1);
        visited[1] = true;
        while(!neighbor.isEmpty()){
            int check = neighbor.poll();
            if(check == A){return 1;}
            for(int current : list.get(check)){
                if(!visited[current]){
                    visited[current] = true;
                    neighbor.offer(current);
                }
            }
        }
        return 0;
    }
}
// First Depth First Search(Find if Path exists from C to B without repeating any EDGE)
public class Solution{
    public int solve(int[] A, int B, int C){
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i <=A.length; i++){list.add(new ArrayList<>());}
        for(int i = 1; i < A.length; i++){list.get(A[i]).add(i+1);}
        Queue<Integer> neighbor = new LinkedList<>();
        boolean[] visited = new boolean[A.length +1];
        neighbor.offer(C);
        visited[C] = true;
        while(!neighbor.isEmpty()){
            int check = neighbor.poll();
            if(check == B){ return 1;}
            for(int current : list.get(check)){
                if(!visited[current]){
                    visited[current]=true;
                    neighbor.offer(current);
                }
            }
        }
        return 0;
    }
}
// Cycle in Directed Graph (Kahn's Algorithm for Topological Sorting)
public class Solution{
    public int solve(int A, int[][] B){
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= A; i++){list.add(new ArrayList<>());}
        int[] directed = new int[A+1];
        for(int[] edge : B){
            list.get(edge[0]).add(edge[1]);
            directed[edge[1]]++;
        }
        Queue<Integer> neighbor = new LinkedList<>();
        for(int i = 1; i <= A; i++){if(directed[i] == 0){neighbor.offer(i);}}
        int count = 0;
        while(!neighbor.isEmpty()){
            int check = neighbor.poll();
            count++;
            for(int current : list.get(check)){
                directed[current]--;
                if(directed[current] == 0){neighbor.offer(current);}
            }
        }
        if(count == A){return 0;}
        else{return 1;}
    }
}
// Number of Islands
public class Solution{
    private static final int[] rowDir = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] colDir = {-1, 0, 1, -1, 1, -1, 0, 1};
    private boolean inBound(int row, int col, int N, int M){
        return row >= 0 && row < N && col >= 0 && col < M;
    }
    public int solve(int[][] A){
        boolean[][] visited = new boolean[A.length][A[0].length];
        int islandCount = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[0].length; j++){
                if(A[i][j] == 1 && !visited[i][j]){
                    bfs(A, visited, i, j, A.length, A[0].length);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }
    private void bfs(int[][] A, boolean[][] visited, int i, int j, int N, int M){
        Queue<int[]> neighbor = new LinkedList<>();
        neighbor.offer(new int[]{i, j});
        visited[i][j] = true;
        while(!neighbor.isEmpty()){
            int[] cell = neighbor.poll();
            for(int d = 0; d < 8; d++){
                int newRow = cell[0]+ rowDir[d];
                int newCol = cell[1] + colDir[d];
                if(inBound(newRow, newCol, N, M) && A[newRow][newCol] == 1 && !visited[newRow][newCol]){
                    neighbor.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }
    }
}
// Clone Graph

public class Solution{
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node){
        if(node == null){return null;}
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> neighbor = new LinkedList<>();
        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        map.put(node, cloneNode);
        neighbor.offer(node);
        while(!neighbor.isEmpty()){
            UndirectedGraphNode currentNode = neighbor.poll();
            for(UndirectedGraphNode current : currentNode.neighbors){
                if(!map.containsKey(current)){
                    UndirectedGraphNode clonedNeighbor = new UndirectedGraphNode(current.label);
                    map.put(current, clonedNeighbor);
                    neighbor.offer(current);
                }
                map.get(currentNode).neighbors.add(map.get(current));
            }
        }
        return map.get(node);
    }
}