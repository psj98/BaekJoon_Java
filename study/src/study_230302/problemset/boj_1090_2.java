package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_1090_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> x = new ArrayList<>(); // x��ǥ
        ArrayList<Integer> y = new ArrayList<>(); // y��ǥ
        ArrayList<int[]> pos = new ArrayList<>(); // (x, y) ��ǥ

        // x, y, (x, y) ��ǥ ����
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int numX = Integer.parseInt(stk.nextToken());
            int numY = Integer.parseInt(stk.nextToken());

            x.add(numX);
            y.add(numY);
            pos.add(new int[] { numX, numY });
        }

        // ���� �迭 �ʱ�ȭ
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);

        // ��� ������ ��ǥ�� ���� Ž��
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<Integer> minVal = new ArrayList<>(); // �Ÿ� ����Ʈ

                // �Ÿ� ���
                for (int[] cur : pos)
                    minVal.add(Math.abs(cur[0] - x.get(i)) + Math.abs(cur[1] - y.get(j)));

                Collections.sort(minVal); // �������� ����

                // �տ������� ������ �°� �ּڰ� ����
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += minVal.get(k);
                    ans[k] = Math.min(ans[k], sum);
                }
            }
        }

        // ���� ���
        for (int val : ans)
            sb.append(val).append(" ");
        System.out.println(sb);
    }
}
