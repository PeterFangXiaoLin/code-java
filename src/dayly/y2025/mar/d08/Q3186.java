package dayly.y2025.mar.d08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q3186 {
    /**
     * 递归 + 记忆化搜索
     */
    public long maximumTotalDamage2(int[] power) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum);
        }

        int[] a = new int[cnt.size()];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }
        Arrays.sort(a);

        int n = a.length;
        long[] memo = new long[n];
        Arrays.fill(memo, -1);
        return dfs(a, cnt, n - 1, memo);
    }

    private long dfs(int[] a, Map<Integer, Integer> cnt, int i, long[] memo) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }

        int j = i;
        int x = a[i];
        while (j > 0 && a[j - 1] >= x - 2) {
            j--;
        }

        return memo[i] = Math.max(dfs(a, cnt, i - 1, memo), dfs(a, cnt, j - 1, memo) + (long)a[i] * cnt.get(a[i]));
    }


    /**
     * 递推
     *
     * @param power
     * @return
     */
    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : power) {
            cnt.merge(x, 1, Integer::sum);
        }

        int n = cnt.size();
        int[] a = new int[n];
        int k = 0;
        for (int x : cnt.keySet()) {
            a[k++] = x;
        }

        Arrays.sort(a);

        int j = 0;
        long[] f = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int x = a[i];
            while (a[j] < x - 2) {
                j++;
            }
            f[i + 1] = Math.max(f[i], f[j] + (long)x * cnt.get(x));
        }

        return f[n];
    }
}
