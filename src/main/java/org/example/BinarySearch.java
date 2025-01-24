package org.example;

import java.util.stream.IntStream;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;

        int result = binarySearch2(nums, target);
        System.out.println(result);


    }

    public static int binarySearch(int[] nums, int target) {
        int firstIndex = 0;
        int lastIndex = nums.length - 1;
        while (firstIndex <= lastIndex) {
            int mid = (firstIndex + lastIndex) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target)
                firstIndex = mid + 1;
            else if (nums[mid] > target)
                lastIndex = mid - 1;

        }
        return -1;
    }

    public static int binarySearch2(int[] nums, int target) {
        return IntStream.range(0, nums.length)
                .filter(element -> nums[element] == target)
                .findFirst()
                .orElse(-1);
    }

}
