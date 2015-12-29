/**
 * HashFunctionDjb2 - A DJB2 hash implementation
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package com.company;

import static java.lang.Math.abs;

public class HashFunctionDjb2 implements HashFunction {
    public int hash(String str) {
        int hashValue = 5381;

        for (int i = 0; i < str.length(); ++i) {
            hashValue = hashValue * 33 + str.charAt(i);
        }

        return abs(hashValue);
    }
}
