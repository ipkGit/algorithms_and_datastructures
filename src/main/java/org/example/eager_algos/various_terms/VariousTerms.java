package org.example.eager_algos.various_terms;


/*
Задача на программирование: различные слагаемые
По данному числу 1≤n≤10n9 найдите максимальное число k, для которого n можно представить как сумму k различных
натуральных слагаемых. Выведите в первой строке число k, во второй — k слагаемых.

Sample Input 1:
4

Sample Output 1:
2
1 3

Sample Input 2:
6

Sample Output 2:
3
1 2 3
 */
import java.util.*;

public class VariousTerms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextLong();
        Set<Long> terms = uniqTerms(num);
        System.out.println(terms.size());
        for(long l : terms) {
            System.out.print(l + " ");
        }
    }

    public static Set<Long> uniqTerms (long num) {
        Set<Long> terms = new HashSet<>();
        long sumTerms = 0;
        long count = 1;
        while(sumTerms != num) {
            terms.add(count);
            sumTerms += count;
            count++;
            if(num < sumTerms) {
                terms.remove(sumTerms - num);
                return terms;
            }
        }
        return terms;
    }
}
