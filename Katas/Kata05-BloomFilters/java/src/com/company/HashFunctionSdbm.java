/**
 * HashFunctionSdbm - A SDBM hash implementation
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package com.company;

import static java.lang.Math.abs;

public class HashFunctionSdbm implements HashFunction {

    public int hash(String str) {
        int hashValue = 0;

        for (int i = 0; i < str.length(); i++) {
            hashValue = str.charAt(i) + hashValue * 65599 - hashValue;
        }

        return abs(hashValue);
    }
}
