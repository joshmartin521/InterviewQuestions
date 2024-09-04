// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardMetadataObfuscation {

    public static List<String> obfuscateCardMetadata(int card_bin, List<String> card_intervals) {
        // Define the start and end of the full BIN range
        long fullStart = card_bin * 1_000_000_0000L;
        long fullEnd = fullStart + 9_999_999_999L;

        // Sort the intervals based on the start value
        Collections.sort(card_intervals, Comparator.comparingLong(s -> Long.parseLong(s.split(",")[0])));

        // Result list to store the obfuscated intervals
        List<String> result = new ArrayList<>();

        long prevEnd = fullStart - 1;  // Previous interval end, initialized to just before fullStart

        for (String interval : card_intervals) {
            String[] parts = interval.split(",");
            long start = fullStart + Long.parseLong(parts[0]);
            long end = fullStart + Long.parseLong(parts[1]);
            String brand = parts[2].trim();
            
            if(result.size() > 0 && brand.equals(result.get(result.size() - 1).split(",")[2]))
            {
                String last = result.remove(result.size()-1);
                String [] lastar = last.split(",");
                result.add(formatInterval(Long.parseLong(lastar[0]), end, lastar[2]));
                continue;
            }

            // Handle gap before this interval
            else if (start > prevEnd + 1) {
                String last = result.remove(result.size()-1);
                String [] lastar = last.split(",");
                result.add(formatInterval(Long.parseLong(lastar[0]), start-1, lastar[2]));
            }
            
            
            // Add the current interval
            result.add(formatInterval(start, end, brand));

            prevEnd = end;  // Update prevEnd
        }

        // Handle any remaining gap at the end of the BIN range
        if (prevEnd < fullEnd) {
            String last = result.remove(result.size()-1);
            String [] lastar = last.split(",");
            result.add(formatInterval(Long.parseLong(lastar[0]), fullEnd, lastar[2]));
        }

        return result;
    }

    private static String formatInterval(long start, long end, String brand) {
        return String.format("%d,%d,%s", start, end, brand);
    }

    public static void main(String[] args) {
        int card_bin = 333333;
        List<String> card_intervals = new ArrayList<>();
        card_intervals.add("7000000000,7900000000, AMEX");
        card_intervals.add("0000000000,5999999999, DISCOVER");
        card_intervals.add("8999999999,9999999999, AMEX");

        List<String> result = obfuscateCardMetadata(card_bin, card_intervals);
        for (String res : result) {
            System.out.println(res);
        }
    }
}
