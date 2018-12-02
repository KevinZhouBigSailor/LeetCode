public class DesignCompressedStringIterator_604 {
    String res;
    int ptr = 0, num = 0;
    char ch = ' ';

    public void StringIterator(String compressedString) {
        res = compressedString;
    }

    public char next() {
        if (!hasNext()) return ' ';
        if (num == 0) {
            ch = res.charAt(ptr++);
            while (ptr<res.length() && Character.isDigit(res.charAt(ptr))) {
                num = num * 10 + res.charAt(ptr++) - '0';
            }
        }
        num--;
        return ch;
    }

    public boolean hasNext() {
        return ptr != res.length() || num !=0;
    }
}
