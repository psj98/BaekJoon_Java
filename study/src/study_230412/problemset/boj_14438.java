package study_230412.problemset;

import java.io.*;
import java.util.*;

public class boj_14438 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int height = (int) Math.ceil(Math.log(n) / Math.log(2)); // Ʈ�� ����
        int size = 1 << height; // ���� ���� ��ġ

        int[] tree = new int[1 << (height + 1)]; // Ʈ�� �ʱ�ȭ
        Arrays.fill(tree, Integer.MAX_VALUE);

        // �� ����
        stk = new StringTokenizer(br.readLine());
        for (int i = size; i < size + n; i++)
            tree[i] = Integer.parseInt(stk.nextToken());

        // �ּڰ� ����
        for (int i = size - 1; i > 0; i--)
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);

        System.out.println(Arrays.toString(tree));

        // ���� ����
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken()) + size - 1;
            int b = Integer.parseInt(stk.nextToken());

            if (op == 1) { // �� ����
                tree[a] = b;
                a /= 2;

                // �θ� ��带 ���󰡸鼭 �ּڰ� ����
                while (a > 0) {
                    tree[a] = Math.min(tree[a * 2], tree[a * 2 + 1]);
                    a /= 2;
                }

                System.out.println(Arrays.toString(tree));
            } else if (op == 2) { // �ּڰ� ���ϱ�
                b += (size - 1);

                int num = Integer.MAX_VALUE;
                while (a < b) {
                    if (a % 2 == 1)
                        num = Math.min(num, tree[a]);
                    if (b % 2 == 0)
                        num = Math.min(num, tree[b]);

                    a = (a + 1) / 2;
                    b = (b - 1) / 2;
                }

                if (a == b)
                    num = Math.min(num, tree[a]);

                sb.append(num).append("\n");
            }
        }

        System.out.println(sb);
    }
}
