package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj_2565 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n];

		ArrayList<int[]> pos = new ArrayList<>(); // ������ ��ǥ
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			pos.add(new int[] { x, y }); // ������ ��ǥ ����
		}

		// x��ǥ ���� ����
		Collections.sort(pos, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});

		// ������ ������ ���� Ž��
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (pos.get(i)[1] < pos.get(j)[1]) // ���� y��ǥ���� ū ��� -> max �� ����
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}

		// ������ ������ ���� �� �ִ� ã��
		for (int i = 1; i < n; i++)
			dp[0] = Math.max(dp[0], dp[i]);

		sb.append(n - dp[0]); // �����ؾ� �� ������ ���� ���
		System.out.println(sb);
	}

}

