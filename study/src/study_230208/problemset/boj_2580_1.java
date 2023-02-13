package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2580_1 {
    static StringBuilder sb = new StringBuilder();
    static int[][] map = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        for (int i = 0; i < 9; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        sudoku(0, 0);
    }

    public static void sudoku(int x, int y) {
        // 9*9�� ��� ��ȸ�����Ƿ� ����
        if (x == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
            System.exit(0);
        }

        // map�� ���̹Ƿ� x+1�ϰ� y�� 0���� �ʱ�ȭ
        if (y == 9) {
            sudoku(x + 1, 0);
            return;
        }

        // ���� ã��
        if (map[x][y] == 0) {
            // �� ���� �־�� �´� �� ã��
            for (int i = 1; i <= 9; i++) {
                // ������ ���� ��ǥ Ž��
                if (check(x, y, i)) {
                    map[x][y] = i;
                    sudoku(x, y + 1);
                    map[x][y] = 0;
                }
            }

            return;
        }

        sudoku(x, y + 1);
    }

    // ���� ã�� (����, ����, 3*3 �ȿ� ��� �ٸ� ��)
    public static boolean check(int x, int y, int num) {
        // ����, ����
        for (int i = 0; i < 9; i++) {
            if (map[x][i] == num)
                return false;
            if (map[i][y] == num)
                return false;
        }

        // 3*3
        for (int i = x / 3 * 3; i < x / 3 * 3 + 3; i++) {
            for (int j = y / 3 * 3; j < y / 3 * 3 + 3; j++) {
                if (map[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
