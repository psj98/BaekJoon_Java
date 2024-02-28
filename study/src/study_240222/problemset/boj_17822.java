package study_240222.problemset;

import java.io.*;
import java.util.*;

public class boj_17822 {
    static int n, m;
    static int[][] map;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static boolean check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        int t = Integer.parseInt(stk.nextToken());

        map = new int[n][m];

        // �� ����
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // ȸ�� Ƚ��
        for (int tc = 0; tc < t; tc++) {
            stk = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stk.nextToken()); // ���
            int d = Integer.parseInt(stk.nextToken()); // 0 : �ð�, 1 : �ݽð�
            int k = Integer.parseInt(stk.nextToken()); // ĭ ��

            // �ݽð��� ���, �ð� �������� ����
            if (d == 1) {
                k = m - k;
            }

            rotate(x, k); // ȸ��

            check = false; // ���� ���� üũ
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0) {
                        continue;
                    }

                    bfs(i, j); // ���� �� ����
                }
            }

            // �������� �ִ� ���, ��ŵ
            if (check) {
                continue;
            }

            avg(); // ���� ���, ��հ� ���ϱ�
        }

        int sum = 0; // ����
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += map[i][j];
            }
        }

        // ���� ���
        sb.append(sum);
        System.out.println(sb);
    }

    // ȸ��
    static void rotate(int x, int k) {
        // Queue�� �����Ͽ� ������� ����
        for (int i = x - 1; i < n; i += x) {
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            for (int j = m - k; j < m; j++) {
                queue.add(map[i][j]);
            }

            for (int j = 0; j < m - k; j++) {
                queue.add(map[i][j]);
            }

            for (int j = 0; j < m; j++) {
                map[i][j] = queue.poll();
            }
        }
    }

    // BFS
    static void bfs(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        int cnt = 1; // ������ �� ����

        queue.add(new int[] { x, y });
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = (cur[1] + dy[i] + m) % m;

                // ���� üũ => ������ ���, �� ���� �̾���
                if (nx < 0 || nx >= n) {
                    continue;
                }

                // �湮 üũ
                if (visited[nx][ny]) {
                    continue;
                }

                // �ٸ� ���, ��ŵ
                if (map[x][y] != map[nx][ny]) {
                    continue;
                }

                queue.add(new int[] { nx, ny });
                visited[nx][ny] = true;
                cnt++;
            }
        }

        // �������� ���� ���,
        if (cnt == 1) {
            return;
        }

        check = true; // �������� �ִ� ���

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    continue;
                }

                map[i][j] = 0; // 0���� ����
            }
        }
    }

    // ��հ� �������� �� ����
    static void avg() {
        double sum = 0, cnt = 0;

        // ��հ� ���ϱ�
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                sum += map[i][j];
                cnt++;
            }
        }

        sum /= cnt;

        // �� ����
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                if (map[i][j] > sum) { // ū ��� -1
                    map[i][j]--;
                } else if (map[i][j] < sum) { // ���� ��� +1
                    map[i][j]++;
                }
            }
        }
    }
}
