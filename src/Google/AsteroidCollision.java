package Google;

import java.util.LinkedList;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> s = new LinkedList<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] > 0 || s.isEmpty() || s.getLast() < 0) {
                s.add(asteroids[i]);
            } else if (s.getLast() <= -asteroids[i]) {
                if (s.pollLast() < -asteroids[i]) i--;
            }
        }
        return s.stream().mapToInt(i -> i).toArray();
    }
}
