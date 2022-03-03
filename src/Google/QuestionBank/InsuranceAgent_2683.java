package Google.QuestionBank;

import java.util.*;

public class InsuranceAgent_2683 {
    // Solution 1: Priority Queue
    public int solve(int N, int M, int T[]) {
        int[] agent = new int[2];
        // (endServiceTime, agentIndex)
        PriorityQueue<int[]> agents
                = new PriorityQueue<>((a, b) -> (a[0] == b[0] ? (a[1] - b[1]) : a[0] - b[0]));

        for (int i = 0; i < N; i++) {
            agents.add(new int[]{0, i});
        }

        for (int i = 0; i < M; i++) {
            agent = agents.poll();
            agent[0] += T[agent[1]];
            agents.add(agent);
        }

        return agents.peek()[0];
    }

    // Solution 2: Binary search
    public int solve2(int N, int M, int T[]) {
        int Tmax = 0, lowerBound = 0, upperBound = 0, waitingTime = 0;
        for (int i = 0; i < N; i++) Tmax = Math.max(T[i], Tmax);
        upperBound = Tmax * M;
        while (lowerBound < upperBound) {
            waitingTime = (lowerBound + upperBound) / 2;
            if (numOfServedCustomer(waitingTime, T) < M) {
                lowerBound = waitingTime + 1;
            } else {
                upperBound = waitingTime;
            }
        }
        return lowerBound;
    }

    private int numOfServedCustomer(int time, int[] T) {
        int num = 0;
        for (int i = 0; i < T.length; i++) {
            num += time / T[i];
        }

        return num;
    }

    // TODO: Solution 3: Least Common Multiple
    /**
     * Lower bound:T_ave*M, Upper bound T_ave*(M+N)
     */
}
