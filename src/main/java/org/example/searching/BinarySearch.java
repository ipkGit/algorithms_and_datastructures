package org.example.searching;


public class BinarySearch {
    public int binarySearch(int[] sortedArray, int searchThis) {
        int position;
        int min = 0;
        int max = sortedArray.length - 1;

        while (min <= max) {
            position = (min + max) / 2;
            if (sortedArray[position] == searchThis) {
                return position;
            } else if (sortedArray[position] < searchThis) {
                min = position + 1;
            } else if (sortedArray[position] > searchThis) {
                max = position - 1;
            }
        }
        return -1;
    }
}
