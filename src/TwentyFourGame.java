import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwentyFourGame {
    public boolean judgePoint24(int[] nums) {
        List<Double> listArray = new ArrayList<>();
        for (int ele : nums) {
            listArray.add((double) ele);
        }
        return backtracking(listArray);
    }

    public boolean backtracking(List<Double> listArray) {
        if (listArray.size() < 1) {
            return false;
        }
        if (listArray.size() == 1) {
            if (Math.abs(listArray.get(0) - 24.0) < 0.0001) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < listArray.size(); i++) {
            for (int j = 0; j < i; j++) {
                double ele1 = listArray.get(i);
                double ele2 = listArray.get(j);
                List<Double> temp = new ArrayList<>();
                temp.addAll(Arrays.asList(ele1 + ele2, ele1 - ele2, ele2 - ele1, ele1 * ele2));
                if (Math.abs(ele1) > 0.0001) {
                    temp.add(ele2 / ele1);
                }
                if (Math.abs(ele2) > 0.0001) {
                    temp.add(ele1 / ele2);
                }
                listArray.remove(i);
                listArray.remove(j);
                for (double ele : temp) {
                    listArray.add(ele);
                    if (backtracking(listArray)) {
                        return true;
                    }
                    listArray.remove(listArray.size() - 1);
                }

                listArray.add(j, ele2);
                listArray.add(i, ele1);
            }
        }

        return false;
    }
}
