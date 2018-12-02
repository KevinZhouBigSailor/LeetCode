import java.util.Arrays;

/**
 * Created by zzhou on 1/25/2018.
 */
public class SuperWashingMachines_517 {
    public int findMinMoves(int[] machines) {
        int total = 0, target = 0, result = 0, n = machines.length;
        total = Arrays.stream(machines).sum();
        if (total == 0) return 0;
        if (total % n != 0) return -1;
        target = total / n;

        int[] move = new int[n];
        for (int i = 0; i < n - 1; i++) {
            if (machines[i] > target) {
                move[i] += machines[i] - target;
                machines[i + 1] += machines[i] - target;
                machines[i] = target;
                result = Math.max(result, move[i]);
            } else {
                move[i + 1] = target - machines[i];
                machines[i + 1] -= target - machines[i];
                machines[i] = target;
                result = Math.max(result, move[i + 1]);
            }
        }

        return result;
    }
}
