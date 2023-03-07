package org.example.dinamic_programming;

/*
Задача на программирование: расстояние редактирования

Вычислите расстояние редактирования двух данных непустых строк длины не более 10n2,
содержащих строчные буквы латинского алфавита.

Sample Input 1:
editing
distance

Sample Output 1:
5

Sample Input 2:
short
ports

Sample Output 2:
3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditDistance {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] first = br.readLine().toCharArray();
        char[] second = br.readLine().toCharArray();
        System.out.print(editDistance(first, second));
    }

    public static int editDistance(char[] first, char[] second) {
        int[][] matrix = new int[first.length + 1][second.length + 1];
        for (int i = 0; i <= first.length; i++) {
            matrix[i][0] = i;
        }
        for (int i = 0; i <= second.length; i++) {
            matrix[0][i] = i;
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                int diff = diff(first[i-1], second[j-1]);
                matrix[i][j] = Math.min(Math.min(matrix[i-1][j]+1, matrix[i][j-1]+1), matrix[i-1][j-1]+diff);
            }
        }
        showMatrix(matrix);
        return matrix[first.length][second.length];
    }

    private static int diff(char c1, char c2) {
        if (c1 == c2) return 0;
        else return 1;
    }

    public static void showMatrix(int[][] matrix) {

        for (int i = 0; i < matrix[1].length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int[] ints : matrix) {
                sb.append(ints[i]).append(" ");
            }
            System.out.println(sb);
        }
    }
}
