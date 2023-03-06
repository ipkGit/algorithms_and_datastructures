package org.example.eager_algos.common_points;


/*
Задача на программирование: покрыть отрезки точками
По данным n отрезкам необходимо найти множество точек минимального размера, для которого каждый из отрезков
содержит хотя бы одну из точек.  В первой строке дано число 1≤n≤100 отрезков.
Каждая из последующих n строк содержит по два числа 0≤ l ≤ r ≤10n9, задающих начало и конец отрезка.
Выведите оптимальное число m точек и сами m точек. Если таких множеств точек несколько, выведите любое из них.

Sample Input 1:
3
1 3
2 5
3 6

Sample Output 1:
1
3

Sample Input 2:
4
4 7
1 3
2 5
5 6

Sample Output 2:
2
3 6
 */

import java.util.*;

public class CommonPoints {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int amount = scanner.nextInt();
        List<Line> lines = new ArrayList<>();
        //add
        for (
                int i = 0;
                i < amount; i++) {
            long start = scanner.nextLong();
            long end = scanner.nextLong();
            lines.add(new Line(start, end));
        }
        //sort by end points
        lines.sort(Comparator.comparingLong(a -> a.end));
        //get result list
        List<Long> intersects = getIntersects(lines);
        //show result
        System.out.println(intersects.size());
        for (
                long i : intersects) {
            System.out.print(i + " ");
        }

    }

    public static List<Long> getIntersects(List<Line> lines) {
        Long edge = null;
        List<Long> intersects = new ArrayList<>();

        for (Line line : lines) {
            if (edge == null) {
                edge = line.end;
                intersects.add(edge);
            }
            if (edge < line.start) {
                edge = line.end;
                intersects.add(edge);
            }
        }
        return intersects;
    }
}

class Line {
    public long start;
    public long end;

    public Line(long start, long end) {
        this.start = start;
        this.end = end;
    }
}
