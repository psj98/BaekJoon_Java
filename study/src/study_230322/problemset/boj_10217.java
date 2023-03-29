package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_10217 {
    static int n, m, k;
    static ArrayList<KCM>[] map;
    static int[][] dp;

    static class KCM implements Comparable<KCM> {
        int end, cost, time; // ���� ���, ���, �ð�

        KCM(int end, int cost, int time) {
            this.end = end;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(KCM o) { // �ð� �������� ����
            if (this.time == o.time)
                return this.cost - o.cost;
            return this.time - o.time;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            stk = new StringTokenizer(br.readLine());
            n = Integer.parseInt(stk.nextToken());
            m = Integer.parseInt(stk.nextToken());
            k = Integer.parseInt(stk.nextToken());

            // �ʱ�ȭ
            map = new ArrayList[n + 1];
            dp = new int[n + 1][m + 1]; // ���� ���� ���� �ð�
            for (int i = 0; i <= n; i++) {
                map[i] = new ArrayList<>();
                Arrays.fill(dp[i], Integer.MAX_VALUE); // max������ �ʱ�ȭ
            }

            // ����, ����, ���, �ð� �Է�
            for (int i = 0; i < k; i++) {
                stk = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(stk.nextToken());
                int end = Integer.parseInt(stk.nextToken());
                int cost = Integer.parseInt(stk.nextToken());
                int time = Integer.parseInt(stk.nextToken());

                map[start].add(new KCM(end, cost, time));
            }

            Dijkstra(); // Dijkstra

            int ans = Integer.MAX_VALUE;
            for (int i = 0; i <= m; i++)
                ans = Math.min(ans, dp[n][i]); // �ּڰ� ã��

            // ���� ���
            if (ans == Integer.MAX_VALUE)
                sb.append("Poor KCM\n");
            else
                sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    // ���ͽ�Ʈ��
    static void Dijkstra() {
        PriorityQueue<KCM> pq = new PriorityQueue<>();
        pq.add(new KCM(1, 0, 0)); // ���� ��ġ (1�� ���, ��� 0, �ð� 0)

        while (!pq.isEmpty()) {
            KCM cur = pq.poll(); // ���� KCM

            if (cur.end == n) // ����
                break;

            if (dp[cur.end][cur.cost] < cur.time) // �ð� ����
                continue;
            dp[cur.end][cur.cost] = cur.time; // ���� �ð� ����

            for (KCM next : map[cur.end]) {
                int nextNode = next.end;
                int nextCost = cur.cost + next.cost;
                int nextTime = cur.time + next.time;

                if (nextCost > m) // ���� ����� m���� ũ�� �� �ʿ� ����
                    continue;

                // ������ �� ���� �ð��� ������ ���� -> pq�� ����
                if (dp[nextNode][nextCost] > nextTime) {
                    dp[nextNode][nextCost] = nextTime;
                    pq.add(new KCM(nextNode, nextCost, nextTime));
                }
            }
        }
    }
}

