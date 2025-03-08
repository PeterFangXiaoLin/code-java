package dayly.y2025.mar.d08;

import java.util.*;

public class MeiTuan {
    public void one(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            String str = scanner.next();
            char[] s = str.toCharArray();
            StringBuilder ans = new StringBuilder();
            int p = 0;
            for (int i = 0; i < s.length; i++) {
                char c = s[i];
                if (Character.isDigit(c)) {
                    int num = c - '0';
                    if (p == 0) {
                        p = num;
                    } else {
                        p = p * 10 + num;
                    }
                } else {
                    ans = new StringBuilder(ans.substring(p) + ans.substring(0, p));
                    if (c == 'R') {
                        ans = ans.reverse();
                    } else {
                        ans.append(c);
                    }
                }
            }

            System.out.println(ans.toString());
        }
    }

    public void two(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        Map<Integer, TreeSet<Integer>> xMap = new HashMap<>();
        Map<Integer, TreeSet<Integer>> yMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
            xMap.computeIfAbsent(x[i], k -> new TreeSet<>()).add(y[i]);
            yMap.computeIfAbsent(y[i], k -> new TreeSet<>()).add(x[i]);
        }

        for (int i = 0; i < n; i++) {
            TreeSet<Integer> ySet = xMap.get(x[i]);
            TreeSet<Integer> xSet = yMap.get(y[i]);

            List<Integer> yList = new ArrayList<>(ySet);
            List<Integer> xList = new ArrayList<>(xSet);

            int ans = 0;
            int xLen = xList.size(), yLen = yList.size();
            if (yLen > 2 && yList.get(yList.size() - 1) > y[i] && yList.get(yList.size() - 2) > y[i]) {
                ans++;
            }
            if (xLen > 2 && xList.get(xList.size() - 1) > x[i] && xList.get(xList.size() - 2) > x[i]) {
                ans++;
            }
            if (yLen > 2 && yList.get(0) < y[i] && yList.get(1) < y[i]) {
                ans++;
            }
            if (xLen > 2 && xList.get(0) < x[i] && xList.get(1) < x[i]) {
                ans++;
            }
            System.out.println(ans);
        }
    }
}
