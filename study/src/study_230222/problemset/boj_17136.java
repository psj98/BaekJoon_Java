package study_230222.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17136 {
    static int[][] map = new int[10][10];
    static int[] paper = new int[6];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // �� ���� ����
        for (int i = 0; i < 10; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        find(0, 0, 0); // ������ ã��

        // ���� ��� -> max ���̸� -1
        if (ans == Integer.MAX_VALUE)
            ans = -1;
        sb.append(ans);
        System.out.println(ans);
    }

    // ������ ã��
    static void find(int x, int y, int cnt) {
        // �� �������� �����ϸ� �ּڰ� ���� ��, return
        if (x == 9 && y == 10) {
            ans = Math.min(ans, cnt);
            return;
        }

        // y�� 10�̸� x�� ����, y=0
        if (y == 10) {
            x++;
            y = 0;
        }

        // cnt�� ans���� ũ�� �ڿ��� Ž���� �ʿ� ����
        if (ans < cnt)
            return;

        if (map[x][y] == 1) { // 1�� ���
            // ������ ���� ũ�� Ȯ��
            for (int i = 5; i > 0; i--) {
                if (can(x, y, i) && paper[i] < 5) { // ������ ���� �� ������
                    paste(x, y, i, 2); // ������ ����
                    paper[i]++; // ������ ���� ����
                    find(x, y + 1, cnt + 1);
                    paste(x, y, i, 1); // �������
                    paper[i]--; // ������ ���� ����
                }
            }
        } else { // 1�� �ƴ� ��� ���� ��ǥ�� �̵�
            find(x, y + 1, cnt);
        }
    }

    // ������ ���� �� �ִ��� ����
    static boolean can(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i >= 10 || j >= 10)
                    return false;
                if (map[i][j] == 0 || map[i][j] == 2)
                    return false;
            }
        }

        return true;
    }

    // ������ ���̱�
    static void paste(int x, int y, int size, int num) {
        for (int i = x; i < x + size; i++)
            for (int j = y; j < y + size; j++)
                map[i][j] = num;
    }
}
