/**
 * Created by zzhou on 2/9/2018.
 */
public class SolvetheEquation_640 {
    public String solveEquation(String equation) {
        int[] left = evaluateExpression(equation.split("=")[0]),
                right = evaluateExpression(equation.split("=")[1]);
        left[0] -= right[0];
        left[1] = right[1] - left[1];
        if (left[0] == 0 && left[1] == 0) return "Infinite solutions";
        if (left[0] == 0) return "No solution";
        return "x=" + left[1] / left[0];
    }

    private int[] evaluateExpression(String exp) {
        String[] tokens = exp.split("(?=[-+])");
        int[] res = new int[2];
        for (String token : tokens) {
            if (token.equals("+x") || token.equals("x")) res[0] += 1;
            else if (token.equals("-x")) res[0] -= 1;
            else if (token.contains("x")) res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
            else res[1] += Integer.parseInt(token);
        }
        return res;
    }

    public static void main(String[] args) {
        SolvetheEquation_640 instance = new SolvetheEquation_640();
        instance.solveEquation("x+5-3+x=6+x-2");
    }
}
