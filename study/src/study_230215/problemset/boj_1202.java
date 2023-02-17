package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1202 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		ArrayList<int[]> gem = new ArrayList<>(); // ���� ���� (����, ����)
		PriorityQueue<Integer> bag = new PriorityQueue<>(); // ���� ����
		PriorityQueue<Integer> selectGem = new PriorityQueue<>(Collections.reverseOrder()); // ���� ����

		int gemNum = Integer.parseInt(stk.nextToken()); // ���� ����
		int bagNum = Integer.parseInt(stk.nextToken()); // ���� ����

		// gem�� ���� ���� ����
		for (int i = 0; i < gemNum; i++) {
			stk = new StringTokenizer(br.readLine());
			int gemWeight = Integer.parseInt(stk.nextToken());
			int gemValue = Integer.parseInt(stk.nextToken());
			gem.add(new int[] { gemWeight, gemValue });
		}

		// ���԰� ���� ������ ���� (compare -1, 0, 1 ����)
		Collections.sort(gem, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		// ���� ���� �������� ����
		for (int i = 0; i < bagNum; i++)
			bag.add(Integer.parseInt(br.readLine()));

		long sum = 0; // ��
		int idx = 0; // ���� index
		while (!bag.isEmpty()) {
			int bagWeight = bag.peek(); // ���� ����
			for (int i = idx; i < gemNum; i++) {
				if (gem.get(i)[0] > bagWeight) // ���� ���Ժ��� ũ�� break
					break;

				// ���� ���Ժ��� ������ selectGem�� ���� ���ݸ� �߰�
				selectGem.add(gem.get(i)[1]); // PriorityQueue (��������)
				idx++;
			}

			// size�� 0�� �ƴϸ� sum�� �߰�
			if (selectGem.size() != 0) {
				sum += (long) selectGem.poll();
			}

			bag.poll();
		}

		sb.append(sum);
		System.out.println(sb);
	}

}

