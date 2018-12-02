/**
 * Created by zzhou on 5/5/2017.
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 Example 1:
 Input: "abab"

 Output: True

 Explanation: It's the substring "ab" twice.
 Example 2:
 Input: "aba"

 Output: False
 Example 3:
 Input: "abcabcabcabc"

 Output: True

 Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)

 Knuth–Morris–Pratt algorithm!!!
 */
public class RepeatedSubstringPattern_459 {
    public boolean repeatedSubstringPattern(String s) {
        //This is the kmp issue
        int[] prefix = kmp(s);
        int len = prefix[s.length()-1];
        int n = s.length();
        return (len > 0 && n%(n-len) == 0);
    }

    private int[] kmp(String s){
        int len = s.length();
        int[] res = new int[len];
        char[] ch = s.toCharArray();
        int i = 0, j = 1;
        res[0] = 0;
        while(i < ch.length && j < ch.length){
            if(ch[j] == ch[i]){
                res[j] = i+1;
                i++;
                j++;
            }else{
                if(i == 0){
                    res[j] = 0;
                    j++;
                }else{
                    i = res[i-1];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RepeatedSubstringPattern_459 instance = new RepeatedSubstringPattern_459();
        System.out.println(instance.repeatedSubstringPattern("ababab"));
    }
}
