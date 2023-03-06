package org.example.eager_algos.huffman;

/*
    По данной непустой строке s длины не более 10n4, состоящей из строчных букв латинского алфавита,
    постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв k,
    встречающихся в строке, и размер получившейся закодированной строки.
    В следующих k строках запишите коды букв в формате "letter: code".
    В последней строке выведите закодированную строку.

    Sample Input 1:
    a

    Sample Output 1:
    1 1
    a: 0
    0

    Sample Input 2:
    abacabad

    Sample Output 2:
    4 14
    a: 0
    b: 10
    c: 110
    d: 111
    01001100100111
     */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HuffmanCoding {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        //символы строки с частотой
        Map<Character, Integer> dictWithFreq = dictionaryWithFrequency(text);
        //список "листьев"
        ArrayList<BiTreeLeaf> dictionary = new ArrayList<>();
        for (Character c : dictWithFreq.keySet()) {
            dictionary.add(new BiTreeLeaf(c, dictWithFreq.get(c)));
        }
        //дерево наших символов собранное по хаффману
        BiTreeLeaf tree = huffman(dictionary);
        //словарь с кодами
        Map<Character, String> codes = new HashMap<>();
        //собираем словарь
        for (Character ch : dictWithFreq.keySet()) {
            String code = tree.getCodeToChar("", ch);
            if(code.isEmpty()) {
                code = "0";
            }
            codes.put(ch, code);
        }
        //собираем закодированную строку по словарю
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < text.length(); i++) {
            result.append(codes.get(text.charAt(i)));
        }
        //результат
        System.out.println(codes.size() + " " + result.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println(result);
    }

    //сборка дерева по алгоритму хоффмана
    public static BiTreeLeaf huffman(ArrayList<BiTreeLeaf> dictionary) {
        while (dictionary.size() > 1) {
            Collections.sort(dictionary);
            BiTreeLeaf left = dictionary.remove(dictionary.size() - 1);
            BiTreeLeaf right = dictionary.remove(dictionary.size() - 1);
            BiTreeLeaf parent = new BiTreeLeaf(null, left.frequency + right.frequency, left, right);
            dictionary.add(parent);
        }
        return dictionary.remove(0);
    }

    //словарь с частотами
    public static Map<Character, Integer> dictionaryWithFrequency(String text) {
        Map<Character, Integer> dict = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Integer count = dict.get(c);
            dict.put(c, count != null ? count + 1 : 1);
        }
        return dict;
    }
}


