package PACKAGE_NAME;

import java.util.*;

public class Algorithm {
    public static void main(String[] args) {
        System.out.println(solve("a"));

    }

    public static String solve(String planText) {
        String[] s = planText.split("");
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {

            int j = i;
            int count = 0;
            do {
                j++;
                count++;
            }
            while (j < s.length && s[i].equals(s[j]));
            result.add(s[i]);
            result.add(String.valueOf(count));
            i = j - 1;

        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < result.size() - 1; i += 2) {
            str.append(result.get(i));
            int temp = Integer.parseInt(result.get(i + 1));
            List<Integer> list = new ArrayList<>();
            do {
                list.add(temp % 16);
                temp = temp / 16;
            } while (temp != 0);
            String a = "";
            for (int j = list.size() - 1; j >= 0; j--) {
                if (list.get(j) <= 9) {
                    a += list.get(j);
                } else switch (list.get(j)) {
                    case 10:
                        a += 'a';
                        break;
                    case 11:
                        a += 'b';
                        break;
                    case 12:
                        a += 'c';
                        break;
                    case 13:
                        a += 'd';
                        break;
                    case 14:
                        a += 'e';
                        break;
                    case 15:
                        a += 'f';
                        break;
                }
            }
            str.append(a);
        }

        str.reverse();
        return String.valueOf(str);
    }

}
