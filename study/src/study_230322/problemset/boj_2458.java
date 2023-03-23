package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2458 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int ans = 0;
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] check = new int[n + 1];
        boolean[][] map = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            map[from][to] = true;
        }

        // �÷��̵� ���� -> �� �� �ִ� ���� üũ
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] && map[k][j])
                        map[i][j] = true;
                }
            }
        }

        // �ش� �������� ������ ���� ������ �� �� �ϳ��� ������ +1
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] || map[j][i]) {
                    check[i]++;
                    // check[j]++;
                }
            }
        }

        // �ڽ��� ������ ���� (n-1) �̸� ans++
        for (int i = 1; i <= n; i++)
            if (check[i] == n - 1)
                ans++;

        sb.append(ans);
        System.out.println(sb);
    }
}