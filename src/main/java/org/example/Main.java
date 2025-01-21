package org.example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 128;
        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));

    }

    public static int[] twoSum(int[] nums, int target) {
        return IntStream.range(0, nums.length - 1)
                .filter(i -> target == nums[i] + nums[i + 1])
                .mapToObj(i->new int[] {i, i+1})
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Нет решения для target " + target));
}
}
