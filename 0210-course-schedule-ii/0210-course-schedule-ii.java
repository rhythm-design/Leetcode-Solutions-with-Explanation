class Solution {
    private boolean cycleInDirectedGraph(ArrayList<Integer>[] graph, boolean[] visited, int source, boolean visitedByDFS[]){

        visitedByDFS[source]=true;
        visited[source]=true;
        for(int course: graph[source]){
            if(visited[course]==false){
                if(cycleInDirectedGraph(graph, visited, course,visitedByDFS)) return true;
            }else if(visitedByDFS[course]){
                return true;
            }
        }
        visitedByDFS[source]=false;
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer> graph[]= new ArrayList[numCourses];
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        for(int[] edge: prerequisites){
            graph[edge[0]].add(edge[1]);
        }
        boolean visited[]= new boolean[graph.length];
        boolean visitedByDFS[]= new boolean[graph.length];
        for(int i=0;i<visited.length;i++){
            if(!visited[i]){
                if(cycleInDirectedGraph(graph, visited, i, visitedByDFS)) return false;
            }
        }
        return true;
    }
    int index;
    private void topoSort(ArrayList<Integer>[] graph, boolean[] visited, int[] compileOrder, int source){

        visited[source]=true;
        for(int course: graph[source]){
            if(!visited[course]){
                topoSort(graph, visited, compileOrder, course);
            }
        }
        compileOrder[index++]=(source);
    }

    // TODO: Combine cycle and topo function 
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        index=0;
        ArrayList<Integer> graph[]= new ArrayList[numCourses];
        for(int i=0;i<graph.length;i++){
            graph[i]= new ArrayList<>();
        }
        boolean visited[]= new boolean[graph.length];
        for(int[] pair: prerequisites){
            graph[pair[0]].add(pair[1]);
        }
        int[] compileOrder=new int[graph.length];
        if(!canFinish(numCourses, prerequisites)) return new int[0];
        for(int i=0;i<visited.length;i++){
            if(!visited[i]){
                topoSort(graph, visited, compileOrder, i);
            }
        }
        return compileOrder;
    }
}