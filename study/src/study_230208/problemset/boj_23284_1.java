package study_230208.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class boj_23284_1 {
	static StringBuilder sb = new StringBuilder();
	static int n;
	static Stack<Integer> st = new Stack<>();
	static List<Integer> arr = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();

		n = Integer.parseInt(br.readLine());

		recursion(1, list, stack);

		System.out.println(sb);
	}

	// ���� ��, ���� ����Ʈ, �� ���� ����
	public static void recursion(int idx, List<Integer> list, Stack<Integer> stack) {
		if (list.size() == n) {
			for (int i : list)
				sb.append(i).append(" ");
			sb.append("\n");

			return;
		}

		// �� ����
		List<Integer> copyList = new ArrayList<>(list);
		Stack<Integer> copyStack = (Stack<Integer>) stack.clone();

		// ���ÿ��� ���� ���� ���, ���� ����Ʈ�� ����
		if (!stack.empty()) {
			list.add(stack.peek());
			stack.pop();
			recursion(idx, list, stack);
		}

		// ���ÿ� ���� �ִ� ���
		if (idx <= n) {
			copyStack.push(idx++);
			recursion(idx, copyList, copyStack);
		}
	}
}
