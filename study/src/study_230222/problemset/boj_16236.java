package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16236 {
	static int n, sharkX, sharkY, babySize = 2, fishCnt = 0, time = 0; // �� ũ��, ��� ��ġ, ��� ũ��, ���� ����� ����, �ð�
	static int[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean meet = false; // �Ʊ� �� ����Ⱑ �ִµ� ����⸦ ���� ���� ��� üũ
	static ArrayList<Fish>[] fish; // ����� ũ�� �� ��ġ

	// ����� ��ġ
	static class Fish {
		int x;
		int y;

		public Fish(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		fish = new ArrayList[7]; // �ʱ�ȭ (����� ũ�� 1 ~ 6)
		for (int i = 0; i < fish.length; i++)
			fish[i] = new ArrayList<>();

		// �� ���� ����
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 6) // ����� ũ�⿡ ���� ��ġ ����
					fish[map[i][j]].add(new Fish(i, j));
				if (map[i][j] == 9) { // ��� �ʱ� ��ġ ����
					sharkX = i;
					sharkY = j;
				}
			}
		}

		// ������� ������ 0�̸� Ž�� X -> 0 ���
		if (cntFish(7) == 0) {
			System.out.println(0);
			System.exit(0);
		}

		// �Ʊ� �� ���� �� �ִ� ����Ⱑ ������ && ����⸦ ���� �� ������
		while (cntFish(babySize) > 0 && !meet) {
			eatFish(); // ����� �Ա�
		}

		sb.append(time);
		System.out.println(sb);
	}

	// ����� �Ա�
	static void eatFish() {
		int x = 0, y = 0; // �� �ű� ��ǥ
		int size = 0, fishIdx = 0; // ����� ũ��, ����� ũ�� List�� idx
		int minDist = Integer.MAX_VALUE; // �ּ� �Ÿ�
		int minMove = 0; // ���� ���������� �Ÿ�

		// �Ʊ� ��� ũ�⺸�� ���� ���
		for (int i = 1; i < babySize; i++) {
			for (int j = 0; j < fish[i].size(); j++) {
				// ������� ��ġ
				int fishX = fish[i].get(j).x;
				int fishY = fish[i].get(j).y;

				minMove = move(fishX, fishY, sharkX, sharkY); // ���� ���������� �Ÿ� (BFS)
				if (minMove < minDist) { // �Ÿ��� ���� ���, ����
					minDist = minMove;
					x = fishX;
					y = fishY;
					size = i;
					fishIdx = j;
				} else if (minMove == minDist) { // �Ÿ��� ���� ���
					if (x == fishX) { // x��ǥ(��)�� ���� ���
						if (y > fishY) { // y��ǥ�� ���� ������ ���� (���� ����)
							x = fishX;
							y = fishY;
							size = i;
							fishIdx = j;
						}
					} else if (x > fishX) { // x��ǥ(��)�� �ٸ� ���, x��ǥ�� ���� ������ ���� (���� ��)
						x = fishX;
						y = fishY;
						size = i;
						fishIdx = j;
					}
				}
			}
		}

		// ����⸦ ���� ���� ���, �������� �ʰ� Ż��
		if (minDist == Integer.MAX_VALUE) {
			meet = true;
			return;
		}

		time += minDist; // �ð�
		fishCnt++; // ���� ����� ���� ����

		map[sharkX][sharkY] = 0; // ��� ��ġ �ű��
		map[x][y] = 9;
		sharkX = x;
		sharkY = y;
		fish[size].remove(fishIdx); // ����� ���ֱ�

		// ��� ũ��� ���� ����� ������ ���� ���, ��� ũ�� +1
		if (babySize == fishCnt) {
			babySize++;
			if (babySize > 6) // �� 6���� Ŀ���� ���, 7�� ����
				babySize = 7;
			fishCnt = 0; // ���� ����� ���� 0���� ����
		}
	}

	// ����� ���� ����
	static int cntFish(int size) {
		int sum = 0;
		for (int i = 1; i < size; i++)
			sum += fish[i].size();

		return sum;
	}

	// �Ÿ� ���
	static int move(int ax, int ay, int bx, int by) {
		boolean[][] visited = new boolean[n][n];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { bx, by });
		visited[bx][by] = true;
		int moveCnt = 0; // �Ÿ�
		boolean check = false; // �� ����� ��ġ�� ������ üũ

		while (!queue.isEmpty()) {
			int qSize = queue.size();
			for (int i = 0; i < qSize; i++) {
				int curX = queue.peek()[0];
				int curY = queue.poll()[1];

				// �� ����� ��ġ�� ���� ���
				if (curX == ax && curY == ay) {
					check = true;
					break;
				}

				for (int j = 0; j < 4; j++) {
					int nx = curX + dx[j];
					int ny = curY + dy[j];

					// ��� ũ�⺸�� �۰ų� ���� ���� �̵� ����
					if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] <= babySize && !visited[nx][ny]) {
						queue.add(new int[] { nx, ny });
						visited[nx][ny] = true;
					}
				}
			}

			// ����� ��ġ�� ���� ���, while�� Ż��
			if (check)
				break;

			moveCnt++; // �Ÿ� ����
		}

		// ����� ��ġ�� �� ���� ���
		if (!check)
			moveCnt = Integer.MAX_VALUE;

		return moveCnt;
	}
}
