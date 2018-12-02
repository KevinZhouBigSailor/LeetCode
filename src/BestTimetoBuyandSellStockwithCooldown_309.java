import java.util.Arrays;

/**
 * Created by zzhou on 2/6/2018.
 */
public class BestTimetoBuyandSellStockwithCooldown_309 {
    public int maxProfit(int[] prices) {
        /*if (prices == null || prices.length <= 1) return 0;
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        Arrays.fill(buy, 0);
        Arrays.fill(sell, 0);
        buy[0] = -prices[0];
        buy[1] = buy[0];
        for (int i = 1; i < prices.length; i++) {
            if (i == 1) {
                buy[i] = Math.max(0 - prices[i], buy[i - 1]);
            } else {
                buy[i] = Math.max(sell[i - 2] - prices[i], buy[i - 1]);
            }
            sell[i] = Math.max(buy[i - 1] + prices[i], sell[i - 1]);
        }
        return sell[prices.length - 1];*/
        int T_ik0_pre = 0, T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;

        for (int price : prices) {
            int T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0_pre - price);
            T_ik0_pre = T_ik0_old;
        }

        return T_ik0;
    }

    public static void main(String[] args) {
        BestTimetoBuyandSellStockwithCooldown_309 instance = new BestTimetoBuyandSellStockwithCooldown_309();
        int[] prices = new int[]{1, 2, 3, 0, 2};
        instance.maxProfit(prices);
    }
}
