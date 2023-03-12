package org.example.dinamic_programming;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Arrays;

/*
Задача на программирование: лестница
Даны число 1≤n≤10n2 ступенек лестницы и целые числа −10n4≤a1,…,an≤10n4, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх (от нулевой доn-й ступеньки),
каждый раз поднимаясь на одну или две ступеньки.
Sample Input 1:
2
1 2

Sample Output 1:
3

Sample Input 2:
2
2 -1

Sample Output 2:
1

Sample Input 3:
3
-1 2 1

Sample Output 3:
3
 */
public class Stairs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rungs = Integer.parseInt(br.readLine());
        int[] costOfSteps = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.print(getMaxLiftAmount(costOfSteps));
    }

    public static int getMaxLiftAmount(int[] costOfSteps) {
        if(costOfSteps.length == 1) return costOfSteps[0];
        int[] costOfWay = new int[costOfSteps.length];
        costOfWay[0] = costOfSteps[0];
        costOfWay[1] = Math.max(costOfSteps[1], costOfSteps[0] + costOfSteps[1]);
        for (int i = 2; i < costOfSteps.length; i++) {
            costOfWay[i] = Math.max(costOfWay[i - 1] + costOfSteps[i], costOfWay[i - 2] + costOfSteps[i]);
        }
        System.out.println(Arrays.toString(costOfSteps));
        System.out.println(Arrays.toString(costOfWay));
        return costOfWay[costOfWay.length - 1];
    }
}
