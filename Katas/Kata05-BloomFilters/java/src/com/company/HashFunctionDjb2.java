package com.company;

import static java.lang.Math.abs;

/**
 * Created by jordi on 28/12/15.
 */
public class HashFunctionDjb2 implements HashFunction {
    public int hash(String str) {
        int hashValue = 5381;

        for (int i = 0; i < str.length(); ++i) {
            hashValue = hashValue * 33 + str.charAt(i);
        }

        return abs(hashValue);
    }
}
