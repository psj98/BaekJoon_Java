package study_230302.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class boj_4195 {
	static int[] parents; // �θ� ���
	static int[] size; // �� ���� �� ũ��

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine()); // testcase
		for (int tc = 1; tc <= t; tc++) {
			Map<String, Integer> name = new HashMap<>(); // (�̸�, idx)
			int idx = 0; // �� �̸� �� idx
			int n = Integer.parseInt(br.readLine());

			makeSet(); // �ʱ�ȭ

			for (int i = 0; i < n; i++) {
				String[] person = br.readLine().split(" ");
				int personIdx1 = -1, personIdx2 = -1; // �̸� idx

				// �̹� �̸��� ������ �ش� value�� ������
				// �̸��� ������ idx�� �����ϰ� map�� �߰�
				if (!name.containsKey(person[0])) {
					personIdx1 = idx;
					name.put(person[0], idx++);
				} else {
					personIdx1 = name.get(person[0]);
				}

				if (!name.containsKey(person[1])) {
					personIdx2 = idx;
					name.put(person[1], idx++);
				} else {
					personIdx2 = name.get(person[1]);
				}

				union(personIdx1, personIdx2); // union

				sb.append(size[findSet(personIdx1)]).append("\n"); // ũ�� ��������
			}
		}

		System.out.println(sb);
	}

	// �ʱ�ȭ -> �̸� �ִ� 200000��
	static void makeSet() {
		parents = new int[200001];
		size = new int[200001];

		for (int i = 0; i < 200001; i++) {
			parents[i] = i;
			size[i] = 1;
		}
	}

	// findset
	static int findSet(int num) {
		if (num == parents[num])
			return num;
		return parents[num] = findSet(parents[num]); // rank ����
	}

	// union
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		// ������ �ٲ� �ʿ� ����
		if (aRoot == bRoot)
			return;

		// �θ� ���� ��ȣ�� ���� ������ union
		if (aRoot < bRoot) {
			parents[bRoot] = aRoot; // �θ� ���� ����
			size[aRoot] += size[bRoot]; // ũ�� ���� -> ���� �ʿ� �ٿ����Ƿ� ���� ������ ������ �߰�
			size[bRoot] = 0; // ū �� ������ 0
		} else {
			parents[aRoot] = bRoot;
			size[bRoot] += size[aRoot];
			size[aRoot] = 0;
		}
	}
}
