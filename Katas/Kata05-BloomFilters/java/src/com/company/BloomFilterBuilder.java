/**
 * BloomFilterBuilder - A builder for BloomFilter
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package com.company;

public class BloomFilterBuilder {
    int bitmapSize;
    private HashFunction[] hashFunctions = new HashFunction[]{};

    public static BloomFilterBuilder aFilter() {
        return new BloomFilterBuilder();
    }

    public BloomFilterBuilder withBitmapSize(int size) {
        this.bitmapSize = size;
        return this;
    }

    public BloomFilterBuilder withHashFunctions(HashFunction... functions) {
        this.hashFunctions = functions;
        return this;
    }

    public BloomFilter build() {
        return new BloomFilter(bitmapSize, hashFunctions);
    }
}