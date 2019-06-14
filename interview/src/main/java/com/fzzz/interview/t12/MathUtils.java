package com.fzzz.interview.t12;

import java.math.BigInteger;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-13
 * update:
 */
class MathUtils {
    static BigInteger getThatLargeFactorial() {
        BigInteger fact = BigInteger.valueOf(1);
        for (int i = 1; i < 8785856; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
}
