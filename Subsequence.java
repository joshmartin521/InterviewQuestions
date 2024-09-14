import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 1, 5, 3, 7, 2};
        int result = subsequence(arr);
        System.out.println(result);
    }

    public static int subsequence(int[] arr) {
        int n = arr.length;

        if (n < 3) {
            return 0;  // Not enough elements for a valid subsequence
        }

        // Array to store the smallest element to the left of each element
        int[] leftMin = new int[n];
        leftMin[0] = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], arr[i - 1]);
        }

        // Array to store the largest element to the right of each element
        int[] rightMax = new int[n];
        rightMax[n - 1] = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i + 1]);
        }

        // Now check if there is a valid subsequence
        for (int j = 1; j < n - 1; j++) {
            if (leftMin[j] < arr[j] && arr[j] < rightMax[j]) {
                return 1;  // Valid subsequence found
            }
        }

        return 0;  // No valid subsequence found
    }
}
