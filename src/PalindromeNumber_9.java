public class PalindromeNumber_9 {
	public boolean isPalindrome(int x) {
		//optimizations
        if(x<0) return false;
        if(x<10) return true;
        if(x%10==0) return false;
        if(x<100&&x%11==0) return true;
        if(x<1000&&((x/100)*10+x%10)%11==0) return true;
		
		long xx = x;
        long new_xx = 0;
        while (xx > 0) {
            new_xx = new_xx * 10 + xx % 10;
            xx /= 10;
        }
        return new_xx == x;
    }
}
