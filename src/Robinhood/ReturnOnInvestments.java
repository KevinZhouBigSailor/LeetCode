package Robinhood;

//import org.junit.jupiter.api.Test;

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

    public int tradeCounts(List<Stock> sells, List<Stock> buys) {
        int count = 0;
        HashMap<String, PriorityQueue<Stock>> maps = new HashMap<>();
        for (Stock sell : sells) {
            if (!maps.containsKey(sell.name)) {
                maps.put(sell.name, new PriorityQueue<>());
            }
            maps.get(sell.name).add(sell);
        }
        for (Stock buy : buys) {
            while (maps.get(buy.name).size() > 0 && maps.get(buy.name).peek().price < buy.price && buy.count > 0) {
                Stock sell = maps.get(buy.name).poll();
                if (sell.count > buy.count) {
                    sell.count -= buy.count;
                    count++;
                    maps.get(buy.name).add(sell);
                    break;
                } else {
                    buy.count -= sell.count;
                    count++;
                }
                //if (maps.get(buy.name).size() == 0) break;
            }
        }
        return count;
    }

    class Stock implements Comparable<Stock> {
        String name;
        int count;
        int price;

        Stock(String name, int count, int price) {
            this.name = name;
            this.count = count;
            this.price = price;
        }

        @Override
        public int compareTo(Stock o) {
            if (this.price == o.price) {
                return 0;
            } else if (this.price < o.price) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    /*@Test
    public void test() {
        Stock sell1 = new Stock("AAPL", 1, 1);
        Stock sell2 = new Stock("AAPL", 1, 3);
        Stock buy1 = new Stock("AAPL", 2, 2);
        //Stock buy2 = new Stock("AAPL", 1, 2);

        List<Stock> sells = new ArrayList<>();
        sells.add(sell1);
        sells.add(sell2);
        List<Stock> buys = new ArrayList<>();
        buys.add(buy1);
        //buys.add(buy2);

        System.out.println(tradeCounts(sells, buys));
    }*/

    /*@Test
    public void maintest() {
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
    }*/
}
