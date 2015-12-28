package com.company;

import java.util.BitSet;

/**
 * Created by jordi on 28/12/15.
 */
public class BloomFilter {
    private int bitmapSize;
    private int elementCount = 0;
    private HashFunction[] hashFunctions;
    private BitSet bitset;

    public BloomFilter(int bitmapSize, HashFunction[] hashFunctions) {
        this.bitmapSize = bitmapSize;
        this.hashFunctions = hashFunctions;
        this.bitset = new BitSet(bitmapSize);
    }

    public void insert(String word) {
        for (HashFunction h : hashFunctions) {
            bitset.set(hash(word, h));
        }
        elementCount++;
    }

    public boolean check(String word) {
        for (HashFunction h : hashFunctions) {
            if (!bitset.get(hash(word, h))) {
                return false;
            }
        }

        return true;
    }

    public int hashFunctionCount() {
        return hashFunctions.length;
    }

    public int getElementCount() {
        return elementCount;
    }

    private int hash(String word, HashFunction h) {
        return h.hash(word) % bitmapSize;
    }
}
