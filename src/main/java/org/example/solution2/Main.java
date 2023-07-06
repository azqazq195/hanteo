package org.example.solution2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    final static Solution solution = new Solution();

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(4, new int[]{1, 2, 3}, 4));
        testCases.add(new TestCase(10, new int[]{2, 5, 3, 6}, 5));
        testCases.add(new TestCase(1, new int[]{2, 5, 3, 6}, 0));
        testCases.add(new TestCase(2, new int[]{2, 5, 3, 6}, 1));
        testCases.add(new TestCase(10, new int[]{11}, 0));

        for (int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            int sum = testCase.getSum();
            int[] coins = testCase.getCoins();
            int answer = testCase.getAnswer();

            int result = solution.count(sum, coins);
            PRINT_RESULT(i + 1, answer, result);
        }
    }

    public static void PRINT_RESULT(int index, int answer, int result) {
        boolean correct = result == (answer);
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n테스트 케이스 ").append(index).append(": ");
        sb.append(correct ? "정답" : "오답").append("\n");
        sb.append("\t- 실행 결과: \t").append(result).append("\n");
        sb.append("\t- 기댓값: \t").append(answer).append("\n");
        if (correct) System.out.println(sb);
        else throw new RuntimeException(sb.toString());
    }
}
