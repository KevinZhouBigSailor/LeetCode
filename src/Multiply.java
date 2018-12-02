import java.util.ArrayList;
import java.util.List;

public class Multiply {
    int N = 0;

    public List<List<String>> multiplyDikaer(List<List<String>> inputs) {
        N = inputs.size();
        List<List<String>> res = new ArrayList<>();
        if (N == 0) return res;
        dfs(inputs, new ArrayList<String>(), 0, res);
        return res;
    }

    public void dfs(List<List<String>> inputs, List<String> tempRes, int listIndex, List<List<String>> res) {
        //if(inputs.size() == res.size()) return;
        if (listIndex == N) {
            res.add(new ArrayList<>(tempRes));
            return;
        }
        for (int i = 0; i < inputs.get(listIndex).size(); i++) {
            tempRes.add(inputs.get(listIndex).get(i));
            dfs(inputs, tempRes, listIndex + 1, res);
            tempRes.remove(tempRes.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> one = new ArrayList<>();
        one.add("one");
        one.add("2");
        List<String> two = new ArrayList<>();
        two.add("two");
        List<List<String>> inputs = new ArrayList<>();
        inputs.add(new ArrayList<>(one));
        inputs.add(new ArrayList<>(two));
        Multiply instance = new Multiply();
        instance.multiplyDikaer(inputs);

    }
}
