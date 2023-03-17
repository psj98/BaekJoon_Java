package study_230315.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1233 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[31]; // dp �迭 �ʱ�ȭ
        dp[0] = 1; // ������ �� 0�� �ȳ����� �ϱ� ����
        dp[2] = 3;

        // f(n) = f(n - 2) * 3 + sum((f(n - 4) ~ f(0)) * 2)
        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = i - 4; j >= 0; j--)
                dp[i] += (dp[j] * 2);
        }

        // ���� ���
        sb.append(dp[n]);
        System.out.println(sb);
    }
}