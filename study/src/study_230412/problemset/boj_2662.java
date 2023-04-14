package study_230412.problemset;

import java.io.*;
import java.util.*;

public class boj_2662 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        // i������ �������� �� j����� ��� ����
        int[][] investment = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        int[][] company = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            stk.nextToken();
            for (int j = 1; j <= m; j++)
                investment[i][j] = Integer.parseInt(stk.nextToken());
        }

        // DP ���
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) { // n���� ����
                for (int k = 0; k <= j; k++) {
                    // i����� j������ ������ ���� = ���� ������� j-k������ ������ ���� + i����� k������ ������ ����
                    if (dp[j][i] < dp[j - k][i - 1] + investment[k][i]) {
                        dp[j][i] = dp[j - k][i - 1] + investment[k][i];
                        company[j][i] = k; // ���� ������� k���� ���� -> ���鼭 ã��?
                    }
                }
            }
        }

        int ans = dp[n][m]; // �ִ� ����
        sb.append(ans).append("\n");

        Stack<Integer> stack = new Stack<>();
        while (m > 0) {
            int invest = company[n][m]; // ���� ������� ������ �׼�
            stack.add(invest);

            n -= invest; // ���� �׼� - ������ �׼� = ���� ����� ������ �׼�
            m--; // ���� ���
        }

        // ���ÿ��� ���鼭 ������� ���
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
