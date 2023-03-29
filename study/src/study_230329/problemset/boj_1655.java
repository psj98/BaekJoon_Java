package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class boj_1655 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder()); // ��������
        PriorityQueue<Integer> right = new PriorityQueue<>(); // ��������

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (i % 2 == 0) // ¦���� ���� left�� ����
                left.add(num);
            else // Ȧ���� ���� right�� ����
                right.add(num);

            // ������ ������� �ʰ� left�� �ִ��� right�� �ּڰ����� ū ��� -> ��ȯ
            if (!left.isEmpty() && !right.isEmpty() && left.peek() > right.peek()) {
                int temp = left.poll();
                left.add(right.poll());
                right.add(temp);
            }

            sb.append(left.peek()).append("\n"); // ���� ���
        }

        System.out.println(sb);
    }
}
