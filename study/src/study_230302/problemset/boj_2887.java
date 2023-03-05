package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_2887 {
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static ArrayList<int[]> node = new ArrayList<>();
    static int[] parents;
    static int n;

    // ���� Ŭ����
    static class Edge implements Comparable<Edge> {
        int start, end, cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost; // ��� �������� ����
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        int cost;

        n = Integer.parseInt(br.readLine()); // ���� ����

        // ���� ����
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());
            node.add(new int[] { x, y, z, i });
        }

        // x��ǥ �������� ����
        node.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // PriorityQueue�� ���� ���� ����
        for (int i = 1; i < n; i++) {
            cost = Math.abs(node.get(i - 1)[0] - node.get(i)[0]);
            pq.add(new Edge(node.get(i - 1)[3], node.get(i)[3], cost));
        }

        // y��ǥ �������� ����
        node.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        // PriorityQueue�� ���� ���� ����
        for (int i = 1; i < n; i++) {
            cost = Math.abs(node.get(i - 1)[1] - node.get(i)[1]);
            pq.add(new Edge(node.get(i - 1)[3], node.get(i)[3], cost));
        }

        // z��ǥ �������� ����
        node.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // PriorityQueue�� ���� ���� ����
        for (int i = 1; i < n; i++) {
            cost = Math.abs(node.get(i - 1)[2] - node.get(i)[2]);
            pq.add(new Edge(node.get(i - 1)[3], node.get(i)[3], cost));
        }

        makeSet(); // parents �迭 �ʱ�ȭ

        int cnt = 0, sum = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            // union�� �����ϸ�
            if (union(edge.start, edge.end)) {
                sum += edge.cost; // ��� ���ϱ�
                cnt++; // ���� ���� ����
            }

            // ���� ������ ����-1 �̸� break
            if (cnt == n - 1)
                break;
        }

        // ���� ���
        sb.append(sum);
        System.out.println(sb);
    }

    // parents �迭 �ʱ�ȭ
    static void makeSet() {
        parents = new int[n];
        for (int i = 0; i < n; i++)
            parents[i] = i;
    }

    // ���� ã��
    static int findSet(int num) {
        if (num == parents[num])
            return num;
        return parents[num] = findSet(parents[num]);
    }

    // ���� ��ġ��
    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }
}
