package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs
public class boj_1240_4 {
    static ArrayList<int[]>[] pos; // ����� ��峢���� ��ǥ �� ��� ����
    static int ans;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        pos = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            pos[i] = new ArrayList<>();
        }

        // ��ǥ ����
        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int value = Integer.parseInt(stk.nextToken());

            pos[x].add(new int[] { y, value });
            pos[y].add(new int[] { x, value });
        }

        // Ž��
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());

            bfs(x, y, n);

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    // �ּ� ��� Ž�� (������ �湮�� ���� Ž�� X)
    public static void bfs(int start, int end, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] cost = new int[n + 1];
        queue.add(start);

        while (!queue.isEmpty()) {
            int num = queue.poll();

            for (int i = 0; i < pos[num].size(); i++) {
                if (!visited[pos[num].get(i)[0]]) {
                    cost[pos[num].get(i)[0]] = pos[num].get(i)[1] + cost[num]; // ��� ����

                    if (pos[num].get(i)[0] == end) {
                        ans = cost[pos[num].get(i)[0]];
                        return;
                    }

                    queue.add(pos[num].get(i)[0]);
                    visited[pos[num].get(i)[0]] = true;
                }
            }
        }
    }
}
