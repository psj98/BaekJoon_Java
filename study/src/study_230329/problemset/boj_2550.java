package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_2550 {
    static int[] index, dp;
    static Node[] num;

    static class Node {
        int num, pos; // ��, ��ġ

        Node(int num, int pos) {
            this.num = num;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        index = new int[n + 1];
        dp = new int[n + 1];
        num = new Node[n + 1];

        // ���� ��ġ�� index�� ��ġ�� i������ ����
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int cur = Integer.parseInt(stk.nextToken());
            index[cur] = i;
        }

        // num�� ���� ���� index[��]�� ��ġ�� ����
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int cur = Integer.parseInt(stk.nextToken());
            num[i] = new Node(cur, index[cur]);
        }

        int idx = 1; // �ʱⰪ
        dp[1] = num[1].pos;
        index[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (dp[idx] < num[i].pos) { // dp ���� ���� ���
                dp[++idx] = num[i].pos; // ���� ���� dp ���� ���� ����
                index[i] = idx; // idx ����
            } else { // dp ���� Ŭ ���
                int low = lowerbound(1, idx, num[i].pos); // ���� ��ġ ã��
                dp[low] = num[i].pos; // �ش� ��ġ�� num�� ��ġ ����
                index[i] = low; // idx(low) ����
            }
        }

        sb.append(idx).append("\n"); // ����ġ �� ���

        ArrayList<Integer> list = new ArrayList<>(); // �κ� ���� ���� ����
        for (int i = n; i > 0; i--) {
            if (index[i] == idx) {
                list.add(num[i].num);
                idx--;
            }
        }

        Collections.sort(list); // �������� ����

        // ���� ���
        for (int i = 0; i < list.size(); i++)
            sb.append(list.get(i)).append(" ");

        System.out.println(sb);
    }

    // binary search -> ���� ���� ��ġ ã��
    static int lowerbound(int left, int right, int num) {
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (dp[mid] >= num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
