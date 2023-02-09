package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_23254 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Pair> pq = new PriorityQueue<>(); // Pair(����, �ð� �� ���� ����)

        int n = Integer.parseInt(stk.nextToken()) * 24; // �ð�
        int m = Integer.parseInt(stk.nextToken()); // ���� ����
        int sum = 0; // ����

        int[] scores = new int[m]; // ���� �迭

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            scores[i] = Integer.parseInt(stk.nextToken());

        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            pq.add(new Pair(scores[i], Integer.parseInt(stk.nextToken())));

        // �� �ð� ���� üũ
        for (int i = 0; i < n; i++) {
            // �� ���� ���� 100�̸� ��ü�� ���� 100�̹Ƿ� �� �̻� ����� �ʿ� ����
            if (pq.peek().first == 100)
                break;

            // score ���
            // ������ + min(100-������, ���� ���� ��)
            int scoreUp = pq.peek().second; // �ð� �� ���� ����
            int score = pq.peek().first + Math.min(100 - pq.peek().first, scoreUp);
            pq.poll();
            pq.add(new Pair(score, scoreUp));
        }

        while (!pq.isEmpty()) {
            sum += pq.peek().first;
            pq.poll();
        }

        sb.append(sum);
        System.out.println(sb);
    }

    public static class Pair implements Comparable<Pair> {
        int first;
        int second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        // ������ ������ �� �ִ� ���� ū ������ ����
        @Override
        public int compareTo(Pair a) {
            // 1 ��ȯ / -1 ��ȯ X
            return Math.min(100 - this.first, this.second) < Math.min(100 - a.first, a.second) ? 1 : -1;
        }
    }

}
