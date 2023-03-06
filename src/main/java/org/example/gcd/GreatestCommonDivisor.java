package org.example.gcd;

import java.math.BigInteger;

public class GreatestCommonDivisor {
    public BigInteger euclidGCD(BigInteger a, BigInteger b) {
        while (true) {
            if (a.equals(BigInteger.ZERO)) return b;
            if (b.equals(BigInteger.ZERO)) return a;
            if (a.compareTo(b)>0) a = a.mod(b);
            else b = b.mod(a);
        }
    }
}
