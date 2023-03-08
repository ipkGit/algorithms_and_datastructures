package org.example.dinamic_programming;

/*
Задача на программирование: наибольшая последовательно кратная подпоследовательность
Дано целое число 1≤n≤10n3 и массив A[1…n] натуральных чисел, не превосходящих 2⋅10n9.
Выведите максимальное 1≤k≤n, для которого найдётся под последовательность 1≤i1<i2<…<ik≤n длины k,
в которой каждый элемент делится на предыдущий (формально: для  всех 1≤j<k,  A[ij]∣A[ij+1]).
Sample Input:
4
3 6 7 12

Sample Output:
3
 */

/*
Задача на программирование повышенной сложности: наибольшая невозрастающая подпоследовательность
Дано целое число 1≤n≤10n5 и массив A[1…n], содержащий неотрицательные целые числа, не превосходящие 10n9.
Найдите наибольшую НЕвозрастающую подпоследовательность в A.
В первой строке выведите её длину k, во второй — её индексы 1≤i1<i2<…<ik≤n (таким образом,  A[i1]≥A[i2]≥…≥A[in]).
Sample Input:
5
5 3 4 4 2

Sample Output:
4
1 3 4 5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class LongestSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lengthSequence = Integer.parseInt(br.readLine());
        int[] sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        findLongestNonIncreasingSubsequence(sequence);
    }

    public static int findLongestNonIncreasingSubsequence(int[] sequence) {
        int[] mapSubsequence = new int[sequence.length];

        for (int i = 0; i < mapSubsequence.length; i++) {
            mapSubsequence[i] = 1;
            for (int j = 0; j < i; j++) {
                if (sequence[j] >= sequence[i] && mapSubsequence[j] + 1 > mapSubsequence[i])
                    mapSubsequence[i] = mapSubsequence[j] + 1;
            }
        }
        int maxSubsequenceLength = 0;
        for (int subsequenceLen : mapSubsequence)
            maxSubsequenceLength = Math.max(maxSubsequenceLength, subsequenceLen);

        System.out.println(maxSubsequenceLength);
        int[] indexes = getIndexOfNonIncreasingSubsequence(sequence, mapSubsequence, maxSubsequenceLength);
        for (int i : indexes) System.out.print(i + " ");
        return maxSubsequenceLength;
    }

    public static int[] getIndexOfNonIncreasingSubsequence(int[] sequence, int[] mapSequence, int lengthSeq) {
        int curr = lengthSeq;
        int[] indexesSubsequence = new int[lengthSeq];
        for (int i = mapSequence.length - 1; i >= 0; i--) {
            if (mapSequence[i] == lengthSeq && lengthSeq == curr) {
                indexesSubsequence[curr - 1] = i + 1;
                curr--;
            }

            if (mapSequence[i] == curr && sequence[i] >= sequence[indexesSubsequence[curr] - 1]) {
                indexesSubsequence[curr - 1] = i + 1;
                curr--;
            }
        }
        return indexesSubsequence;
    }

    public static int findLongestNonDecreasingSubsequence(int[] sequence) {
        int[] mapSubsequence = new int[sequence.length];

        for (int i = 0; i < mapSubsequence.length; i++) {
            mapSubsequence[i] = 1;
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i] && mapSubsequence[j] + 1 > mapSubsequence[i])
                    mapSubsequence[i] = mapSubsequence[j] + 1;
            }
        }
        int maxSubsequenceLength = 0;

        for (int subsequenceLen : mapSubsequence)
            maxSubsequenceLength = Math.max(maxSubsequenceLength, subsequenceLen);

        System.out.println(Arrays.toString(mapSubsequence));
        System.out.println(Arrays.toString(getLongestNonDecreasingSubsequence(sequence, mapSubsequence, maxSubsequenceLength)));
        return maxSubsequenceLength;
    }

    public static int findLongestNonDecreasingAndMultipleSubsequence(int[] sequence) {
        int[] mapSubsequence = new int[sequence.length];
        for (int i = 0; i < mapSubsequence.length; i++) {
            mapSubsequence[i] = 1;
            for (int j = 0; j < i; j++) {
                if ((sequence[i] % sequence[j]) == 0 && mapSubsequence[j] + 1 > mapSubsequence[i])
                    mapSubsequence[i] = mapSubsequence[j] + 1;
            }
        }
        int maxSubsequenceLength = 0;

        for (int subsequenceLen : mapSubsequence)
            maxSubsequenceLength = Math.max(maxSubsequenceLength, subsequenceLen);

        System.out.println(Arrays.toString(mapSubsequence));
        System.out.println(Arrays.toString(getLongestNonDecreasingSubsequence(sequence, mapSubsequence, maxSubsequenceLength)));
        return maxSubsequenceLength;
    }

    public static int[] getLongestNonDecreasingSubsequence(int[] sequence, int[] mapSequence, int lengthSeq) {
        int curr = lengthSeq;
        int[] longestSubsequence = new int[lengthSeq];
        for (int i = mapSequence.length - 1; i >= 0; i--) {
            if (mapSequence[i] == lengthSeq && lengthSeq == curr) {
                longestSubsequence[curr - 1] = sequence[i];
                curr--;
            }

            if (mapSequence[i] == curr && sequence[i] <= longestSubsequence[curr]) {
                longestSubsequence[curr - 1] = sequence[i];
                curr--;
            }
        }
        return longestSubsequence;
    }
}
