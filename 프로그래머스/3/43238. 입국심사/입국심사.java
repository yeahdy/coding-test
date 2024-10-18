class Solution {
    public long solution(int n, int[] times) {
        int length = times.length;
        long left = 0;
        long right = (long) times[length-1]*n;

        while(left<=right){
            long mid = (left+right)/2;

            long count = 0;
            for(int time : times){
                count += mid/time;
            }

            if(count>=n){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return right+1;
    }
}