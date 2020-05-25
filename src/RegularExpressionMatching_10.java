public class RegularExpressionMatching_10 {
	public boolean isMatch(String s, String p) {
		int lens = s.length();
	    int lenp = p.length();
	    if (lens == 0 && lenp == 0) return true;

	    boolean[] last = new boolean[lenp + 1]; //last[j] means if p[1~j] matches s[1~i-1]
	    boolean[] cur = new boolean[lenp + 1]; //cur[j] means if p[1~j] matches s[1~i]
	    last[0] = cur[0] = true;
	    // for string like "a*b*c*", make last/cur[indexOf('*')]=true.
	    for (int j = 1; j <= lenp; j++) {
	        if (j >= 2 && p.charAt(j - 1) == '*' && last[j - 2]) {
	            last[j] = cur[j] = true;
	        }
	    }

	    for (int i = 1; i <= lens; i++) {
	        // determine if p[1~j] matches s[1~i].
	        cur[0] = false;
	        for (int j = 1; j <= lenp; j++) {
	            // three cases: 
	            // 1) p[j]==s[i] (include p[j]=='.') and p[1~j-1] matches s[1~i-1];
	            // 2) p[j-1~j]="x*" and s[i]='x' and p[1~j] matches s[1~i-1];
	            // 3) p[j-2~j]="xy*" and p[1~j-2] matches s[1~i].	        	
	            cur[j] = (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') && last[j - 1]
	                    || p.charAt(j - 1) == '*' && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') && last[j]
	                    || j >= 2 && p.charAt(j - 1) == '*' && cur[j - 2];
	                    
	                    if (j == 4) {
	    	        		System.out.println("1:"+ String.valueOf((p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') && last[j - 1]));
	    	        		System.out.println("2: "+ String.valueOf(p.charAt(j - 1) == '*' && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') && last[j]));
	    	        		System.out.println("3:"+ String.valueOf(j >= 2 && p.charAt(j - 1) == '*' && cur[j - 2]));
	    	        		System.out.println(cur[j]);
	    	        	}
	        }
	        for (int j = 0; j <= lenp; j++) {
	            last[j] = cur[j];
	        }
	    }

	    return cur[lenp];
	}

	class Solution {
		public boolean isMatch(String text, String pattern) {
			boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
			dp[text.length()][pattern.length()] = true;

			for (int i = text.length(); i >= 0; i--){
				for (int j = pattern.length() - 1; j >= 0; j--){
					boolean first_match = (i < text.length() &&
							(pattern.charAt(j) == text.charAt(i) ||
									pattern.charAt(j) == '.'));
					if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
						dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
					} else {
						dp[i][j] = first_match && dp[i+1][j+1];
					}
				}
			}
			return dp[0][0];
		}
	}
	
	
	public static void main(String[] args) {
		RegularExpressionMatching_10 s = new RegularExpressionMatching_10();
		//System.out.println(s.isMatch("aa", "a*"));
        System.out.println(s.isMatch("aab", "c*a*b"));
	}
}
