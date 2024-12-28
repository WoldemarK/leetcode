package org.example;

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {89, 57, 91, 47, 95, 3, 27, 22, 67, 99};
        int target = 47;

        int result = binarySearch(array, target);
        System.out.println(result);


    }

    public static int binarySearch(int[] nums, int target) {
        int firstIndex = 0;                          // 0                                             || 6
        int lastIndex = nums.length;                 // 10                                            || 10
        //                                               ||
        while (firstIndex <= lastIndex) {            // 0 < == 10          Yes                        || 6 < = 10  Yes
            int mid = (firstIndex + lastIndex) / 2;  // mid = 0+10=10, 10/2 =5;     index 5 = 3       || 6 + 10 = 16, 16 \ 2 =8; index 8 = 67
            if (nums[mid] == target) {               //3 == 47 No                                     ||
                return mid;                          //         -----                                 || -------
            } else if (nums[mid] < target)           //3 element < = 47                               ||
                firstIndex = mid + 1;                //firstIndex = mid + 1; first(0)->(6)= mid(5)+1=6||
            else if (nums[mid] > target)             //                                               ||
                lastIndex = mid - 1;                 //                                               ||

        }
        return -1;
    }

}
