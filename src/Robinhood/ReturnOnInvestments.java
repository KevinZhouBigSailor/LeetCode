package Robinhood;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ReturnOnInvestments {
    public LinkedHashMap<String, Long> myReturn(List<String> inputOnes, List<String> inputTwos) {
        HashMap<String, Long> holdings = new HashMap<>();
        for (String inputOne : inputOnes) {
            String[] inputOneArr = inputOne.split("\\s+");
            holdings.put(inputOneArr[1], holdings.getOrDefault(inputOneArr, 0L) + Long.valueOf(inputOneArr[0]) * Long.valueOf(inputOneArr[3]));
        }
        for (String inputTwo : inputTwos) {
            String[] inputTwoArr = inputTwo.split("\\s+");
            if (inputTwoArr[0].equals("bought")) {
                holdings.put(inputTwoArr[2], holdings.getOrDefault(inputTwoArr[2], 0L) - Long.valueOf(inputTwoArr[1]) * Long.valueOf(inputTwoArr[4]));
            } else {
                holdings.put(inputTwoArr[2], holdings.getOrDefault(inputTwoArr[2], 0L) + Long.valueOf(inputTwoArr[1]) * Long.valueOf(inputTwoArr[4]));
            }
        }

        LinkedHashMap<String, Long> sorted = holdings.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        LinkedHashMap<String, Long> output = new LinkedHashMap<>();
        Long total = 0L;
        for (String key : sorted.keySet()) {
            total += sorted.get(key);
        }
        output.put("total", total);
        output.putAll(sorted);

        return output;
    }

    //@Test
    public static void main(String[] args) {
        ReturnOnInvestments investments = new ReturnOnInvestments();
        List<String> inputOnes = new ArrayList<>();
        inputOnes.add("4 AAPL valued 100 each");
        inputOnes.add("10 SPY valued 200 each");
        inputOnes.add("20 BND valued 150 each");
        List<String> inputTwos = new ArrayList<>();
        inputTwos.add("bought 10 AAPL at 300");
        inputTwos.add("sold 6 AAPL at 250");
        inputTwos.add("bought 5 SPY at 50");
        inputTwos.add("bought 5 SPY at 75");
        inputTwos.add("bought 20 BND at 145");
        inputTwos.add("sold 5 TLSA at 50");
        inputTwos.add("bought 5 TLSA at 50");

        System.out.println(investments.myReturn(inputOnes, inputTwos));
    }
}
