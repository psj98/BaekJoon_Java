package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_16120 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>(); // ���ĺ��� ���� ���� (A or P)
		boolean check = false;

		String str = br.readLine();

		for (int i = 0; i < str.length(); i++) {
			// A�� ������ ���� ���� P�� ���
			if (str.charAt(i) == 'A' && i + 1 < str.length() && str.charAt(i + 1) == 'P') {
				int size = stack.size();
				
				// ���� �� ���� PP �� ���
				if (size - 2 >= 0 && stack.elementAt(size - 2) == 'P' && stack.elementAt(size - 1) == 'P') {
					stack.pop(); // ������ PP�� �����Ƿ� P �� ���� pop
					i++; // A ������ P�� �� �ʿ� �����Ƿ� ��ŵ
				} else {
					stack.add(str.charAt(i));
				}
			} else {
				stack.add(str.charAt(i));
			}
		}

		// P�� ��� PPAP ����
		if (stack.size() == 1 && stack.peek() == 'P')
			check = true;

		sb.append(check ? "PPAP" : "NP");
		System.out.println(sb);
	}

}

