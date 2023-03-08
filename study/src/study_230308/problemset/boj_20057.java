package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20057 {
    static int n, tx, ty, ans = 0; // �� ũ��, ����̵� ��ǥ, ������ ���� �� ��
    static int[][] map; // �� ũ��
    static int[] dx = { 0, 1, 0, -1 }; // ���� ����
    static int[] dy = { -1, 0, 1, 0 };

    // ����̵��� ���� �� ������ �� ��ǥ
    static int[][] spreadX = {
            { 0, 0, -1, -1, -1, -2, 1, 1, 1, 2 }, // ��
            { 1, 2, -1, 0, 1, 0, -1, 0, 1, 0 }, // ��
            { 0, 0, 1, 1, 1, 2, -1, -1, -1, -2 }, // ��
            { -1, -2, 1, 0, -1, 0, 1, 0, -1, 0 } }; // ��
    static int[][] spreadY = {
            { -1, -2, 1, 0, -1, 0, 1, 0, -1, 0 }, // ��
            { 0, 0, -1, -1, -1, -2, 1, 1, 1, 2 }, // ��
            { 1, 2, -1, 0, 1, 0, -1, 0, 1, 0 }, // ��
            { 0, 0, 1, 1, 1, 2, -1, -1, -1, -2 } }; // ��
    static int[] ratio = { 0, 5, 1, 7, 10, 2, 1, 7, 10, 2 }; // ����

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        // �ʱ�ȭ �� �� ���� ����
        tx = ty = n / 2; // ����̵� ���� ��ǥ
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        move(); // ����̵� ������

        // ���� ���
        sb.append(ans);
        System.out.println(sb);
    }

    // ����̵� �����̱�
    static void move() {
        int idx = 0, num = 1; // ���� ����, �� ĭ �̵��ϴ���

        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < num; j++) {
                    tx += dx[idx];
                    ty += dy[idx];

                    if (ty == -1) // y��ǥ�� -1�� �Ǹ� ����
                        return;

                    if (map[tx][ty] > 0) // �� ���� 0���� Ŭ ��, �𷡸� ��Ʈ��
                        spread(idx);
                }

                // ���� ���� ����
                idx++;
                idx %= 4;
            }

            num++;
        }
    }

    // �� ��Ʈ����
    static void spread(int idx) {
        int sum = 0, nx = 0, ny = 0; // y���� �� �� ��

        for (int i = 1; i < 10; i++) {
            nx = tx + spreadX[idx][i];
            ny = ty + spreadY[idx][i];

            int amount = map[tx][ty] * ratio[i] / 100; // �� ���� ���
            sum += amount; // y���� �� �� �� ����
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) // ���� ���� ���, �ش� ��ǥ�� �� ����
                map[nx][ny] += amount;
            else // ���� ���� ���, ���信 �� ���� ����
                ans += map[tx][ty] * ratio[i] / 100;
        }

        // �����ִ� �� ���� ��ġ
        nx = tx + spreadX[idx][0];
        ny = ty + spreadY[idx][0];

        if (nx >= 0 && nx < n && ny >= 0 && ny < n) // ���� ���� ���, ���� �� �翡�� ���� �� ���� ����ŭ ����
            map[nx][ny] += (map[tx][ty] - sum);
        else // ���� ���� ���, ���信 ����
            ans += (map[tx][ty] - sum);

        map[tx][ty] = 0; // ���� ��ġ�� �� ���� 0���� ����
    }
}
