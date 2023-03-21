package study_230315.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_14002 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // �Է� �� �ʱ�ȭ
        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n];
        int[] dp = new int[n];
        dp[0] = 1;

        // �� ����
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            cost[i] = Integer.parseInt(stk.nextToken());

        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (cost[i] > cost[j])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }

            ans = Math.max(ans, dp[i]); // dp�� �ִ� -> ���� ���� ���� ����
        }

        Stack<Integer> stack = new Stack<>();
        
        // �տ������� dp[]�� ����� ���� 1, 2 ~ ans �����̱� ������ �Ųٷ� �޾ƿ;� ��
        for (int i = n - 1; i >= 0; i--) {
            if (ans == dp[i]) { // ans�� ������ ���ÿ� �߰�
                stack.add(cost[i]);
                ans--;
            }
        }

        // ���� ���
        sb.append(stack.size()).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
