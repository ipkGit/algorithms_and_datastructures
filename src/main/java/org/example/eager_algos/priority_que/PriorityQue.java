package org.example.eager_algos.priority_que;


/*
Задача на программирование: очередь с приоритетами

Первая строка входа содержит число операций 1≤n≤1n5.
Каждая из последующих n строк задают операцию одного из следующих двух типов:
•	Insert x, где 0≤x≤1n9 — целое число;
•	ExtractMax.
Первая операция добавляет число x в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.

Sample Input:
6
Insert 200
Insert 10
ExtractMax
Insert 5
Insert 500
ExtractMax

Sample Output:
200
500
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PriorityQue {

    public static void main(String[] args) throws IOException {
        PriorityQue priorQue = new PriorityQue();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int operations = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < operations ; i++) {
            String operation = br.readLine();
            if(operation.contains("Insert")) {
                int ins = Integer.parseInt(operation.substring(7));
                priorQue.insert(ins);
            }
            if(operation.contains("ExtractMax")) {
                System.out.println(priorQue.extractMax());
            }
        }
    }
    int[] arr = new int[10];
    int size = 0;

    public void insert (int i) {
        if(arr.length == size) {
            arr = Arrays.copyOf(arr, arr.length*2);
        }
        arr[size] = i;
        size++;
        condition (size-1);

    }

    public int extractMax () {
        int max = 0;
        for(int i = size-1; i >= size/2 ; i--) {
            if(arr[i] > arr[max]) {
                max = i;
            }
        }
        int result = arr[max];
        arr[max] = arr[size-1];
        size--;
        condition (max);
        return result;
    }

    public void condition (int pos) {
        if (arr[pos] < arr[pos/2]) {
            int temp = arr[pos];
            arr[pos] = arr[pos/2];
            arr[pos/2] = temp;
            if(arr[pos/2] < arr[pos/4]) {
                condition(pos/2);
            }
        }
    }
}
