package org.example.solution2;

public class Solution {
    private int answer;

    public int count(int sum, int[] coins) {
        answer = 0;
        count(sum, coins, 0, 0);
        return answer;
    }

    private void count(int sum, int[] coins, int currentSum, int index) {
        if (sum < currentSum) return;
        if (sum == currentSum) {
            answer++;
            return;
        }

        for (int i = index; i < coins.length; i++) {
            count(sum, coins, currentSum + coins[i], i);
        }
    }
}