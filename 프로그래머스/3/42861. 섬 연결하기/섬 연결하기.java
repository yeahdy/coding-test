import java.util.*;

class Solution {
    static class Bridge {
        int x;
        int y;
        int cost;
        Bridge(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static int solution(int n, int[][] costs) {
        int length = costs.length;
        Bridge[] bridgeArr = new Bridge[length];
        for(int i=0; i<length; i++){
            if(costs[i][2] == 0){
                continue;
            }
            bridgeArr[i] = new Bridge(costs[i][0],costs[i][1],costs[i][2]);
        }

        Arrays.sort(bridgeArr, Comparator.comparingInt(o -> o.cost));

        int[] parent = new int[length+1];
        for(int i=1; i<length+1; i++){
            parent[i] = i;
        }

        int minCost = 0;
        int line = 0;
        for(Bridge bridge : bridgeArr){
            if(line == n-1){    //간선수는 n-1개
                break;
            }

            if(find(parent, bridge.x) != find(parent, bridge.y)){
                union(parent,bridge.x,bridge.y);
                minCost += bridge.cost;
                line++;
            }
        }
        return minCost;
    }

    static int find(int[] parent, int num){
        if(parent[num] == num){
            return parent[num];
        }
        return parent[num] = find(parent,parent[num]);
    }

    static void union(int[] parent,int num1, int num2){
        num1 = find(parent,num1);
        num2 = find(parent,num2);

        if(num1 == num2){
            return;
        }
        if(num1 > num2){
            parent[num1] = num2;
        }else{
            parent[num2] = num1;
        }
    }
}