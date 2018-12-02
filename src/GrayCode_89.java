import java.util.LinkedList;
import java.util.List;

public class GrayCode_89 {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1 << n; i++) result.add(i ^ i >> 1);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(4 >> 2);
        System.out.println(4 >> 1);
        System.out.println(1 ^ 4>>1);
        System.out.println(2 ^ 4>>1);
    }
}
