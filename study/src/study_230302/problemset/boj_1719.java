package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1719 {
    static ArrayList<int[]>[] map; // �� ����
    static int[][] ans; // ���� �迭

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // �Է�
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        // �ʱ�ȭ
        map = new ArrayList[n + 1];
        ans = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++)
            map[i] = new ArrayList<>();

        // �ʿ� ����
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            map[start].add(new int[] { end, cost });
            map[end].add(new int[] { start, cost });
        }

        // �� ���� ���� Dijkstra
        for (int i = 1; i <= n; i++)
            Dijkstra(n, i);

        // ���
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (ans[i][j] == i)
                    sb.append("- ");
                else
                    sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // Dijkstra
    static void Dijkstra(int V, int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; // ��� �������� ����
            }
        });
        int[] distance = new int[V + 1]; // �Ÿ�
        int[] parents = new int[V + 1]; // ���İ��� �ϴ� ����
        boolean[] visited = new boolean[V + 1]; // �湮 �迭

        Arrays.fill(distance, Integer.MAX_VALUE); // �ִ����� ����
        distance[start] = 0; // ���� ���� 0���� ����
        parents[start] = start; // ���� �������δ� �湮���� �ʱ� ������ start�� �ʱ�ȭ

        pq.add(new int[] { start, 0 }); // ���� ����, ��� ����

        while (!pq.isEmpty()) {
            int cur = pq.poll()[0]; // ���� ����

            visited[cur] = true; // �湮 üũ

            for (int[] next : map[cur]) {
                if (visited[next[0]]) // �湮������ �湮�� �ʿ� ����
                    continue;

                // �Ÿ� ����
                if (distance[next[0]] > distance[cur] + next[1]) {
                    distance[next[0]] = distance[cur] + next[1];
                    pq.add(new int[] { next[0], distance[next[0]] });
                    parents[next[0]] = cur; // ���İ��� �ϴ� ���� ����
                }
            }
        }

        // ans�� ���� ����
        for (int i = 1; i <= V; i++)
            ans[i][start] = parents[i];
    }
}
