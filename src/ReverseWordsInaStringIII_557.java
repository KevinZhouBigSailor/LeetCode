/**
 * Created by zzhou on 8/16/2017.
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

 Example 1:
 Input: "Let's take LeetCode contest"
 Output: "s'teL ekat edoCteeL tsetnoc"
 Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */
public class ReverseWordsInaStringIII_557 {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n ; i++) {
            if (arr[i] != ' ') {   // when i is a non-space
                int j = i;
                while (j + 1 < n && arr[j + 1] != ' ') { j++; } // move j to the end of the word
                swap(arr, i, j);
                i = j;
            }
        }

        return String.valueOf(arr);
    }

    private void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }
}



