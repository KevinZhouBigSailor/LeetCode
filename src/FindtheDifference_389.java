public class FindtheDifference_389 {
    public char findTheDifference(String s, String t) {
        int charCode = t.charAt(s.length());
        for(int i=0; i< s.length(); i++) {
            charCode -= s.charAt(i);
            charCode += t.charAt(i);
        }
        return (char) charCode;
    }
}
