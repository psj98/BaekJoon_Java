package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2636 {
    static int n, m;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int time = 0, cnt = 0;

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new int[n][m];

        // map ���� ����
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        // ġ� �� ���� ������ ����
        while (count(map) != 0) {
            cnt = count(map); // ������ 1�ð������� ġ�� ����
            visited = new boolean[n][m]; // �湮 �迭 �ʱ�ȭ

            // �� ������ ����
            for (int i = 0; i < n; i++) {
                // ����, �Ʒ����� ���, ���ο� ���� ��� Ž��
                if (i == 0 || i == n - 1) {
                    for (int j = 0; j < m; j++) {
                        if (!visited[i][j] && map[i][j] == 0) {
                            dfs(i, j);
                        }
                    }
                } else { // �������� ���, �� ���� ���ؼ��� Ž��
                    if (!visited[i][0] && map[i][0] == 0) {
                        dfs(i, 0);
                    }

                    if (!visited[i][m - 1] && map[i][m - 1] == 0) {
                        dfs(i, m - 1);
                    }
                }
            }

            changeMap(map); // map ����

            time++; // �ð� ����
        }

        sb.append(time).append("\n").append(cnt);
        System.out.println(sb);
    }

    // dfs - ġ�� ������ 1�� 2�� ����
    static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                if (map[nx][ny] == 1) { // ġ� �ִ� ���
                    map[nx][ny] = 2;
                } else if (map[nx][ny] == 0) { // �� ����
                    dfs(nx, ny);
                }
            }
        }
    }

    // ġ�� ���� Ȯ��
    static int count(int[][] cntMap) {
        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (cntMap[i][j] == 1)
                    sum++;

        return sum;
    }

    // �� ����
    static void changeMap(int[][] cngMap) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (cngMap[i][j] == 2)
                    cngMap[i][j] = 0;
    }
}
