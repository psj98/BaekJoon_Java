package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_20056 {
    static int n;
    static ArrayList<FireBall> fireBalls; // ���̾ ���� ����Ʈ
    static ArrayList<FireBall>[][] moveMap; // ���̾ �̵� ��, ��
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }; // ���̾ ���� ����
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

    // ���̾ Ŭ����
    static class FireBall {
        int x, y, mass, speed, dir;

        FireBall(int x, int y, int mass, int speed, int dir) {
            this.x = x;
            this.y = y;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // �Է�
        n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int k = Integer.parseInt(stk.nextToken());

        // �ʱ�ȭ
        fireBalls = new ArrayList<>();
        moveMap = new ArrayList[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                moveMap[i][j] = new ArrayList<>();

        // ���̾ �Է�
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int mass = Integer.parseInt(stk.nextToken());
            int speed = Integer.parseInt(stk.nextToken());
            int dir = Integer.parseInt(stk.nextToken());

            fireBalls.add(new FireBall(x, y, mass, speed, dir)); // ���̾ ����
        }

        // k��ŭ ���̾ �����̰� ��Ʈ����
        for (int i = 0; i < k; i++) {
            move();
            spread();
        }

        // ���� �� ���
        int sum = 0;
        for (FireBall fireBall : fireBalls)
            sum += fireBall.mass;

        sb.append(sum);
        System.out.println(sb);
    }

    // ���̾ �̵�
    static void move() {
        for (FireBall fireBall : fireBalls) {
            // ���̾ ��ǥ �̵�
            fireBall.x = (fireBall.x + dx[fireBall.dir] * fireBall.speed) % n;
            fireBall.y = (fireBall.y + dy[fireBall.dir] * fireBall.speed) % n;

            if (fireBall.x < 0)
                fireBall.x += n;
            if (fireBall.y < 0)
                fireBall.y += n;

            moveMap[fireBall.x][fireBall.y].add(fireBall); // moveMap�� �̵��� ���̾ ����
        }

        fireBalls.clear(); // ���̾ ����Ʈ ���
    }

    // ���̾ ��Ʈ����
    static void spread() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (moveMap[i][j].size() >= 2) { // ���̾�� 2�� �̻��� ��
                    int mass = 0;
                    int speed = 0;
                    boolean even = false, odd = false; // ���� ¦��, Ȧ�� üũ

                    // ���̾���� ����, �ӵ� �� �� ���� ¦��, Ȧ�� ���� üũ
                    for (FireBall fireBall : moveMap[i][j]) {
                        mass += fireBall.mass;
                        speed += fireBall.speed;

                        if (fireBall.dir % 2 == 0)
                            even = true;
                        else
                            odd = true;
                    }

                    mass /= 5; // ���� ���
                    speed /= moveMap[i][j].size(); // �ӵ� ���

                    // ������ 0�̸� ���̾ �����
                    if (mass == 0) {
                        moveMap[i][j].clear();
                        continue;
                    }

                    if (even && odd) { // ¦��, Ȧ�� �� �� ������ 1, 3, 5, 7�� ����
                        for (int k = 1; k < 8; k += 2)
                            fireBalls.add(new FireBall(i, j, mass, speed, k));
                    } else { // ¦�� �Ǵ� Ȧ���� ������ 0, 2, 4, 6���� ����
                        for (int k = 0; k < 8; k += 2)
                            fireBalls.add(new FireBall(i, j, mass, speed, k));
                    }
                } else if (moveMap[i][j].size() == 1) { // ���̾�� �ϳ��� �ִ� ���, �״�� ����
                    fireBalls.add(moveMap[i][j].get(0));
                }

                moveMap[i][j].clear(); // ���̾ �̵� �� �ʱ�ȭ
            }
        }
    }
}
