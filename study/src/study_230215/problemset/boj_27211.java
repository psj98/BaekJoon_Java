package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_27211 {
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int[][] map;
    static int n, m, cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][m];

        // ��ǥ ���� (0, 1)
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        // ��ǥ�� 0�� �κп� ���� bfs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    bfs(i, j);
                    cnt++; // bfs ������ (���� 1�� Ȯ��)
                }
            }
        }

        sb.append(cnt);
        System.out.println(sb);
    }

    // 0�� ��ǥ Ž�� ��, 2�� ����
    public static void bfs(int a, int b) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { a, b });
        map[a][b] = 2;

        // 0�� �ȳ��� ������ Ž��
        while (!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // ���ʿ��� ���������� or ������ �Ʒ��� �Ѿ�� ���, �� �������� �� ����
                if (nx < 0)
                    nx = n - 1;
                if (ny < 0)
                    ny = m - 1;

                // ���� ������ ����� ��� ����
                nx %= n;
                ny %= m;

                if (map[nx][ny] == 0) {
                    map[nx][ny] = 2; // �湮 ǥ��
                    queue.add(new int[] { nx, ny });
                }
            }
        }
    }
}
