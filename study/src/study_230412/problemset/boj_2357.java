package study_230412.problemset;

import java.io.*;
import java.util.*;

public class boj_2357 {
    /*
     * 1. maxTree, minTree ���ϱ�
     * 2. �θ�� �ö󰡸鼭 �ּڰ�, �ִ� ����
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int height = (int) Math.ceil(Math.log(n) / Math.log(2)); // Ʈ�� ����
        int[] minTree = new int[1 << (height + 1)]; // �ּڰ� ã�� Ʈ��
        int[] maxTree = new int[1 << (height + 1)]; // �ִ� ã�� Ʈ��

        Arrays.fill(minTree, Integer.MAX_VALUE); // �ִ����� ���� & ���� 1���� ������ ������ maxTree�� ���� �ʿ� ����

        // Ʈ���� �� ����
        int size = 1 << height;
        for (int i = 0; i < n; i++)
            maxTree[size + i] = minTree[size + i] = Integer.parseInt(br.readLine());

        for (int i = size - 1; i > 0; i--) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]); // �ּڰ����� �θ� ����
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]); // �ִ����� �θ� ����
        }

        // ������ idx�� ��� Ž��
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken()) + size - 1;
            int b = Integer.parseInt(stk.nextToken()) + size - 1;

            int left = a;
            int right = b;

            /* �ּڰ� ã�� */
            int result = Integer.MAX_VALUE;
            while (left <= right) {
                if (left % 2 == 1) // ��� ��ȣ�� Ȧ���� ���, ������ ���� ��ġ�� �θ�� �̵�
                    result = Math.min(result, minTree[left]);
                if (right % 2 == 0) // ��� ��ȣ�� ¦���� ���, ���� ���� ��ġ�� �θ�� �̵�
                    result = Math.min(result, minTree[right]);

                // �θ�� �̵�
                left = (left + 1) / 2;
                right = (right - 1) / 2;
            }

            sb.append(result).append(" ");

            left = a;
            right = b;

            /* �ִ� ã�� */
            result = 0;
            while (left <= right) {
                if (left % 2 == 1)
                    result = Math.max(result, maxTree[left]);
                if (right % 2 == 0)
                    result = Math.max(result, maxTree[right]);

                // �θ�� �̵�
                left = (left + 1) / 2;
                right = (right - 1) / 2;
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}
