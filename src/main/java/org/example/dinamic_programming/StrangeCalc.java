package org.example.dinamic_programming;

/*
У вас есть примитивный калькулятор, который умеет выполнять всего три операции с текущим числом x:
заменить x на 2x, 3x или x+1. По данному целому числу 1≤n≤10n5 определите минимальное число операций k,
необходимое, чтобы получить n из 1. Выведите k и последовательность промежуточных чисел.
Sample Input 1:
1

Sample Output 1:
0
1

Sample Input 2:
5

Sample Output 2:
3
1 2 4 5

Sample Input 3:
96234

Sample Output 3:
14
1 3 9 10 11 22 66 198 594 1782 5346 16038 16039 32078 96234
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StrangeCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        iHaveNoIdeaHowToNameThisMethod(num);
    }

    public static void iHaveNoIdeaHowToNameThisMethod(int num) {
        if (num == 1) {
            System.out.println(0 + "\n" + 1);
            return;
        }
        int[][] steps = new int[num+1][3];
        for (int i = 2; i < steps.length; i++) {
            steps[i][0] = i % 2 == 0 ? Math.min(Math.min(steps[i / 2][0], steps[i / 2][1]), steps[i / 2][2]) + 1 : Integer.MAX_VALUE;
            steps[i][1] = i % 3 == 0 ? Math.min(Math.min(steps[i / 3][0], steps[i / 3][1]), steps[i / 3][2]) + 1 : Integer.MAX_VALUE;
            steps[i][2] = Math.min(Math.min(steps[i - 1][0], steps[i - 1][1]), steps[i - 1][2]) + 1;
        }
        int minSteps = Math.min(Math.min(steps[num][0], steps[num][1]), steps[num][2]);
        System.out.println(minSteps);
        getOrder(steps);
    }

    public static void getOrder(int[][] steps) {
        List<Integer> values = new ArrayList<>();
        for (int i = steps.length - 1; i > 1; ) {
            values.add(i);
            i = steps[i][0] < steps[i][1] ? steps[i][0] <= steps[i][2] ? i / 2 : i - 1 : steps[i][1] <= steps[i][2] ? i / 3 : i - 1;
        }
        System.out.print(1 + " ");
        values.stream().sorted().forEach(integer -> System.out.print(integer + " "));
    }
}
