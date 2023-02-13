package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2447_1 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                recursion(i, j, n);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    // ��� ���� Ȯ�� -> n���� ������ 3���� ���� �������� 1�� (n=3�� ��, [1, 1], n=9�� ��, [3~5, 3~5])
    public static void recursion(int x, int y, int n) {
        if (x / n % 3 == 1 && y / n % 3 == 1) {
            sb.append(" ");
        } else {
            if (n == 1)
                sb.append("*");
            else
                recursion(x, y, n / 3);
        }
    }

}
