public class ReadNCharactersGivenRead4IICallmultipletimes_158 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return The number of characters read
     */
    private int prevIndex = 0;
    private int prevSize = 0;
    private char[] prevBuff = new char[4];

    public int read(char[] buf, int n) {
        int counter = 0;
        while (counter < n) {
            if (prevIndex < prevSize) {
                buf[counter++] = prevBuff[prevIndex++];
            } else {
                prevSize = read4(prevBuff);
                prevIndex = 0;
                if (prevSize == 0) {
                    //no more data to stream
                    break;
                }
            }
        }
        return counter;
    }

    private int read4(char[] buff) {
        return 0;
    }
}
