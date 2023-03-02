package study_230302;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1238 {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayList<int[]>[] homeMap, partyMap; // ������, ��Ƽ��
        int[] tohome, toparty; // ������ �ִ� ���, ��Ƽ�� �ִ� ���
        int ans = 0; // ����

        // �Է�
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int x = Integer.parseInt(stk.nextToken());

        // �ʱ�ȭ
        homeMap = new ArrayList[n + 1];
        partyMap = new ArrayList[n + 1];
        tohome = new int[n + 1];
        toparty = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            homeMap[i] = new ArrayList<>();
            partyMap[i] = new ArrayList<>();
        }

        Arrays.fill(tohome, Integer.MAX_VALUE);
        Arrays.fill(toparty, Integer.MAX_VALUE);

        // �� ���� ����
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());
            homeMap[start].add(new int[] { end, cost }); // start -> end
            partyMap[end].add(new int[] { start, cost }); // end -> start
        }

        // ���ͽ�Ʈ�� �˰���
        tohome = Dijkstra(homeMap, n, x, tohome);
        toparty = Dijkstra(partyMap, n, x, toparty);

        // �ּڰ� ���ϱ�
        for (int i = 1; i <= n; i++)
            ans = Math.max(ans, tohome[i] + toparty[i]);

        sb.append(ans);
        System.out.println(sb);
    }

    // ���ͽ�Ʈ�� �˰���
    static int[] Dijkstra(ArrayList<int[]>[] map, int V, int start, int[] distance) {
        boolean[] visited = new boolean[V + 1]; // �湮 �迭
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; // ��� �� ����
            }
        });

        pq.add(new int[] { start, 0 }); // ���� ����, ��� 0
        distance[start] = 0; // �湮 üũ

        while (!pq.isEmpty()) {
            int node = pq.poll()[0]; // ���� ����
            visited[node] = true; // �湮 üũ

            for (int[] next : map[node]) {
                if (visited[next[0]]) // �湮������ �湮�� �ʿ� ����
                    continue;

                // �ִ� �Ÿ�
                if (distance[next[0]] > distance[node] + next[1]) {
                    distance[next[0]] = distance[node] + next[1];
                    pq.offer(new int[] { next[0], distance[next[0]] });
                }
            }
        }

        return distance;
    }
}
