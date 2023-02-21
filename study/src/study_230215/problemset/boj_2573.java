package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2573 {
    static int n, m; // �� ũ��
    static int[][] map; // �� ����
    static boolean[][] zeroCheck, visited; // 0���� üũ, dfs �湮 ���� üũ
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][m];
        zeroCheck = new boolean[n][m];

        // �� ���� ���� | map�� ���� 0�̸� true, 0�� �ƴϸ� false�� ����
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                zeroCheck[i][j] = map[i][j] == 0 ? true : false;
            }
        }

        // �� ���� ������ �ݺ� -> �� �� ������ ������ �и� ���θ� Ȯ���ϱ� ������ cnt++
        while (!melt()) {
            cnt++;
        }

        sb.append(cnt);
        System.out.println(sb);
    }

    // ���� ���̱�
    static boolean melt() {
        // ������ ���������� ����
        if (checkArea()) {
            return true;
        } else {
            // ������ ���������� ������ ���� ���̱�
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        // map�� 0�� �ƴ� ��, ���� üũ, �� �ֺ��� 0�� �� -> map�� ������ �Ǹ� 0���� ����
                        if (!zeroCheck[i][j] && nx >= 0 && nx < n && ny >= 0 && ny < m && zeroCheck[nx][ny]) {
                            if (map[i][j] > 0)
                                map[i][j]--;
                        }
                    }
                }
            }

            // ������ ��� ��Ƽ� �������� ���
            if (checkSum()) {
                System.out.println(0);
                System.exit(0);
            }

            return false;
        }
    }

    // ������ ��� ��Ƽ� �������� �� üũ
    static boolean checkSum() {
        int zero = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    zeroCheck[i][j] = true;
                    zero++;
                }
            }
        }

        if (zero == n * m) {
            return true;
        } else {
            return false;
        }
    }

    // �и� ���� üũ
    static boolean checkArea() {
        int area = 0;
        visited = new boolean[n][m];

        // 0�� �ƴ� �� + �湮���� ���� �� Ž�� = Ž�� �Ϸ�Ǹ� area ����
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!zeroCheck[i][j] && !visited[i][j]) {
                    visited[i][j] = true;
                    dfs(i, j);
                    area++;
                }
            }
        }

        // ������ 2 �̻����� ���������� true / �ƴϸ� false
        return area >= 2 ? true : false;
    }

    // ���� Ž��
    static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !zeroCheck[nx][ny] && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }

        return;
    }
}
