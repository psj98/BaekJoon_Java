package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14391 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int ans = 0;

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        // �� ����
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++)
                map[i][j] = str.charAt(j) - '0';
        }

        // 0 ~ 1111 1111 1111 1111 (���� 0, ���� 1) - 2^15
        // 2*2��� �ϸ�, 1010�� ��, ��� ���� ���Ͽ� Ž���ϱ� ������ 1010�� �Ųٷ��� 0101�� Ž���ص� ����
        for (int bit = 0; bit < 1 << (n * m); bit++) {
            int totalSum = 0;

            // ���� üũ
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = 0; j < m; j++) {
                    int flag = 1 << (i * m + j);

                    if ((bit & flag) == 0) { // ������ ��� (0)
                        sum = sum * 10 + map[i][j];
                    } else { // ������ ���, totalSum�� d���±��� ���� sum ���ϰ�, sum �ʱ�ȭ
                        totalSum += sum;
                        sum = 0;
                    }
                }

                totalSum += sum; // ������ �� ���ϱ�
            }

            // ���� üũ
            for (int i = 0; i < m; i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    int flag = 1 << (i + j * m);

                    if ((bit & flag) != 0) { // ������ ��� (1)
                        sum = sum * 10 + map[j][i];
                    } else { // ������ ���, totalSum�� d���±��� ���� sum ���ϰ�, sum �ʱ�ȭ
                        totalSum += sum;
                        sum = 0;
                    }
                }

                totalSum += sum; // ������ �� ���ϱ�
            }

            ans = Math.max(ans, totalSum);
        }

        sb.append(ans);
        System.out.println(sb);
    }
}
