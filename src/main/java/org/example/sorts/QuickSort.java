package org.example.sorts;

/*
В первой строке задано два целых числа
1≤n≤50000 и 1≤m≤50000 — количество отрезков и точек на прямой, соответственно.
Следующие n строк содержат по два целых числа a и b (a ≤b) — координаты концов отрезков.
Последняя строка содержит m целых чисел — координаты точек. Все координаты не превышают 10n8по модулю.
Точка считается принадлежащей отрезку, если она находится внутри него или на границе.
Для каждой точки в порядке появления во вводе выведите, скольким отрезкам она принадлежит.

Sample Input:
2 3
0 5
7 10
1 6 11
Sample Output:
1 0 0
*/

import java.util.Scanner;

public class QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countSegments = scanner.nextInt();
        int countPoints = scanner.nextInt();
        int[] segmentsLeftEdge = new int[countSegments];
        int[] segmentsRightEdge = new int[countSegments];
        int[] points = new int[countPoints];
        for (int i = 0; i < countSegments; i++) {
            segmentsLeftEdge[i] = scanner.nextInt();
            segmentsRightEdge[i] = scanner.nextInt();
        }
        for (int i = 0; i < countPoints; i++) {
            points[i] = scanner.nextInt();
        }

//        Arrays.sort(segmentsLeftEdge);
//        Arrays.sort(segmentsRightEdge);

        quickSort(segmentsLeftEdge, 0, segmentsLeftEdge.length - 1);
        quickSort(segmentsRightEdge, 0, segmentsRightEdge.length - 1);

        countIntersectionsWithBiSearch(points, segmentsLeftEdge, segmentsRightEdge);
    }

    private static void countIntersectionsWithBiSearch(int[] points, int[] leftEdges, int[] rightEdges) {
        for (int p : points) {
            int left = bisectRight(p, leftEdges, 0, leftEdges.length - 1);
            int right = bisectLeft(p, rightEdges, 0, leftEdges.length - 1);
            System.out.print(left - right + " ");
        }
    }

    public static int bisectRight(int point, int[] edges, int low, int high) {
        if (edges.length == 0)
            return 0;

        if (point < edges[low])
            return low;

        if (point >= edges[high])
            return high + 1;

        while (true) {
            if (low + 1 == high)
                return low + 1;

            int mid = (low + high) / 2;

            if (point >= edges[mid])
                low = mid;
            else
                high = mid;
        }
    }

    public static int bisectLeft(int point, int[] edges, int low, int high) {
        if (edges.length == 0)
            return 0;

        if (point < edges[low])
            return low;

        if (point > edges[high])
            return high + 1;

        if (edges.length == 1)
            return 0;

        while (true) {
            if (low + 1 == high)
                return point == edges[low] ? low : low + 1;

            int mid = (low + high) / 2;

            if (point > edges[mid])
                low = mid;
            else
                high = mid;
        }
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;

        int mid = (r - l) / 2 + l;
        int midElem = arr[mid];

        int left = l, right = r;

        while (left <= right) {
            while (arr[left] < midElem) left++;
            while (arr[right] > midElem) right--;


            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }

            if (l < right) quickSort(arr, l, right);
            if (r > left) quickSort(arr, left, r);
        }
    }

    //too slow...
    //    public static void checkIntersection(int[][] segments, int[] points) {
//        for (int point = 0; point < points.length; point++) {
//            int count = 0;
//            for (int i = 0; i < segments.length; i++) {
//                if (points[point] < segments[i][0]) break;
//                if (points[point] > segments[i][1]) continue;
//                count++;
//            }
//            System.out.println(count + " ");
//        }
//    }

}
