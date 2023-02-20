package study_230215.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class boj_5397 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            String str = br.readLine();
            LinkedList<Character> linkedList = new LinkedList<>();
            ListIterator<Character> it = linkedList.listIterator(); // linkedlist�� ��ġ�� �� �� �ִ� iterator

            // ���ڿ��� ���� Ž��
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (c == '<') { // Ŀ�� �������� �̵�
                    if (it.hasPrevious())
                        it.previous();
                } else if (c == '>') { // Ŀ�� ���������� �̵�
                    if (it.hasNext())
                        it.next();
                } else if (c == '-') { // �� �����
                    if (it.hasPrevious()) {
                        it.previous();
                        it.remove();
                    }
                } else { // �� �߰�
                    it.add(c);
                }
            }

            // ��ü �� ����
            it = linkedList.listIterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

}
