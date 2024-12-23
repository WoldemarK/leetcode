package org.example;

public class DuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        int result = removeDuplicates(nums);
        System.out.println(result);
    }

    public static int removeDuplicates(int[] nums) {
        int k = 0;
        for (int i : nums) {
            if (k == 0 || i != nums[k - 1]) {
                nums[k++] = i;
            }
        }
        return k;
    }
}
