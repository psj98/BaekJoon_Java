package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_21610 {
    static int n, m;
    static int[][] map;
    static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static boolean[][] visited;
    static Queue<int[]> cloud = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        // ���� �ʱ� ��ǥ ����
        for (int i = 0; i <= 1; i++) {
            cloud.add(new int[] { n - 1, i });
            cloud.add(new int[] { n - 2, i });
        }

        // ���� �����̸鼭 �� �� ���ϱ�
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(stk.nextToken()) - 1;
            int cnt = Integer.parseInt(stk.nextToken());
            moveCloud(dir, cnt);
            copyWater();
            makeCloud();
        }

        // �� �� ���ϱ�
        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                sum += map[i][j];

        sb.append(sum);
        System.out.println(sb);
    }

    // ���� �̵� �� �ٱ��� �� �� ����
    static void moveCloud(int dir, int cnt) {
        for (int[] cur : cloud) {
            // ���� ��ȯ �Ѳ�����
            cur[0] = (cur[0] + n + dx[dir] * (cnt % n)) % n;
            cur[1] = (cur[1] + n + dy[dir] * (cnt % n)) % n;
            map[cur[0]][cur[1]]++;
        }
    }

    // �� ����
    static void copyWater() {
        visited = new boolean[n][n]; // ������ ������� ��ġ

        while (!cloud.isEmpty()) {
            int[] pos = cloud.poll();
            int cnt = 0; // �밢���� ��ġ�� �� �ִ� �ٱ��� ����

            for (int i = 1; i < 8; i += 2) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] > 0)
                    cnt++; // ���� ����
            }

            map[pos[0]][pos[1]] += cnt; // �ش� �ٱ��� ������ŭ ����
            visited[pos[0]][pos[1]] = true; // ������ ������� ��ġ üũ
        }
    }

    // ���� �����
    static void makeCloud() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // ������ ����� ��ġ�� �ƴϰ� ���� ���� 2 �̻��̸� ���� ����
                if (!visited[i][j] && map[i][j] >= 2) {
                    cloud.add(new int[] { i, j });
                    map[i][j] -= 2; // �� �� - 2
                }
            }
        }
    }
}