package study_230315.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_12738 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // �Է� �� �ʱ�ȭ
        int n = Integer.parseInt(br.readLine());
        int[] cost = new int[n];
        ArrayList<Integer> dp = new ArrayList<>();

        // �� ����
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            cost[i] = Integer.parseInt(stk.nextToken());

        dp.add(cost[0]); // ���� ���� ������ ���̴� ������ 1 �̻�

        // �� ���� ������ �̺�Ž��
        for (int i = 1; i < n; i++) {
            // ���� ���� dp�� ������ ������ ũ�� �ڿ� �� ����
            if (dp.get(dp.size() - 1) < cost[i])
                dp.add(cost[i]);
            else { // �̺� Ž�� - �߰��� �� ����
                int left = 0, right = dp.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;
                    if (cost[i] > dp.get(mid)) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                dp.set(right, cost[i]); // �� ����
            }
        }

        // ���
        sb.append(dp.size());
        System.out.println(sb);
    }
}
