package study_230222.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_14865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        Stack<long[]> stack = new Stack<>();
        ArrayList<long[]> pos = new ArrayList<>();
        ArrayList<long[]> list = new ArrayList<>();
        long a = 0; // �ٸ� ���츮�� ���� ���Ե��� �ʴ� ���츮 ����
        long b = 0; // �ٸ� ���츮�� �������� �ʴ� ���츮 ����
        int minNum = Integer.MAX_VALUE, idx = 0; // ���� ������ ���� ã�� ����

        // �Է�
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            pos.add(new long[] { x, y });
            if (x < minNum) { // ���� ���� �� ã�� (x�� ���� ���� ��)
                minNum = x;
                idx = i;
            }
        }

        Collections.rotate(pos, -idx); // x�� ���� ���� ���� �� ������ �̵�
        if (pos.get(0)[0] != pos.get(1)[0]) // x�� ���� �� ��ǥ�� �پ��ְ� �ϱ� ����
            Collections.rotate(pos, 1);
        pos.add(new long[] { pos.get(0)[0], pos.get(0)[1] }); // ���� ���� ���� ���ؼ� �� ���� ���� �ڷ� �־���

        // x��ǥ�� ���� ���� ��, �� y��ǥ�� ��ȣ�� �ٸ��� ���� �� �ۿ� ����
        long check = 0, matchNum = 1;
        for (int i = 0; i < n; i++) {
            long num = pos.get(i)[1] * pos.get(i + 1)[1]; // ��ȣ�� �ٸ���
            if (num < 0) {
                list.add(new long[] { pos.get(i)[0], matchNum }); // ��ġ�Ǵ� ���츮�� ���� ���� ���� (matchNum���� Ȯ��)
                check++;
                if (check % 2 == 0) // �� ���� �־����� ��Ī�� ���̱� ������ matchNum ����
                    matchNum++;
            }
        }

        list.sort((o1, o2) -> o1[0] < o2[0] ? -1 : ((o1[0] == o2[0]) ? 0 : 1)); // x��ǥ �������� ����

        for (int i = 0; i < list.size(); i++) {
            long num = list.get(i)[1]; // matchNum

            // ������ ���������, ���츮�� ���Ե��� �ʴ� ���̱� ������ a++
            if (stack.empty()) {
                stack.push(new long[] { num, 0 }); // 0�� ���츮�� �����ϴ��� üũ�ϱ� ���� ��
                a++;
            } else { // ������ ������� ������
                if (stack.peek()[0] == num) { // peek()�� matchNum�� ���ÿ� �� matchNum�� ������
                    if (stack.peek()[1] == 0) { // ���츮�� �������� ������, b++
                        b++;
                    }

                    stack.pop(); // ���ÿ��� ����
                } else { // �ٸ���, ���츮�� �ȿ� �� �ִٴ� ���̹Ƿ� peek()�� matchNum�� 1�� ����
                    stack.peek()[1] = 1;
                    stack.push(new long[] { num, 0 }); // ���ÿ� �� �߰�
                }
            }
        }

        sb.append(a).append(" ").append(b);
        System.out.println(sb);
    }
}
