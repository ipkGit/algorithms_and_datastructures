package org.example.eager_algos.huffman;

/*
Задача на программирование: декодирование Хаффмана

Восстановите строку по её коду и беспрефиксному коду символов.
В первой строке входного файла заданы два целых числа k и l через пробел — количество различных букв,
встречающихся в строке, и размер получившейся закодированной строки, соответственно. В следующих k строках записаны коды
букв в формате "letter: code". Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке.
В качестве букв могут встречаться лишь строчные буквы латинского алфавита; каждая из этих букв встречается в строке
хотя бы один раз. Наконец, в последней строке записана закодированная строка.
Исходная строка и коды всех букв не пусты. Заданный код таков, что закодированная строка имеет минимальный возможный размер.
В первой строке выходного файла выведите строку s. Она должна состоять из строчных букв латинского алфавита.
Гарантируется, что длина правильного ответа не превосходит 1n4 символов.

Sample Input 1:
1 1
a: 0
0

Sample Output 1:
a

Sample Input 2:
4 14
a: 0
b: 10
c: 110
d: 111
01001100100111

Sample Output 2:
abacabad
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HuffmanDecoder {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        String[] arr = br.readLine().split("\\W+");
        int values = Integer.parseInt(arr[0]); //количество уникальных значений

        BiTreeLeaf tree = new BiTreeLeaf();

        for(int i = 0 ; i < values ; i++) {
            String s = br.readLine(); //получаем значение: код
            reconstructCodeTree(tree, s); //записываем в дерево
        }
        String code = br.readLine(); //закодированная строка
        String result = decoder(code, tree);
        System.out.println(result);
    }

    public static void reconstructCodeTree (BiTreeLeaf tree, String code) {
        BiTreeLeaf temp = tree;
        char c = code.charAt(0);
        String way = code.substring(3);

        for(int i = 0 ; i<way.length() ; i++) {
            if(way.charAt(i) == '0') {
                temp.left = temp.left == null ? new BiTreeLeaf() : temp.left;
                temp = temp.left;
            } else {
                temp.right = temp.right == null ? new BiTreeLeaf() : temp.right;
                temp = temp.right;
            }
        }
        temp.symbol = c;
    }

    public static String decoder(String code, BiTreeLeaf tree) {
        BiTreeLeaf temp = tree;
        StringBuilder result = new StringBuilder();
        for (int i = 0 ; i < code.length() ; i++) {
            if(code.charAt(i) == '0') {
                temp = temp.left;
                if(temp.symbol !=null) {
                    result.append(temp.symbol);
                    temp = tree;
                }
            } else {
                temp = temp.right;
                if(temp.symbol !=null) {
                    result.append(temp.symbol);
                    temp = tree;
                }
            }
        }
        return result.toString();
    }
}


