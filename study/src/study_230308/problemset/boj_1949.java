package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1949 {
    static int n;
    static ArrayList<Integer>[] node;
    static int[][] dp;
    static boolean[] visited;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        // �ʱ�ȭ
        visited = new boolean[n + 1];
        dp = new int[2][n + 1];
        node = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            node[i] = new ArrayList<>();

        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            dp[1][i] = Integer.parseInt(stk.nextToken()); // ���� ������ ��� ���� (��� ������ ���)

        // ���� ���� ���� ����
        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            node[start].add(end);
            node[end].add(start);
        }

        dfs(1); // dfs

        sb.append(Math.max(dp[0][1], dp[1][1]));
        System.out.println(sb);
    }

    // ���� ������ dfs
    static void dfs(int cur) {
        visited[cur] = true; // ���� ��� �湮

        // ������ �� ��尡 ���� ���
        for (int next : node[cur]) {
            if (!visited[next]) { // �湮���� ���� ���, dfs(next)
                dfs(next);

                dp[0][cur] += Math.max(dp[1][next], dp[0][next]); // �Ϲ� ���� -> ������ �Ϲ� �Ǵ� ��� ����
                dp[1][cur] += dp[0][next]; // ��� ���� -> ������ ������ �Ϲ� ����
            }
        }
    }
}
