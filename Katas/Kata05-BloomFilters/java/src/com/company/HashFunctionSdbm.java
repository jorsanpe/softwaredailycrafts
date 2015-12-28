package com.company;

import static java.lang.Math.abs;

/**
 * Created by jordi on 28/12/15.
 */
public class HashFunctionSdbm implements HashFunction {

    public int hash(String str) {
        int hashValue = 0;

        for (int i = 0; i < str.length(); i++) {
            hashValue = str.charAt(i) + hashValue * 65599 - hashValue;
        }

        return abs(hashValue);
    }
}
