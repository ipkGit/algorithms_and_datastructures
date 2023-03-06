package org.example.eager_algos.huffman;

class BiTreeLeaf implements Comparable<BiTreeLeaf> {
    Character symbol;
    Integer frequency;
    BiTreeLeaf left;
    BiTreeLeaf right;

    public BiTreeLeaf() {}

    public BiTreeLeaf(Character symbol, Integer frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }

    public BiTreeLeaf(Character symbol, Integer frequency, BiTreeLeaf left, BiTreeLeaf right) {
        this.symbol = symbol;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public String getCodeToChar(String code, Character c) {
        if (symbol == c) {
            return code;
        } else {
            if (left != null) {
                String result = left.getCodeToChar(code+0, c);
                if (result != null) {
                    return result;
                }
            }
            if (right != null) {
                return right.getCodeToChar(code+1, c);
            }
            return null;
        }
    }

    @Override
    public int compareTo(BiTreeLeaf o) {
        return o.frequency - this.frequency;
    }

    @Override
    public String toString() {
        return "BiTreeLeaf{" +
                "symbol=" + symbol +
                ", frequency=" + frequency +
                '}';
    }
}
