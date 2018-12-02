import java.util.Stack;

public class AsteroidCollision_735 {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int ast : asteroids) {
            collision:
            {
                while (!stack.isEmpty() && ast < 0 && stack.peek() > 0) {
                    if(stack.peek() < -ast) {
                        stack.pop();
                        continue;
                    }
                    else if(stack.peek() == -ast) {
                        stack.pop();
                    }
                    break collision;
                }
                stack.push(ast);
            }
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        AsteroidCollision_735 instance = new AsteroidCollision_735();
        int[] asteroids = {4, -5, 3, 2};
        instance.asteroidCollision(asteroids);
    }
}
