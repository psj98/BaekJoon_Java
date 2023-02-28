package study_230222.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7576 {
    static int n, m, cnt = -1;
    static int[][] map; // �丶�� ���� ��
    static int[] dx = { 1, -1, 0, 0 }; // bfs Ž���ϱ� ���� �迭
    static int[] dy = { 0, 0, 1, -1 };
    static boolean[][] visited; // �湮 üũ
    static ArrayList<int[]> pos = new ArrayList<>(); // ���� 1�� (x, y)�� ������ ����Ʈ

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // �� �Է� �� �ʱ�ȭ
        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] == 1) // 1�� ���� ��ġ�� ���� ����
                    pos.add(new int[] { i, j });
            }
        }

        bfs(); // bfs Ž��

        // 0�� ������ -1 ��� / �ƴϸ� cnt ���
        if (check())
            sb.append(-1);
        else
            sb.append(cnt);
        System.out.println(sb);
    }

    // bfs Ž��
    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        int size = 0;

        // 1�� ���� queue�� ����
        for (int i = 0; i < pos.size(); i++) {
            int x = pos.get(i)[0];
            int y = pos.get(i)[1];
            queue.add(pos.get(i));
            visited[x][y] = true;
        }

        // queue�� �� ������ ����
        while (!queue.isEmpty()) {
            size = queue.size(); // ��¥�� ���� ���� size�� queue.size() ����
            cnt++; // cnt ����

            for (int s = 0; s < size; s++) {
                int curX = queue.peek()[0];
                int curY = queue.poll()[1];

                for (int i = 0; i < 4; i++) {
                    int nx = curX + dx[i];
                    int ny = curY + dy[i];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        if (!visited[nx][ny] && map[nx][ny] == 0) { // 0�� ��쿡�� �� 1�� ����
                            visited[nx][ny] = true;
                            map[nx][ny] = 1;
                            queue.add(new int[] { nx, ny });
                        }
                    }
                }
            }
        }

        return;
    }

    // 0�� �ִ��� üũ
    static boolean check() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (map[i][j] == 0)
                    return true;

        return false;
    }
}
