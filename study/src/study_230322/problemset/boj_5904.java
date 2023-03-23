package study_230322.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_5904 {
    static char ans; // ����

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        divide(n); // ���� ����

        sb.append(ans);
        System.out.println(sb);
    }

    // S(k)=S(k-1) + 'm'(1)+'o'(k+2) + S(k-1);
    static void divide(int n) {
        int center = 3, side = 4; // S(0) ����, ����� ���� ����

        if (n == 1) // �� ���� m
            ans = 'm';
        else if (n == 2 || n == 3) // ���� �� �ڸ����� o
            ans = 'o';
        else {// �� ���� ��� ��ȭ���� ���� ������ ��
            while (true) { // ������ n���� ũ�ų� ������ ������ ���
                if (center * 2 + side >= n)
                    break;
                center = center * 2 + side++;
            }

            if (center + 1 == n) // ���� ��ȭ�Ŀ��� �� �տ� S(k-1)�� center -> center ���� �ڸ��� ������ m
                ans = 'm';
            else if (center + 1 < n && n <= center + side) { // 'o'(k+2) ��ġ
                ans = 'o';
            } else { // �ڿ� ��ġ�� S(k-1)�� ���, ������ ���ܼ� ���
                divide(n - center - side);
                return;
            }
        }
    }
}