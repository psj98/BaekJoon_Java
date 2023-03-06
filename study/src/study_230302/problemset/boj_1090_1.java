package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_1090_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        Set<Integer> x = new HashSet<>(); // x��ǥ
        Set<Integer> y = new HashSet<>(); // y��ǥ
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

        Iterator<Integer> itX = x.iterator();
        Iterator<Integer> itY = y.iterator();

        // ���� �迭 �ʱ�ȭ
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);

        // ��� ������ ��ǥ�� ���� Ž��
        while (itX.hasNext()) {
            int curX = itX.next();
            itY = y.iterator();
            while (itY.hasNext()) {
                ArrayList<Integer> minVal = new ArrayList<>(); // �Ÿ� ����Ʈ
                int curY = itY.next();

                // �Ÿ� ���
                for (int[] cur : pos)
                    minVal.add(Math.abs(cur[0] - curX) + Math.abs(cur[1] - curY));

                Collections.sort(minVal);

                // �տ������� ������ �°� �ּڰ� ����
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += minVal.get(i);
                    ans[i] = Math.min(ans[i], sum);
                }
            }
        }

        // ���� ���
        for (int val : ans)
            sb.append(val).append(" ");
        System.out.println(sb);
    }
}
