package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1405 {
    static int n;
    static boolean[][] visited = new boolean[30][30]; // �湮 �迭
    static int[] dx = { 0, 0, 1, -1 }; // ���� ���� (��, ��, ��, ��)
    static int[] dy = { 1, -1, 0, 0 };
    static double[] percent = new double[4]; // Ȯ�� �迭
    static double ans = 0; // ����

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < 4; i++)
            percent[i] = Integer.parseInt(stk.nextToken()) * 0.01; // Ȯ�� ����

        visited[15][15] = true; // ���� ��ġ
        dfs(0, 1, 15, 15);

        // ���� ���
        sb.append(ans);
        System.out.println(sb);
    }

    // ���� ���ڷ� �����ϴ� ��Ʈ��ŷ
    static void dfs(int cnt, double sum, int x, int y) {
        if (cnt == n) { // �̵� Ƚ���� �������� ���信 �߰�
            ans += sum;
            return;
        }

        // dfs
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!visited[nx][ny]) { // �湮���� ���� ���� ����
                visited[nx][ny] = true; // �湮 üũ
                dfs(cnt + 1, sum * percent[i], nx, ny); // dfs
                visited[nx][ny] = false; // �������
            }
        }
    }
}