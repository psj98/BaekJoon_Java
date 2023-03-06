package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_14400 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        // �ʱ�ȭ
        int n = Integer.parseInt(br.readLine().trim());
        int[] x = new int[n];
        int[] y = new int[n];

        // �� ����
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(stk.nextToken());
            y[i] = Integer.parseInt(stk.nextToken());
        }

        // �������� ����
        Arrays.sort(x); 
        Arrays.sort(y);

        // �߰��� ã��
        int midX = x[n / 2];
        int midY = y[n / 2];

        // �߰����� �������� �Ÿ� ���ϱ�
        long sum = 0;
        for (int i = 0; i < n; i++)
            sum += Math.abs(midX - x[i]) + Math.abs(midY - y[i]);

        sb.append(sum);
        System.out.println(sb);
    }
}