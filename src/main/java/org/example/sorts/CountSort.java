package org.example.sorts;

/*
Первая строка содержит число 1≤≤n≤10n4, вторая — n натуральных чисел, не превышающих 10.
Выведите упорядоченную по не убыванию последовательность этих чисел.

Sample Input:
10
2 3 1 9 3 3 2 10 9 8

Sample Output:
2 2 3 9 10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CountSort {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String numbers = br.readLine();
        int[] unsortedArr = Arrays.stream(numbers.split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Arrays.stream(countSort(unsortedArr,10)).forEach(i -> System.out.print(i + " "));
    }

    public static int[] countSort(int[] unsortedArr, int uniq) {
        int[] frequency = new int[uniq + 1];
        int[] result = new int[unsortedArr.length];
        Arrays.stream(unsortedArr).forEach(n -> frequency[n] += 1);
        int count = 0;
        for (int i = 1; i < frequency.length; i++) {
            for(int j = 0; j < frequency[i]; j++) {
                result[count] = i;
                count++;
            }
        }
        return result;
    }
}
