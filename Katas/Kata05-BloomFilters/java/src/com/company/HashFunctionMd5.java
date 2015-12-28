package com.company;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.lang.Math.abs;

/**
 * Created by jordi on 28/12/15.
 */
public class HashFunctionMd5 implements HashFunction {
    MessageDigest messageDigest;
    int baseIndex;

    public HashFunctionMd5(int base) throws NoSuchAlgorithmException {
        this.messageDigest = MessageDigest.getInstance("MD5");
        this.baseIndex = base;
    }

    public int hash(String str) {
        messageDigest.update(str.getBytes());
        byte[] digest = messageDigest.digest();

        int hashValue = 0;
        for (int i = baseIndex; i < baseIndex+4; i++) {
            hashValue += (hashValue << 8) | digest[i];
        }

        return abs(hashValue);
    }
}
