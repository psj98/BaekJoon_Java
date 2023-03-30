package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_14003 {
    static int[] num, dp, index;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        num = new int[n + 1]; // �� ���� �迭
        dp = new int[n + 1]; // ���� ���� �迭
        index = new int[n + 1]; // ��ġ�� ������ �迭

        // �� �Է� �� ����
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            num[i] = Integer.parseInt(stk.nextToken());

        int idx = 1; // ���� ��ġ
        dp = new int[n + 1];
        dp[1] = num[1];
        index[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (dp[idx] < num[i]) {
                dp[++idx] = num[i];
                index[i] = idx;
            } else {
                int low = lowerBound(1, idx, num[i]); // �̺� Ž��
                dp[low] = num[i];
                index[i] = low; // i��° �迭�� idx ����
            }
        }

        sb.append(idx).append("\n"); // ���� ���

        // �κ� ���� ã�� -> idx���� �Ųٷ� �̵��ϸ鼭 stack�� ����
        Stack<Integer> stack = new Stack<>();
        for (int i = n; i > 0; i--) {
            if (index[i] == idx) {
                stack.add(num[i]);
                idx--;
            }
        }

        // �κ� ���� ���
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        // ���� ���
        System.out.println(sb);
    }

    static int lowerBound(int left, int right, int num) {
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (num <= dp[mid])
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}
