package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1062_1 {
    static boolean[] visited = new boolean[26]; // �湮 �迭
    static int n, k, ans = -1;
    static String[] word;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        char[] acint = { 'a', 'c', 'i', 'n', 't' }; // �⺻���� ������ �־�� �ϴ� ���ĺ�

        // �湮 �迭 �ʱ�ȭ
        for (int i = 0; i < 5; i++)
            visited[acint[i] - 97] = true;

        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken()) - 5; // �⺻ ���ĺ� ����

        // �ܾ�
        word = new String[n];
        for (int i = 0; i < n; i++)
            word[i] = br.readLine();

        // 5�� (a, c, i, n, t)���� ������ �ܾ ���� �� ����
        if (k < 0) {
            sb.append(0);
            System.out.println(sb);
            return;
        }

        // ��Ʈ��ŷ
        search(0, 0);

        sb.append(ans);
        System.out.println(sb);
    }

    // ���� ã��
    public static void search(int cnt, int idx) {
        if (cnt == k) {
            int sum = 0;

            for (int i = 0; i < n; i++) {
                boolean check = false;
                for (int j = 0; j < word[i].length(); j++) {
                    // �湮���� ���� ���� ������ �ִ� ���
                    if (!visited[word[i].charAt(j) - 97]) {
                        check = true;
                        break;
                    }
                }

                if (check)
                    continue;
                sum++;
            }

            ans = Math.max(ans, sum);

            return;
        }

        for (int i = idx; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                search(cnt + 1, i + 1);
                visited[i] = false;
            }
        }
    }

}
