class Solution {
    public int solution(int n, int k) {
        String primNumber = Integer.toString(n,k);  // 10진수 > n진수 변환
        if(primNumber.length() == 1){
            return 1;
        }
        int count = 0;
        String[] numberList = primNumber.split("0");
        for(String number : numberList){
            if(!number.isEmpty() && isPrime(Long.parseLong(number))){
                count++;
            }
        }
        return count;
    }

    //소수찾기
    private boolean isPrime(Long num){
        if(num <= 1){
            return false;
        }
        for(int i=2; i <= Math.sqrt(num); i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }
}