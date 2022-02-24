package Google.QuestionBank;

import java.sql.Timestamp;
import java.util.*;

public class GoogleFinance_339 {
    HashMap<Timestamp, Integer> timeToCost = new HashMap<>();
    int highCost = 0;
    int cur;
    Timestamp highCostTime = null;
    // PriorityQueue<StockPrice> minHeap = new PriorityQueue<>((a, b) -> a.price - b.price);
    final int maxSize = 5;

    // TODO: Only keep track of `maxSize` of `StockPrice`
    public void addToCost(int price, Timestamp timestamp) {
        cur = price;
        timeToCost.put(timestamp, price);
        //updateHeap(new StockPrice(timestamp, price));
        if (price > highCost) {
            highCost = price;
            highCostTime = timestamp;
        }
    }

    public void updateCost(int price, Timestamp timestamp) {
        timeToCost.put(timestamp, price);
        if (highCostTime.equals(timestamp)) {
            if (price > highCost) {
                highCost = price;
            } else {
                updateHighCost();
            }
        } else if (price > highCost) {
            highCost = timeToCost.get(price);
            highCostTime = timestamp;
        }
    }

    /*
    private void updateHeap(StockPrice stockPrice) {
        if (minHeap.size() < maxSize) {
            minHeap.offer(stockPrice);
        } else if (minHeap.peek().price < stockPrice.price) {
            minHeap.poll();
            minHeap.offer(stockPrice);
        }
    }
    */

    private void updateHighCost() {
        highCost = 0;
        highCostTime = null;
        for (Timestamp k : timeToCost.keySet()) {
            if (timeToCost.get(k) > highCost) {
                highCost = timeToCost.get(k);
                highCostTime = k;
            }
        }
    }

    public void dropCost(Timestamp timestamp) {
        timeToCost.remove(timestamp);
        if (highCostTime.equals(timestamp)) {
            updateHighCost();
        }
    }

    static class StockPrice {
        Timestamp timestamp;
        int price;

        public StockPrice(Timestamp timestamp, int price) {
            this.timestamp = timestamp;
            this.price = price;
        }
    }
}
