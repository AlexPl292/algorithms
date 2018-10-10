package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Alex Plate on 10.10.2018.
 */
public class IpSorter {
    public static void main(String[] args) {
        Comparator<String> ipComparator = (o1, o2) -> {
            String[] firstIps = o1.split("\\.");
            String[] secondIps = o2.split("\\.");
            int comparation = 0;
            for (int i = 0; i < 4; i++) {
                comparation = Integer.valueOf(firstIps[i]).compareTo(Integer.valueOf(secondIps[i]));
                if (comparation != 0) {
                    return comparation;
                }
            }
            return comparation;
        };

        List<String> ips = new ArrayList<>(Arrays.asList("10.62.12.143", "10.62.12.145", "10.62.12.144", "10.62.12.111"));

        ips.sort(ipComparator);
        System.out.println(Arrays.toString(ips.toArray()));
    }
}
