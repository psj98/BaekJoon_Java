package study_230308.problemset;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_2812_1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		Integer.parseInt(stk.nextToken()); // ���ڿ� ����
		int k = Integer.parseInt(stk.nextToken()); // �������ϴ� ���� ����

		String str = br.readLine(); // ���ڿ�
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i); // ���� ����
			if (stack.isEmpty()) { // ������ ��������� ���� ����
				stack.add(c);
				continue;
			}

			// ���� ���� ���� �ȿ� �ִ� ������ Ŭ ���, ũ�ų� ���� ���� ���� ������ �� ��
			// k�� 0�� �Ǹ� �� �ʿ� ����
			while (k != 0 && !stack.isEmpty() && stack.peek() < c) {
				stack.pop();
				k--;
			}

			stack.add(c); // ���� �� ����
		}

		// k�� 0�� �ƴ� ��� üũ -> �� �պ��� stack ���� - k ���� sb�� ����
		// sb.insert()�� array�� copy�ؿͼ� �տ� �ֱ� ������ ���� �ɸ�
		while (!stack.isEmpty()) {
			if (k != 0) {
				k--;
				stack.pop();
			} else
				sb.insert(0, stack.pop());
		}

		System.out.println(sb); // ���
	}
}
