package study_230315.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj_1644 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        boolean[] prime = new boolean[n + 1];
        ArrayList<Integer> num = new ArrayList<>();

        // �����佺�׳׽��� ü
        for (int i = 2; i <= n; i++)
            for (int j = 2; i * j <= n; j++)
                prime[i * j] = true;

        // �Ҽ� ����
        for (int i = 2; i <= n; i++)
            if (!prime[i])
                num.add(i);

        int start = 0, end = 0, sum = 0, cnt = 0;

        // �� ������
        while (start <= end) {
            if (sum > n) // ���� ũ��, start�� ++
                sum -= num.get(start++);
            else if (end == num.size()) // n�� �Ҽ��� �ͱ��� ����
                break;
            else // ���� ������, end�� ++
                sum += num.get(end++);

            if (sum == n)
                cnt++;
        }

        // ���� ���
        sb.append(cnt);
        System.out.println(sb);
    }
}