package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1082 {
    static class Number implements Comparable<Number> {
        int num, cost;

        Number(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Number o) {
            return this.cost - o.cost; // ��� �������� ����
        }
    }

    /*
     * 1. �ִ� �ڸ� ã��
     * 2. �� ���ڸ��� 0�� ���, �� ���� ������ cost üũ
     *    -> cost�� money���� Ŭ ��� ���� / cost�� money���� ���� ���, �ش� ���ڷ� ù ��° �ڸ� ����
     * 3. �ִ� �ڸ����� ans �迭�� ���� ����
     * 4. �� ���ڸ����� ū ���ڷ� ��ü
     * 5. ���� ���
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        if (n == 1) { // 0 �ۿ� ���� ���, 0 ���
            sb.append(0);
            System.out.println(sb);
            return;
        }

        Number[] sortArr = new Number[n]; // ��� �������� �迭
        int[] number = new int[n]; // �� ���� �� cost �迭
        int[] ans = new int[51]; // ���� �迭

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(stk.nextToken());
            sortArr[i] = new Number(i, number[i]);
        }

        Arrays.sort(sortArr); // �������� ����

        int money = Integer.parseInt(br.readLine());

        int idx = 0; // �ڸ��� üũ

        // �� ���ڸ��� 0�� ���
        if (sortArr[0].num == 0) {
            if (sortArr[1].cost > money) { // �� ���� ������ ����� ���� ������ �ִ� ������ ���� ���, 0 ���
                sb.append(0);
                System.out.println(sb);
                return;
            }

            // ù ��° �ڸ��� 0 ���� ���ڷ� ����
            ans[idx++] = sortArr[1].num;
            money -= sortArr[1].cost;
        }

        while (sortArr[0].cost <= money) { // �ִ� �ڸ����� ���� ����
            ans[idx++] = sortArr[0].num;
            money -= sortArr[0].cost;
        }

        for (int i = 0; i < idx; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == 0 && j == 0) // 1��° �ڸ��� 0�� �Ǹ� X
                    continue;

                if (money + number[ans[i]] >= number[j]) { // ���� �ڸ��� �ٸ� ���ڷ� ��ü ������ ���
                    money = money + number[ans[i]] - number[j];
                    ans[i] = j;
                    break;
                }
            }
        }

        // ���� ���
        for (int i = 0; i < idx; i++)
            sb.append(ans[i]);

        System.out.println(sb);
    }
}
