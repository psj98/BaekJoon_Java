package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// dfs
public class boj_1240_3 {
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

            dfs(x, y, 0, -1);

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    // �ּ� ��� Ž�� (������ �湮�� ���� Ž�� X)
    public static void dfs(int start, int end, int cost, int previousIdx) {
        if (start == end) {
            ans = cost;
        }

        for (int i = 0; i < pos[start].size(); i++) {
            if (pos[start].get(i)[0] != previousIdx) {
                dfs(pos[start].get(i)[0], end, cost + pos[start].get(i)[1], start);
            }
        }
    }
}
