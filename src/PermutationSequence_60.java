import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermutationSequence_60 {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new LinkedList<>();
        int[] factorial = new int[n + 1];
        StringBuilder sb = new StringBuilder();

        int sum = 1;
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
            factorial[i] = sum;
            numbers.add(i);
        }

        k--;

        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            sb.append(String.valueOf(numbers.get(index)));
            k -= index * factorial[n - i];
            numbers.remove(index);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        PermutationSequence_60 instance = new PermutationSequence_60();
        instance.getPermutation(3, 2);
    }
}
