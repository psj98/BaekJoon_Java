package study_230329.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_21611 {
    static int n, m;
    static int[][] map;
    static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 }; // �� �Ʒ� �� ����
    static int[] moveX = { 0, 1, 0, -1 }, moveY = { -1, 0, 1, 0 }; // �� �Ʒ� ���� ��
    static int[] marble;
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        marble = new int[3]; // ������ ���� ����
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(stk.nextToken());
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(stk.nextToken()) - 1;
            int scope = Integer.parseInt(stk.nextToken());

            destroy(dir, scope); // �ı�
            makeQueue(); // queue ����
            moveMarble(); // �迭�� �ֱ�
            makeQueue();
            explosion(); // ����
            moveMarble();
            makeQueue();
            change(); // ��ȭ
            moveMarble();
        }

        // ���� ��� �� ���
        int ans = 0;
        for (int i = 1; i <= 3; i++) {
            ans += (i * marble[i - 1]);
        }

        sb.append(ans);
        System.out.println(sb);
    }

    // ���� �ı�
    static void destroy(int dir, int scope) {
        int x = n / 2, y = n / 2;
        while (scope != 0) { // �ش� scope ��ŭ �̵��ϸ鼭 0���� ����
            x += dx[dir];
            y += dy[dir];
            map[x][y] = 0;
            scope--;
        }
    }

    // queue�� ������� �ֱ�
    static void makeQueue() {
        queue = new ArrayDeque<>();
        int x = n / 2, y = n / 2, idx = 0, cnt = 1;
        while (true) { // �� �Ʒ� ���� �� �������
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cnt; j++) {
                    x += moveX[idx];
                    y += moveY[idx];

                    if (y == -1) {
                        return;
                    }

                    if (map[x][y] != 0)
                        queue.add(map[x][y]);
                }

                idx = (idx + 1) % 4; // ���� �̵�
            }

            cnt++;
        }
    }

    // queue���� ���� �迭�� ����
    static void moveMarble() {
        int x = n / 2, y = n / 2, idx = 0, cnt = 1;
        while (true) { // �� �Ʒ� ���� �� ������� ����
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < cnt; j++) {
                    x += moveX[idx];
                    y += moveY[idx];

                    if (y == -1) {
                        return;
                    }

                    if (!queue.isEmpty()) // queue�� ���� ������ �ش� �� ����
                        map[x][y] = queue.poll();
                    else // ���� ������ 0 ����
                        map[x][y] = 0;
                }

                idx = (idx + 1) % 4; // ���� �̵�
            }

            cnt++;
        }
    }

    // ���� ����
    static void explosion() {
        int destroyCnt = -1;
        while (destroyCnt != 0) { // ���� ������ ���̷���� ������ �ݺ�
            Queue<Integer> next = new ArrayDeque<>(); // ���ӵ� ������ �� queue
            queue.add(0);

            int curNum = queue.poll(), cnt = 1;
            destroyCnt = 0; // �ı��� Ƚ��
            while (!queue.isEmpty()) {
                int num = queue.poll();

                if (curNum != num) {
                    if (cnt < 4) { // 4�� �̸��̸�, �ش� ���� next�� cnt��ŭ ����
                        for (int i = 0; i < cnt; i++)
                            next.add(curNum);
                    } else { // 4�� �̻��̸�, marble �迭�� ������ ���� ���� �߰�
                        destroyCnt++;
                        marble[curNum - 1] += cnt;
                    }

                    curNum = num;
                    cnt = 1;
                } else {
                    cnt++;
                }
            }

            while (!next.isEmpty()) {
                queue.add(next.poll());
            }
        }
    }

    // ���� ��ȭ
    static void change() {
        Queue<Integer> next = new ArrayDeque<>(); // ���� �׷��� �� queue
        queue.add(0);

        int curNum = queue.poll(), cnt = 1;
        while (!queue.isEmpty()) {
            int num = queue.poll();

            if (curNum != num) { // ������ �ٸ� ���
                next.add(cnt); // ���� ����
                next.add(curNum); // ���� �ѹ�

                curNum = num;
                cnt = 1;
            } else {
                cnt++;
            }
        }

        while (!next.isEmpty()) {
            queue.add(next.poll());
        }
    }
}
