import java.util.*;
import java.text.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        ParkingLot parkingLot = new ParkingLot(fees);
        for(String record : records){
            parkingLot.processInAndOut(record);
        }
        parkingLot.processNoOutCar();

        // 문제 정답 조건
        // > 자동차 번호를 오름차순으로 정렬한다. 자동차마다 누적시간을 요금으로 계산한다.
        Map<String, Car> parkingCar = parkingLot.getParkingCar();
        List<String> numberList = new ArrayList<>(parkingCar.keySet());
        Collections.sort(numberList);

        int[] answer = new int[numberList.size()];
        for(int i = 0; i<numberList.size(); i++){
            Car car = parkingCar.get(numberList.get(i));
            answer[i] = parkingLot.calculateFee(car.getTotalParkingTime());
        }

        return answer;
    }

    // NOTE: Map 을 여러번 사용해야 한다면 key와 value를 클래스의 필드로 만들어보자
    class Car {
        private String number;
        private String inTime;
        private int totalParkingTime;   //전체 주차시간. 기본시간: 초과시간이하 & 초과시간:(전체시간-기본시간)

        public Car(String number) {
            this.number = number;
            this.inTime = null;
            this.totalParkingTime = 0;
        }

        //입차
        void park (String inTime) {
            this.inTime = inTime;
        }

        //출차
        void leave(String outTime) {
            Long time = calculateTime(inTime, outTime);
            //System.out.printf("입출차 시간 {%d}\n",time);
            this.totalParkingTime += time;
            this.inTime = null;
        }

        //입출차 시간 계산하기
        private Long calculateTime(String inTime, String outTime) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Long inTimeMill = null;
            Long outTimeMill = null;
            try{
                inTimeMill = sdf.parse(inTime).getTime();
                outTimeMill = sdf.parse(outTime).getTime();
            }catch (ParseException pe){
                //System.out.println("입출차 시간 변환 오류");
            }

            return (outTimeMill - inTimeMill) / (1000*60);   //분
        }

        public String getNumber() {
            return number;
        }

        public String getInTime() {
            return inTime;
        }

        public int getTotalParkingTime() {
            return totalParkingTime;
        }
    }

    class ParkingLot {
        int basicTime;
        int basicFee;
        int unitTime;
        int unitFee;

        Map<String,Car> parkingCar;

        String IN = "IN";
        String END_TIME = "23:59";

        //fees: 기본시간, 기본요금, 단위시간, 단위요금
        public ParkingLot(int[] fees) {
            this.basicTime = fees[0];
            this.basicFee = fees[1];
            this.unitTime = fees[2];
            this.unitFee = fees[3];
            parkingCar = new HashMap<>();
        }

        //자동차 별 입출차 내역 정리하기. records: 시각 차량번호 내역
        public void processInAndOut (String record){
            String[] recordArr = record.split(" ");
            String time = recordArr[0];
            String number = recordArr[1];
            String action = recordArr[2];

            parkingCar.putIfAbsent(number,new Car(number)); //자동차 저장

            if(IN.equals(action)){  //입차
                parkingCar.get(number).park(time);
            }else{  //출차
                parkingCar.get(number).leave(time);
            }
        }

        //출차 기록 없는 자동차 정리하기
        public void processNoOutCar(){
            for(Car car : parkingCar.values()){
                if(car.getInTime() != null){
                    car.leave(END_TIME);
                }
            }
        }

        //주차 요금 계산하기
        public int calculateFee(int totalParkingTime){
            //System.out.printf("누적 주차시간 {%d}\n",totalParkingTime);
            if(totalParkingTime <= basicTime){  // 누적시간이 기본시간 이하 시 기본요금
                return basicFee;
            }
            return basicFee + (int) Math.ceil((double)(totalParkingTime - basicTime)/unitTime) * unitFee;
        }

        public Map<String, Car> getParkingCar() {
            return parkingCar;
        }
    }


    //NOTE
    // 자동차의 입차 정보를 저장
    // ★출차가 나왔다는 것은 이미 입차 정보에 있다는 뜻★으로 입차 정보의 시간을 가져와서 출차 시간과 계산한다
    // > 출차한 자동차는 입차 정보를 없앤다. 모든 입출차 정보를 읽으면 입차-출차 세트는 시간 계산완료
    // 자동차의 입차정보가 남았다는 건 출차정보가 없다는 뜻. 마지막 출차시간인 23:59로 계산한다.

}