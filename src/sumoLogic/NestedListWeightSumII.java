package sumoLogic;

import java.util.*;

public class NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    unweighted += ni.getInteger();
                } else {
                    nextLevel.addAll(ni.getList());
                }
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }

        return weighted;
    }

    public int depthSumInverse2(List<NestedInteger> nestedList) {
        int prevSum = 0, totalSum = 0;
        Queue<NestedInteger> queue = new ArrayDeque();
        for (NestedInteger ni : nestedList) {
            queue.offer(ni);
        }

        while (!queue.isEmpty()) {
            int size = queue.size(), levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.poll();
                if (current.isInteger()) {
                    levelSum += current.getInteger();
                } else {
                    for (NestedInteger ni: current.getList()) {
                        queue.offer(ni);
                    }
                }
            }
            prevSum += levelSum;
            totalSum += prevSum;
        }
        return totalSum;
    }
}
