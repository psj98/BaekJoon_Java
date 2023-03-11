package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_1562 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int sum = 0;
        int[][][] dp = new int[n + 1][10][1024]; // n �ڸ���, ������ �ڸ� ��, � ���ڰ� ����ִ���

        // �� �ڸ����� �� -> ������ �ڸ� ���� ���� ��Ʈ����ŷ (1�� 1, 2�� 2, 3�� 4, ...)
        for (int i = 1; i < 10; i++)
            dp[1][i][1 << i] = 1;

        // ���� Ž��
        // 2�ڸ��� - ������ �ڸ� 5 (j) -> 45, 65 - �̿� ���� ��Ʈ����ŷ (���� ��Ʈ����ŷ ��(k) | (1 << j))
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j); // ������ �� ��Ʈ ��

                    if (j == 0) // ������ �ڸ��� 0�� ���, ���� �ڸ��� 1�� ���� ����
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][1][k]) % 1000000000;
                    else if (j == 9) // ������ �ڸ��� 9�� ���, ���� �ڸ��� 8�� ���� ����
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][8][k]) % 1000000000;
                    else // �������� j - 1 �̳� j + 1 �� �� ����
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % 1000000000;
                }
            }
        }

        // n�ڸ��� -> 0 ~ 9���� �� �ִ� ���
        for (int i = 0; i < 10; i++)
            sum = (sum + dp[n][i][1023]) % 1000000000;

        sb.append(sum);
        System.out.println(sb);
    }
}
