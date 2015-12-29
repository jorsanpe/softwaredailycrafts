/**
 * BloomFilterMain - A test application for bloom filters
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class BloomFilterMain {
    public static Random random = new Random();

    public static void main(String[] args) throws Exception {
        int bitmapSize = 6000000;
        int numberWords = 100000;

        BloomFilter filter = null;
        filter = BloomFilterBuilder.aFilter()
                .withBitmapSize(bitmapSize)
                .withHashFunctions(
                        new HashFunctionDjb2(),
                        new HashFunctionSdbm(),
                        new HashFunctionMd5(0),
                        new HashFunctionMd5(4),
                        new HashFunctionMd5(8))
                .build();

        feedBloomFilter(filter, "/usr/share/dict/words");

        testFilter(filter, bitmapSize, numberWords);
    }

    private static void testFilter(BloomFilter filter, int bitmapSize, int numberWords) throws IOException {
        float falsePositives = 0;
        for (int i = 0; i < numberWords; i++) {
            String word = generateRandomWord(randomLength());
            if (filter.contains(word) && !fileContains(word, "/usr/share/dict/words")) {
                falsePositives++;
            }
        }
        System.out.format("Configuration\n\t%d bits\n\t%d hash functions\n\t%d words inserted\n\t%.2f bits-to-words ratio\n",
                bitmapSize, filter.hashFunctionCount(), filter.getElementCount(), (float)bitmapSize / filter.getElementCount());
        System.out.format("-------\n");
        System.out.format("False positive ratio: %.6f\n", falsePositives / (float)numberWords);
    }

    private static int randomLength() {
        return random.nextInt(5)+3;
    }

    private static void checkWord(BloomFilter filter, String word) {
        System.out.format("Checking for '%s': %s\n", word, filter.contains(word) ? "Ok" : "Missing");
    }

    private static void feedBloomFilter(BloomFilter filter, String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String word;

        while ((word = br.readLine()) != null) {
            filter.insert(word);
        }
    }

    private static boolean fileContains(String searchTerm, String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String word;

        while ((word = br.readLine()) != null) {
            if (word.equals(searchTerm)) {
                return true;
            }
        }

        return false;
    }

    private static String generateRandomWord(int wordLength) {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'".toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < wordLength; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }

        return sb.toString();
    }
}
