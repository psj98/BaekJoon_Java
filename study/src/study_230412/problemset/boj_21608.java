package study_230412.problemset;

import java.util.*;
import java.io.*;

public class boj_21608 {
    static int n;
    static int[][] map;
    static int[][] emptySeat;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static HashMap<Integer, Student> list;

    static class Student {
        int x, y; // ��ǥ
        int[] friends; // �����ϴ� ģ�� �迭

        Student(int x, int y, int[] friends) {
            this.x = x;
            this.y = y;
            this.friends = friends;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int ans = 0;
        int[] score = { 0, 1, 10, 100, 1000 };

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        emptySeat = new int[n][n];
        list = new HashMap<>();

        findEmptySeat(); // ���ڸ� ã�� -> ��ġ�� ������ �ֺ� ������ �� --

        for (int i = 0; i < n * n; i++) {
            stk = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stk.nextToken());

            // ģ�� ��ȣ ����
            int[] friends = new int[4];
            for (int j = 0; j < 4; j++)
                friends[j] = Integer.parseInt(stk.nextToken());

            if (i == 0) {
                map[1][1] = num;
                list.put(num, new Student(1, 1, friends));

                for (int j = 0; j < 4; j++)
                    emptySeat[1 + dx[j]][1 + dy[j]]--;

                continue;
            }

            findSeat(num, friends);
        }

        for (int i = 1; i <= n * n; i++) {
            int cnt = 0;
            Student student = list.get(i);

            for (int friend : student.friends) {
                int dist = Math.abs(student.x - list.get(friend).x)
                        + Math.abs(student.y - list.get(friend).y);
                if (dist == 1)
                    cnt++;
            }

            ans += score[cnt];
        }

        sb.append(ans);
        System.out.println(sb);
    }

    // �� �ڸ� ã��
    static void findEmptySeat() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 4;

                if (i == 0 || i == n - 1)
                    cnt--;
                if (j == 0 || j == n - 1)
                    cnt--;

                emptySeat[i][j] = cnt;
            }
        }
    }

    // ��ġ�� �ڸ� ã��
    static void findSeat(int num, int[] friends) {
        int[][] nextToFriend = new int[n][n];

        // ��ġ�� �л��鿡�� ������ �� ���� ����
        for (int friend : friends) {
            if (!list.containsKey(friend))
                continue;

            Student student = list.get(friend); // �����ϴ� �л� ����
            for (int i = 0; i < 4; i++) {
                int nx = student.x + dx[i];
                int ny = student.y + dy[i];

                // ���� üũ
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                // ��� �ִ� �� üũ
                if (map[nx][ny] != 0)
                    continue;

                nextToFriend[nx][ny]++; // ������ �� ����
            }
        }

        int x = -1, y = -1, maxCnt = -1, maxEmptySeat = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) // �����ϴ� �л��� ��ġ�� �ȵǾ����� �� ���� -> ��ġ�� �� ����
                    continue;

                // 1. ����ִ� ĭ -> �����ϴ� �л��� ���� ���� ������ ĭ ���ϱ�
                if (maxCnt < nextToFriend[i][j]) {
                    x = i;
                    y = j;
                    maxCnt = nextToFriend[i][j];
                    maxEmptySeat = emptySeat[i][j];
                } else if (maxCnt == nextToFriend[i][j]) { // 2. 1������ ����ִ� ĭ�� ���� ĭ
                    if (maxEmptySeat < emptySeat[i][j]) {
                        x = i;
                        y = j;
                        maxEmptySeat = emptySeat[i][j];
                    }

                    // 3. 2������ �� ��ȣ ���� ĭ -> �� ��ȣ ���� ĭ -> ��, �� ���� �������� Ž���ϹǷ� üũ X
                }
            }
        }

        map[x][y] = num;
        list.put(num, new Student(x, y, friends));

        // ��ġ ���� ��, ������ �� ���� ���̱�
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // ���� üũ
            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;

            // ��� �ִ� �� üũ
            if (map[nx][ny] != 0)
                continue;

            emptySeat[nx][ny]--;
        }
    }
}
