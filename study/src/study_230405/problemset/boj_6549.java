package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_6549 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        while (true) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            if (n == 0)
                break;

            Stack<Integer> stack = new Stack<>();
            long ans = 0;

            // ���� ����
            long[] height = new long[n + 2];
            for (int i = 1; i <= n; i++)
                height[i] = Long.parseLong(stk.nextToken());

            // ������ ������ ����ϱ� ���� n+1���� ����
            for (int i = 1; i <= n + 1; i++) {
                // ���� ���� ���� ������ ���� ���
                while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                    // ���� ���� �����ͼ� �ʺ�� ����
                    long area = 0;
                    long pre = height[stack.pop()]; // ���� ����

                    if (stack.isEmpty()) { // Stack�� �� ���, �ʺ�� �� ó������ �������
                        area = (i - 1) * pre;
                    } else { // Stack�� ���� �ִ� ���, �ʺ�� �ش� ������ �������
                        area = (i - stack.peek() - 1) * pre;
                    }

                    ans = Math.max(area, ans); // �ִ� ����
                }

                stack.add(i); // index ���� (�ʺ� �˱� ����)
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
