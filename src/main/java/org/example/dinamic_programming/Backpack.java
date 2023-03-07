package org.example.dinamic_programming;


/*
Первая строка входа содержит целые числа 1≤W≤10n4 и 1≤n≤300 — вместимость рюкзака и число золотых слитков.
Следующая строка содержит n целых чисел 0≤w1,…,wn≤10n5, задающих веса слитков.
Найдите максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8

Sample Output:
9
 */

import java.util.Scanner;

import static org.example.dinamic_programming.EditDistance.showMatrix;

public class Backpack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = scanner.nextInt();
        int countGoldBars = scanner.nextInt();
        int[] goldBars = new int[countGoldBars];
        for (int i = 0; i < countGoldBars; i++) {
            goldBars[i] = scanner.nextInt();
        }

        System.out.println(howManyGoldYouCanTake(capacity, goldBars));

    }

    public static int howManyGoldYouCanTake(int capacity, int[] goldBars) {
        int[][] matrix = new int[capacity + 1][goldBars.length + 1];

        for (int i = 1; i <= goldBars.length; i++) {
            for (int j = 1; j <= capacity; j++) {

                matrix[j][i] = matrix[j][i-1];
                if(goldBars[i-1] <= j) {
                    matrix[j][i] = Math.max(matrix[j][i], matrix[j-goldBars[i-1]][i-1] + goldBars[i-1]);
                }
            }
        }
        showMatrix(matrix);

        return matrix[matrix.length-1][matrix[0].length-1];

    }
}
