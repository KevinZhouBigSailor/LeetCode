package Google.QuestionBank;

import java.util.*;

public class PointsInPlane_3123 {
    static enum Dir {
        N, W, E, S, NW, NE, SE, SW;
    }

    static class Rule {
        int start;
        int dest;
        Dir dir;

        Rule(int start, int dest, Dir dir) {
            this.start = start;
            this.dest = dest;
            this.dir = dir;
        }
    }

    static List<Rule> transformRules(List<Rule> rules) {
        List<Rule> newRules = new ArrayList<>();
        for (Rule rule : rules) {
            if (rule.dir == Dir.N || rule.dir == Dir.NE || rule.dir == Dir.NW) {
                newRules.add(new Rule(rule.start, rule.dest, Dir.N));
            }
            if (rule.dir == Dir.S || rule.dir == Dir.SW || rule.dir == Dir.SE) {
                newRules.add(new Rule(rule.dest, rule.start, Dir.N));
            }
            if (rule.dir == Dir.E || rule.dir == Dir.NE || rule.dir == Dir.SE) {
                newRules.add(new Rule(rule.dest, rule.start, Dir.N));
            }
            if (rule.dir == Dir.W || rule.dir == Dir.NW || rule.dir == Dir.SW) {
                newRules.add(new Rule(rule.start, rule.dest, Dir.N));
            }
        }
        return newRules;
    }

    static List<List<Integer>> constructGraph(int n, List<Rule> rules, Dir dir) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (Rule rule : rules) {
            if (rule.dir.equals(dir)) {
                graph.get(rule.start).add(rule.dest);
            }
        }

        return graph;
    }

    // Topological sort via BFS
    static boolean isCycle(List<List<Integer>> graph) {
        int n = graph.size();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int v : graph.get(i)) {
                inDegree[v]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        int visitedCount = 0;
        while (!queue.isEmpty()) {
            int top = queue.poll();
            for (int v : graph.get(top)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
            visitedCount++;
        }
        return visitedCount != n;
    }

    public static void main(String[] args) {
        int n = 3;
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule(0, 1, Dir.N));
        rules.add(new Rule(1, 2, Dir.N));
        rules.add(new Rule(0, 2, Dir.N));
        List<List<Integer>> nGraph = constructGraph(n, rules, Dir.N);
        List<List<Integer>> wGraph = constructGraph(n, rules, Dir.W);
        System.out.println(!isCycle(nGraph) && !isCycle(wGraph));
    }
}
