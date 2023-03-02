package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14502 {
    static int n, m, ans = Integer.MIN_VALUE;
    static int[][] map;
    static int[] posIdx = new int[3];
    static ArrayList<int[]> virus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                // ���̷��� ��ǥ ����
                if (map[i][j] == 2)
                    virus.add(new int[] { i, j });
            }
        }

        comb(0, 0); // ���� ã��

        sb.append(ans);
        System.out.println(sb);
    }

    // ���� ã��
    static void comb(int cnt, int idx) {
        // �� 3���� ã����
        if (cnt == 3) {
            int[][] copyMap = new int[n][m]; // ���� �� ������ �迭
            for (int i = 0; i < n; i++)
                copyMap[i] = Arrays.copyOf(map[i], m);

            // �� �����
            for (int i = 0; i < 3; i++) {
                int x = posIdx[i] / m;
                int y = posIdx[i] % m;
                copyMap[x][y] = 1;
            }

            // ���̷��� �۶߸���
            for (int i = 0; i < virus.size(); i++)
                bfs(virus.get(i)[0], virus.get(i)[1], copyMap);

            ans = Math.max(ans, cntzero(copyMap)); // ���� ���� ���� �ִ� ����

            return;
        }

        // ���� ã��
        for (int i = idx; i < n * m; i++) {
            // ���� 0�� ���
            if (map[i / m][i % m] == 0) {
                posIdx[cnt] = i; // index ����
                comb(cnt + 1, i + 1); // ���� ã��
            }
        }
    }

    // bfs Ž�� (���̷��� �۶߸���)
    static void bfs(int x, int y, int[][] copyMap) {
        int[] dx = { 1, -1, 0, 0 };
        int[] dy = { 0, 0, 1, -1 };
        boolean[][] visited = new boolean[n][m]; // �湮 üũ �迭
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] { x, y });
        visited[x][y] = true; // �湮 üũ

        while (!queue.isEmpty()) {
            int curX = queue.peek()[0];
            int curY = queue.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                // ���� üũ + �湮 ���� �� + 0(�� ��)�� ���
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visited[nx][ny] && copyMap[nx][ny] == 0) {
                        queue.add(new int[] { nx, ny }); // ��ǥ �߰�
                        copyMap[nx][ny] = 2; // ���̷����� ����
                        visited[nx][ny] = true; // �湮 üũ
                    }
                }
            }
        }
    }

    // �� �� ���� ���� ���ϱ�
    static int cntzero(int[][] copyMap) {
        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (copyMap[i][j] == 0)
                    sum++;

        return sum;
    }
}
