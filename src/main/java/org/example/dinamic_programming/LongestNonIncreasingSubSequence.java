package org.example.dinamic_programming;

/*
Задача на программирование повышенной сложности: наибольшая невозрастающая подпоследовательность
Дано целое число 1≤n≤10n5 и массив A[1…n], содержащий неотрицательные целые числа, не превосходящие 10n9.
Найдите наибольшую НЕвозрастающую подпоследовательность в A.
В первой строке выведите её длину k, во второй — её индексы 1≤i1<i2<…<ik≤n (таким образом,  A[i1]≥A[i2]≥…≥A[in]).
O(nLogN)
Sample Input:
5
5 3 4 4 2

Sample Output:
4
1 3 4 5

Sample Input:
10
7 6 1 6 4 1 2 4 10 1
Sample Output:
6
1 2 4 5 8 10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestNonIncreasingSubSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] nonIncreasingSunSeq = findNonIncreasingSubSeq(values);

        System.out.println(nonIncreasingSunSeq.length);
        Arrays.stream(nonIncreasingSunSeq).forEach(x -> System.out.print(x + " "));
    }

    public static int[] findNonIncreasingSubSeq(int[] values) {
        int[] tail = new int[values.length];
        int[] prev = new int[values.length];

        int len = 0;
        for (int i = 0; i < values.length; i++) {
            int pos = lowerBound(values, tail, len, values[i]);
            if (pos == len) {
                ++len;
            }
            prev[i] = pos > 0 ? tail[pos - 1] : -1;
            tail[pos] = i;
        }

        int[] res = new int[len];
        for (int i = tail[len - 1]; i >= 0; i = prev[i]) {
            --len;
            res[len] = i + 1;
        }
        return res;
    }

    static int lowerBound(int[] a, int[] tail, int len, int key) {
        int low = -1;
        int high = len;
        while (high - low > 1) {
            int mid = (low + high) / 2;
            if (a[tail[mid]] >= key) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }
}

