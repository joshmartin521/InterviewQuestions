import java.util.*;
public class Main {
    public static void main(String[] args) {
        int [] nums = {-1,2,3};
        Arrays.sort(nums);
        int sum = 0;
        int target = 4;

        int start = -1;
        int current = 0;
        boolean found = false;

        while(current < nums.length && start < current && !found) {
            if(sum < target)
            {
                sum += nums[current];
                current++;
            }
            if(sum > target){
                start++;
                sum-=nums[start];

            }
            if(sum == target)
            {
                found = true;
            }
        }

        System.out.println(found);
    }
}
