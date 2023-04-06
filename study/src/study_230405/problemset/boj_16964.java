package study_230405.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_16964 {
    static ArrayList<Integer>[] list;
    static int[] ans, sortArr;
    static boolean[] visited;
    static int cnt = 1;
    static StringBuilder sb = new StringBuilder();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        int V = Integer.parseInt(br.readLine()); // ���� ����

        // �ʱ�ȭ
        ans = new int[V + 1];
        sortArr = new int[V + 1];
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
        for (int i = 1; i <= V; i++) {
            ans[i] = Integer.parseInt(stk.nextToken());
            sortArr[ans[i]] = i; // Ž�� ������ ã�� ���� �迭
        }

        // sort - sortArr ������ ���� ���� (���� ������ ���� ���� ����)
        for (int i = 1; i <= V; i++) {
            Collections.sort(list[i], new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return sortArr[o1] - sortArr[o2];
                }
            });
        }

        dfs(1); // ���� ��� 1

        sb.append(1);
        System.out.println(sb);
    }

    static void dfs(int node) {
        if (visited[node]) { // �湮�� ��� return
            return;
        }

        if (ans[cnt] != node) { // ���� dfs�� �ٸ� ���
            sb.append(0);
            System.out.println(sb);
            System.exit(0);
        }

        cnt++; // ���� dfs�� ���� ���, ���� ���� node�� Ž���ϱ� ���� cnt++
        visited[node] = true; // �湮 üũ

        // ���� ��忡�� �� �� �ִ� �� Ž��
        for (int next : list[node]) {
            dfs(next);
        }
    }
}
