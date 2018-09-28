package strings;

/**
 * Created by Alex Plate on 28.09.2018.
 */
public class ZFunction {
    public static int[] getFunction(String str) {
        if (str.length() <= 0) {
            return new int[0];
        }

        int[] zFunction = new int[str.length()];

        zFunction[0] = str.length();
        int left = 0;
        int right = 0;

        for (int i = 1; i < str.length(); i++) {
            if (right < i) {
                int j = 0;
                while (j + i < str.length() && str.charAt(j) == str.charAt(i + j)) {
                    j++;
                }
                left = i;
                zFunction[i] = j;
                right = i + j - 1;
            } else {
                if (zFunction[i - left] + i <= right) {
                    zFunction[i] = zFunction[i - left];
                } else {
                    left = i;
                    do {
                        right++;
                    } while (right + i < str.length() && str.charAt(right) == str.charAt(right - left));
                    zFunction[i] = right - left;
                    right--;
                }
            }
        }
        return zFunction;
    }
}
