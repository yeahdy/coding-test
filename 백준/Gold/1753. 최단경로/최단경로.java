import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node>{
        int index;
        int value;
        Node (int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o){
            return this.value - o.value;
        }
    }

    static final int MAX = Integer.MAX_VALUE;
    static List<List<Node>> graph = new ArrayList<>();
    static int[] table;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        table = new int[V+1];
        Arrays.fill(table,MAX);
        //간선의 수는 N+1 개
        for(int i=0; i<V+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            graph.get(n1).add(new Node(n2,value));
        }

        execution(K);
        for(int i=1; i<table.length; i++){
            if(table[i] == MAX){
                System.out.println("INF");
            }else{
                System.out.println(table[i]);
            }
        }
    }

    static void execution(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        table[start] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int index = node.index;
            int value = node.value;

            if(value > table[index]){
                continue;
            }

            for(Node next : graph.get(index)){
                int nIndex = next.index;
                int currentAndNext = value + next.value;
                if(currentAndNext < table[nIndex]){
                    table[nIndex] = currentAndNext;
                    pq.offer(new Node(nIndex,currentAndNext));
                }
            }
        }
    }
}