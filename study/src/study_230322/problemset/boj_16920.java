package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16920 {
    static int n, m, p;
    static int[][] map;
    static int[] move;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static Queue<int[]>[] pos;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        p = Integer.parseInt(stk.nextToken());

        move = new int[p + 1];
        pos = new ArrayDeque[p + 1]; // ���� �ٱ��� ��ǥ�� ������ ť
        for (int i = 0; i <= p; i++)
            pos[i] = new ArrayDeque<>();

        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= p; i++)
            move[i] = Integer.parseInt(stk.nextToken());

        // �� : ���� / �� : -1 / �� ���� : 0
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = str.charAt(j);
                if (c == '.')
                    map[i][j] = 0;
                else if (c == '#')
                    map[i][j] = -1;
                else {
                    map[i][j] = c - '0';
                    pos[c - '0'].add(new int[] { i, j });
                }
            }
        }

        while (true) {
            // ������ �� Ȯ��
            for (int i = 1; i <= p; i++) {
                if (pos[i].size() != 0)
                    bfs(i);
            }

            // �� ���� ������ ����
            if (!check())
                break;
        }

        // �� ���� ���� ����
        int[] cnt = new int[p + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0)
                    cnt[map[i][j]]++;
            }
        }

        // ���� ���
        for (int i = 1; i <= p; i++)
            sb.append(cnt[i]).append(" ");
        System.out.println(sb);
    }

    // �� Ž�� �� Ȯ��
    static void bfs(int num) {
        int curMove = move[num]; // ������ �� �ִ� Ƚ��
        while (curMove-- != 0) {
            int size = pos[num].size();

            // ũ�Ⱑ 0�̸� Ž�� �ʿ� ����
            if (size == 0)
                break;

            // ũ�⸸ŭ ���� -> Ƚ�� üũ�ϱ� ����
            for (int i = 0; i < size; i++) {
                int[] curPos = pos[num].poll(); // ���� ��ġ
                for (int k = 0; k < 4; k++) {
                    int nx = curPos[0] + dx[k]; // ������ ������ �� �ִ� ��
                    int ny = curPos[1] + dy[k];

                    // ���� üũ
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                        if (map[nx][ny] == 0) {
                            map[nx][ny] = num;
                            pos[num].add(new int[] { nx, ny });
                        }
                    }
                }
            }
        }
    }

    // ��ü ��ǥ�� ���� �� ���� ���� ��� üũ
    static boolean check() {
        for (int i = 1; i <= p; i++) {
            if (pos[i].size() != 0)
                return true;
        }

        return false;
    }
}