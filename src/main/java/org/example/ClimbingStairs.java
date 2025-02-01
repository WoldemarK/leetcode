package org.example;

public class ClimbingStairs {
    public static void main(String[] args) {
        ClimbingStairs cs = new ClimbingStairs();
        //System.out.println(cs.climbStairs(2)); // Вывод: 2
        System.out.println(cs.climbStairs(3)); // Вывод: 3
        //System.out.println(cs.climbStairs(4)); // Вывод: 5
    }

    public int climbStairs(int n) {
        if (n <= 2) return n;
        int prev1 = 1;
        int prev2 = 2;
        for (int i = 3; i <= n; i++) {
            int cur = prev1 + prev2;
            prev1 = prev2;
            prev2 = cur;
        }
        return prev2;
    }
}
