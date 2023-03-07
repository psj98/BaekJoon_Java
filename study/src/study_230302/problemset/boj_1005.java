package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1005 {
    static int[] value, indegree; // ���������� �Ǽ� �ð�, �ش� ������ ȭ��ǥ�� �޴� ����
    static ArrayList<Integer>[] map; // ���� ����

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            stk = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(stk.nextToken());
            int k = Integer.parseInt(stk.nextToken());

            // �ʱ�ȭ
            indegree = new int[n + 1];
            value = new int[n + 1];
            map = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++)
                map[i] = new ArrayList<>();

            // ���������� �Ǽ� �ð� ����
            stk = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++)
                value[i] = Integer.parseInt(stk.nextToken());

            // ���� ���� ���� �� ȭ��ǥ �޴� ���� ����
            for (int i = 0; i < k; i++) {
                stk = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(stk.nextToken());
                int end = Integer.parseInt(stk.nextToken());
                map[start].add(end);
                indegree[end]++;
            }

            int w = Integer.parseInt(br.readLine()); // ����ؾ��ϴ� ����

            sb.append(topology(n, w)).append("\n"); // ���� ���
        }

        System.out.println(sb);
    }

    // ���� ����
    static int topology(int n, int end) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] ans = new int[n + 1]; // ���� �迭

        // ȭ��ǥ�� �޴� ������ 0�� ������ queue�� ����
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                ans[i] = value[i]; // �ش� ������ ���� value�� �ʱ�ȭ
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : map[cur]) {
                if (--indegree[next] == 0) // �ش� ������ ȭ��ǥ�� ��� �޾����� �� �������� �ٸ� �������� ��� ����
                    queue.offer(next);
                ans[next] = Math.max(ans[next], value[next] + ans[cur]); // �ִ� ����
            }
        }

        return ans[end]; // ����
    }
}
