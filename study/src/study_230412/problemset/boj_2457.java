package study_230412.problemset;

import java.io.*;
import java.util.*;

public class boj_2457 {
    static class Flower implements Comparable<Flower> {
        int start, end; // ������, ������

        Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start == o.start) // ������ ��������
                return o.end - this.end;
            return this.start - o.start; // ������ ��������
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Flower[] flower = new Flower[n];

        // �� ����
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int startM = Integer.parseInt(stk.nextToken()) * 100;
            int startD = Integer.parseInt(stk.nextToken());
            int endM = Integer.parseInt(stk.nextToken()) * 100;
            int endD = Integer.parseInt(stk.nextToken());

            flower[i] = new Flower(startM + startD, endM + endD);
        }

        Arrays.sort(flower); // ����

        int idx = 0, cnt = 0;
        int startDate = 301, endDate = 0; // ���� �Ǵ�, ���� ��¥
        while (startDate < 1201) {
            boolean check = false;
            int cur = idx;

            for (int i = cur; i < n; i++) {
                if (flower[i].start > startDate)
                    break;

                // �Ǵ� ��¥�� �����Ͽ� ������� �ϰ�, �������� �ִ��� �����ɸ��� �� ã��
                if (flower[i].start <= startDate && flower[i].end > endDate) {
                    endDate = flower[i].end;
                    idx++;
                    check = true;
                }
            }

            if (check) { // ��ġ�� ���� ������ ����
                startDate = endDate;
                cnt++;
            } else // ������ ����
                break;
        }

        // ���� ���
        if (endDate >= 1201) // 1130�� ���� 1130�� ���� X -> 1201 �̻��̾�� ��
            sb.append(cnt);
        else
            sb.append(0);
        System.out.println(sb);
    }
}
