package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_15681 {
    static int n;
    static ArrayList<Integer>[] node;
    static int[] dp;
    static boolean[] visited;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        int r = Integer.parseInt(stk.nextToken());
        int q = Integer.parseInt(stk.nextToken());

        // �ʱ�ȭ
        node = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            node[i] = new ArrayList<>();

        visited = new boolean[n + 1];
        dp = new int[n + 1];
        Arrays.fill(dp, 1); // 1�� �ʱ�ȭ (���� ��� -> 1)

        // ���� ���� ���� ����
        for (int i = 0; i < n - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            node[start].add(end);
            node[end].add(start);
        }

        dfs(r); // dfs

        // ���
        for (int i = 0; i < q; i++)
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");

        System.out.println(sb);
    }

    // ���� ������ dfs
    static int dfs(int cur) {
        visited[cur] = true; // ���� ��� �湮

        // ������ �� ��尡 ���� ���
        for (int next : node[cur])
            if (!visited[next]) // �湮���� ���� ���, ���� dp�� dfs(next) ���� ����
                dp[cur] += dfs(next);

        return dp[cur]; // ���� ���, ���� dp �� return
    }
}
