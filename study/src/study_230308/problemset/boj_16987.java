package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16987 {
    static int n, ans = 0;
    static Egg[] egg;

    static class Egg {
        int durability, weight; // ������, ����

        Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        egg = new Egg[n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            egg[i] = new Egg(durability, weight);
        }

        eggBreak(0, 0); // ��� ����

        sb.append(ans);
        System.out.println(sb);
    }

    // ��� ���� - (�� ��° ���, ���� ��� ��)
    static void eggBreak(int cnt, int sum) {
        if (cnt == n) { // ������ ������� �� �� ���
            ans = Math.max(ans, sum); // �ִ� ����
            return;
        }

        // ���� ��� �������� 0 ���ϸ� ���� �������
        // ���� ����� �������� ��� 0 ���ϸ� ���� �������
        if (egg[cnt].durability <= 0 || sum == n - 1) {
            eggBreak(cnt + 1, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            // cnt��° ����� ����� ��, �� ����� �� �� ���� / ������ 1�̻�
            if (i != cnt && egg[i].durability >= 1) {
                int broken = 0; // ���� ��� ����
                egg[cnt].durability -= egg[i].weight;
                egg[i].durability -= egg[cnt].weight;

                // ���� ��� ���� ����
                if (egg[cnt].durability <= 0)
                    broken++;
                if (egg[i].durability <= 0)
                    broken++;

                eggBreak(cnt + 1, sum + broken); // ��Ʈ��ŷ

                // �������
                egg[cnt].durability += egg[i].weight;
                egg[i].durability += egg[cnt].weight;
            }
        }
    }
}
