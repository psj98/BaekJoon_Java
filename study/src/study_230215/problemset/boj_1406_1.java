package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class boj_1406_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> linkedList = new LinkedList<>(); // LinkedList

        String str = br.readLine();
        for (int i = 0; i < str.length(); i++)
            linkedList.add(str.charAt(i)); // �� �ڸ����� Character������ ����

        ListIterator<Character> it = linkedList.listIterator(); // Ŀ���� ���� ListIterator ���
        while (it.hasNext()) { // Ŀ���� �� ���������� �̵�
            it.next();
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            char op = stk.nextToken().charAt(0);

            if (op == 'L') { // Ŀ�� ��������
                if (it.hasPrevious())
                    it.previous();
            } else if (op == 'D') { // Ŀ�� ����������
                if (it.hasNext())
                    it.next();
            } else if (op == 'B') { // Ŀ�� ���� �� ����
                if (it.hasPrevious()) {
                    it.previous();
                    it.remove();
                }
            } else if (op == 'P') { // Ŀ�� ���ʿ� �߰�
                it.add(stk.nextToken().charAt(0));
            }
        }

        it = linkedList.listIterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }

        System.out.println(sb);
    }
}
