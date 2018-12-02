public class ReverseInteger_7 {
	public int reverse(int x) {
		long ret = 0;
        while (Math.abs(x) != 0) {            
            ret = ret * 10 + x % 10;
            if (Math.abs(ret) > Integer.MAX_VALUE)
                return 0;
            x /= 10;
        }
        return (int)ret;
    }
	
	public static void main(String[] args) {
		ReverseInteger_7 s = new ReverseInteger_7();
		System.out.println(s.reverse(1534236469));
	}
}
