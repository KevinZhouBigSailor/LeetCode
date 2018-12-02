import java.util.Stack;

/**
 * Created by zzhou on 12/18/2017.
 */
public class RemoveKDigits_402 {
    public String removeKdigits(String num, int k) {
        /*int digits = num.length() - k;
        char[] stk = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (top > 0 && stk[top - 1] > c && k > 0) {
                top--;
                k--;
            }
            stk[top++] = c;
        }
        int idx = 0;
        while (idx < digits && stk[idx] == '0') idx++;
        return idx == digits ? "0" : new String(stk, idx, digits - idx);*/
        int len = num.length();
        //corner case
        if(k==len)
            return "0";

        Stack<Character> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();
        //int top = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stk.isEmpty() && stk.peek() > c && k > 0) {
                stk.pop();
                k--;
            }
            stk.push(c);
        }

        // corner case like "1111"
        while(k>0){
            stk.pop();
            k--;
        }

        while(!stk.isEmpty())
            sb.append(stk.pop());
        sb.reverse();

        //remove all the 0 at the head
        while(sb.length()>1 && sb.charAt(0)=='0')
            sb.deleteCharAt(0);
        return sb.toString();
    }
}
