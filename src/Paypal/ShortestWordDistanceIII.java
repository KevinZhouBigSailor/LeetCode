package Paypal;

public class ShortestWordDistanceIII {
    public int shortestDistance(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int minDistance = words.length;
        //boolean odd = true;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(word2) && words[i].equals(word1)) {
                if (i2 > i1) {
                    i1 = i;
                } else {
                    i2 = i;
                }
            } else if (words[i].equals(word1)) {
                i1 = i;
            } else if (words[i].equals(word2)) {
                i2 = i;
            }

            if (i1 != -1 && i2 != -1) {
                minDistance = Math.min(minDistance, Math.abs(i1 - i2));
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        ShortestWordDistanceIII instance = new ShortestWordDistanceIII();
        String[] words = {"a", "a"};
        System.out.println(instance.shortestDistance(words, "a", "a"));
    }
}
