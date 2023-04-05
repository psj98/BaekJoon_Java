package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_16964 {
    static ArrayList<Integer>[] list;
    static int[] ans;
    static boolean[] visited;
    static int cnt = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int V = Integer.parseInt(br.readLine()); // ���� ����

        // �ʱ�ȭ
        ans = new int[V + 1];
        visited = new boolean[V + 1];
        list = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++)
            list[i] = new ArrayList<>();

        // ���� ���� ���� (�����)
        for (int i = 0; i < V - 1; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());

            list[from].add(to);
            list[to].add(from);
        }

        // ����� ����
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= V; i++)
            ans[i] = Integer.parseInt(stk.nextToken());

    }
}
