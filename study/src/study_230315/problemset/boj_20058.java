package study_230315.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_20058 {
    static int n;
    static int[][] map; // �� ����
    static int[] dx = { 1, -1, 0, 0 }; // ���� ����
    static int[] dy = { 0, 0, 1, -1 };
    static boolean[][] visited; // �湮 üũ

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        int q = Integer.parseInt(stk.nextToken());

        n = (int) Math.pow(2, n); // �� ũ��

        // �ʱ�ȭ
        visited = new boolean[n][n];
        map = new int[n][n];

        // �� ���� ����
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        // �ش� ������ŭ ȸ�� ��, ���� ���̱�
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            int num = Integer.parseInt(stk.nextToken());
            rotate((int) Math.pow(2, num), 0, 0, n); // ȸ��
            eraseIce(); // ���� ���̱�
        }

        // ������ ��, ���� ��� ã��
        int sum = 0, maxIce = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                    maxIce = Math.max(maxIce, findIce(i, j));
                }
            }
        }

        // ���� ���
        sb.append(sum).append("\n").append(maxIce);
        System.out.println(sb);
    }

    // ���� ���� + ȸ��
    static void rotate(int r, int x, int y, int size) {
        if (size == r) { // size�� r�̶� ��������, ȸ��
            int[][] copyMap = new int[size][size]; // ȸ�� �迭 ������ copyMap
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    copyMap[i][j] = map[x + size - j - 1][y + i];

            // ȸ���� �迭�� map�� ����
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    map[x + i][y + j] = copyMap[i][j];

            return;
        }

        int newSize = size / 2;

        // ���� ���� ��ǥ
        for (int i = x; i < x + size; i += newSize)
            for (int j = y; j < y + size; j += newSize)
                rotate(r, i, j, newSize);
    }

    // ���� ���̱�
    static void eraseIce() {
        int cnt; // �ֺ��� ���� 3�� �̻�, ������ ĭ üũ�� ����
        int[][] copyMap = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                copyMap[i][j] = map[i][j];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt = 0; // �ʱ�ȭ
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    // ���� üũ
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        cnt++;
                        continue;
                    }

                    // ������ ���� ���
                    if (copyMap[nx][ny] == 0)
                        cnt++;
                }

                if (cnt >= 2 && map[i][j] > 0) // cnt�� 2�� �̻��̸� -> ���� ���̱�
                    map[i][j]--;
            }
        }
    }

    // ���� ��� ã�� - BFS
    static int findIce(int x, int y) {
        int cnt = 1;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { x, y });

        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int curX = queue.peek()[0];
            int curY = queue.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (visited[nx][ny])
                        continue;
                    if (map[nx][ny] == 0)
                        continue;

                    queue.add(new int[] { nx, ny });
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
