public class BestTimeToBuyAndSellStockIII_123 {
    public int maxProfit(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for (int price : prices) {
            release2 = Math.max(release2, hold2 + price); // The maximum if we've just sold 2nd stock so far.
            hold2 = Math.max(hold2, release1 - price); // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1 + price);
            hold1 = Math.max(hold1, -price);
        }
        return release2;
    }
}
