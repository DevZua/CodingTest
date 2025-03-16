import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTimeMap = new HashMap<>();
        Map<String, Integer> totalTimeMap = new TreeMap<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            int time = convertToMinutes(parts[0]);
            String carNumber = parts[1];
            String action = parts[2];

            if (action.equals("IN")) {
                inTimeMap.put(carNumber, time);
            } else {
                int inTime = inTimeMap.remove(carNumber);
                totalTimeMap.put(carNumber, totalTimeMap.getOrDefault(carNumber, 0) + (time - inTime));
            }
        }

        for (String car : inTimeMap.keySet()) {
            int inTime = inTimeMap.get(car);
            totalTimeMap.put(car, totalTimeMap.getOrDefault(car, 0) + (convertToMinutes("23:59") - inTime));
        }

        int[] answer = new int[totalTimeMap.size()];
        int index = 0;
        for (int time : totalTimeMap.values()) {
            answer[index++] = calculateFee(fees, time);
        }
        return answer;
    }

    private int convertToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private int calculateFee(int[] fees, int time) {
        int baseTime = fees[0], baseFee = fees[1], unitTime = fees[2], unitFee = fees[3];

        if (time <= baseTime) {
            return baseFee;
        }
        return baseFee + (int) Math.ceil((double) (time - baseTime) / unitTime) * unitFee;
    }
}
