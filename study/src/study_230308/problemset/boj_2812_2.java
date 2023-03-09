package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_2812_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();

		int n = Integer.parseInt(stk.nextToken()); // ���ڿ� ����
		int k = Integer.parseInt(stk.nextToken()); // �������ϴ� ���� ����

		String str = br.readLine(); // ���ڿ�
		int[] nums = new int[n]; // ���ڿ��� ���ڷ� ������ �迭
		for (int i = 0; i < n; i++)
			nums[i] = str.charAt(i) - '0';

		for (int i = 0; i < n; i++) {
			if (stack.isEmpty()) { // ������ ��������� ���� ����
				stack.add(nums[i]);
				continue;
			}

			// ���� ���� ���� �ȿ� �ִ� ������ Ŭ ���, ũ�ų� ���� ���� ���� ������ �� ��
			// k�� 0�� �Ǹ� �� �ʿ� ����
			while (k != 0 && !stack.isEmpty() && stack.peek() < nums[i]) {
				stack.pop();
				k--;
			}

			stack.add(nums[i]); // ���� �� ����
		}

		// stack ���� - k ���� sb�� ����
		for (int i = 0; i < stack.size() - k; i++)
			sb.append(stack.get(i));

		System.out.println(sb);
	}
}
