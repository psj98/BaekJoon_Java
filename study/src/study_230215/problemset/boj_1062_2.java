package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1062_2 {
    static int n, k, ans;
    static String[] word;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        char[] acint = { 'a', 'c', 'i', 'n', 't' }; // �⺻���� ������ �־�� �ϴ� ���ĺ�
        int flag = 0;

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

        // flag�� ���ĺ� �ʱ�ȭ
        for (int i = 0; i < 5; i++)
            flag |= 1 << (acint[i] - 97);

        // ��Ʈ��ŷ
        search(0, 0, flag);

        sb.append(ans);
        System.out.println(sb);
    }

    // ���� ã��
    public static void search(int cnt, int idx, int flag) {
        if (cnt == k) {
            int sum = 0;

            for (int i = 0; i < n; i++) {
                boolean check = false;
                for (int j = 0; j < word[i].length(); j++) {
                    // �湮���� ���� ���� ������ �ִ� ���
                    if ((flag & (1 << (word[i].charAt(j) - 97))) == 0) {
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

        // ��Ʈ����ŷ���� flag�� �������� i�� shift�� ���� &�� ����
        // 0�̸� �湮���� ���� ���̹Ƿ� flag�� | ����
        // 1�̸� �湮�� ���̹Ƿ� ���
        for (int i = idx; i < 26; i++)
            if ((flag & (1 << i)) == 0)
                search(cnt + 1, i + 1, flag | (1 << i));
    }

}
