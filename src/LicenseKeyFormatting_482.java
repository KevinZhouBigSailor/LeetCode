import java.util.Arrays;

/**
 * Created by zzhou on 2/15/2018.
 */
public class LicenseKeyFormatting_482 {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int charlen = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                if (charlen > 0 && charlen % k == 0) {
                    sb.append('-');
                }
                sb.append(s.charAt(i));
                charlen++;
            }
        }

        return sb.reverse().toString().toUpperCase();
    }
}
