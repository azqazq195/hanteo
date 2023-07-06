package org.example.solution2;

public class TestCase {
    private final int sum;
    private final int[] coins;
    private final int answer;

    public TestCase(int sum, int[] coins, int answer) {
        this.sum = sum;
        this.coins = coins;
        this.answer = answer;
    }

    public int getSum() {
        return sum;
    }

    public int[] getCoins() {
        return coins;
    }

    public int getAnswer() {
        return answer;
    }
}