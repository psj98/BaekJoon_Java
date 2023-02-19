package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// dfs
public class boj_1240_2 {
    static ArrayList<Node>[] map; // ����� ��峢���� ��ǥ �� ��� ����
    static int ans;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        map = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++)
            map[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int value = Integer.parseInt(stk.nextToken());

            map[x].add(new Node(y, value));
            map[y].add(new Node(x, value));
        }

        // Ž��
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());

            dfs(x, y, -1, 0);

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    // �ּ� ��� Ž�� (������ �湮�� ���� Ž�� X)
    public static void dfs(int s, int e, int prev, int cost) {
        if (s == e) {
            ans = cost;
        }

        for (Node node : map[e]) {
            if (node.dest != prev) {
                dfs(s, node.dest, e, cost + node.cost);
            }
        }
    }

    static class Node {
        int dest;
        int cost;

        Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
