public class StringToIntegerAtoi_8 {
	public int myAtoi(String str) {
		int ret = 0;
		
//		if (str == null)
//        	return ret;
        
		boolean overflow = false;
        int sign = 1;	// default is '+'
        int i = 0;
        int len = str.length();

        while (i < len && (str.charAt(i) == ' ' || str.charAt(i) == '\n' || str.charAt(i) == '\t'))
            ++i;
        if (i == len)
            return 0;

        // get sign
        if (str.charAt(i) == '-') {
            ++i;
            sign = -1;
        } else if (str.charAt(i) == '+')
            ++i;
		
        while(i < len) {
        	if (str.charAt(i) < '0' || str.charAt(i) > '9')
                break;
        	
        	//if  (Math.abs(ret * 10 +  (str.charAt(i) - '0') ) > Integer.MAX_VALUE) {
        	
        	if ((sign == 1 && ret > (Integer.MAX_VALUE - (str.charAt(i)-'0')) / 10) ||
                    (sign == -1 && -ret < (Integer.MIN_VALUE + (str.charAt(i)-'0')) / 10)) {
        		overflow = true;
        		break;
        	}
        		
        	ret = ret * 10 + (str.charAt(i) - '0');	//trick part
        	++i; 
        }
                
        if (overflow)
            ret = (sign == 1) ?  Integer.MAX_VALUE: Integer.MIN_VALUE;
        else
            ret *= sign;
        return ret;
    }
	
	public static void main(String[] args) {
		StringToIntegerAtoi_8 s = new StringToIntegerAtoi_8();
		System.out.println(s.myAtoi("      -11919730356x"));
		System.out.println((int)"1".charAt(0));
		System.out.println((int)"0".charAt(0));
	}
}
