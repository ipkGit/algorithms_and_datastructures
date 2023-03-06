package org.example.sorts;

public class MergeSort {
    public static  int count = 0;
    public int[] mergeSort(int[] unsortedArray) {

        if (unsortedArray.length < 2) {
            return unsortedArray;
        } else {
            int[] left = new int[unsortedArray.length / 2];
            int[] right = new int[unsortedArray.length - unsortedArray.length / 2];
            System.arraycopy(unsortedArray, 0, left, 0, unsortedArray.length / 2);
            System.arraycopy(unsortedArray, unsortedArray.length / 2, right, 0, unsortedArray.length - unsortedArray.length / 2);
            left = mergeSort(left);
            right = mergeSort(right);
            return merge(left, right);
        }
    }

    public int[] merge(int[] left, int[] right) {
        int[] sorted = new int[left.length + right.length];
        int countLeft = 0;
        int countRight = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (countLeft == left.length) {
                sorted[i] = right[countRight];
                countRight++;
            } else if (countRight == right.length) {
                sorted[i] = left[countLeft];
                countLeft++;
            } else if (left[countLeft] > right[countRight]) {
                sorted[i] = right[countRight];
                countRight++;
                count += left.length - countLeft;

            } else {
                sorted[i] = left[countLeft];
                countLeft++;
            }
        }
        return sorted;
    }
}

