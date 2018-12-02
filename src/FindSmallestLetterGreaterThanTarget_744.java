public class FindSmallestLetterGreaterThanTarget_744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int low = 0, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if(letters[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return letters[low % n];
    }
}
