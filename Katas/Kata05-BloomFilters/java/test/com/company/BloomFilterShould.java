package com.company;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static com.company.BloomFilterBuilder.*;
import static org.junit.Assert.*;

/**
 * Created by jordi on 28/12/15.
 */
public class BloomFilterShould {
    @Test
    public void shouldInsertObject() {
        BloomFilter filter = null;
        try {
            filter = aFilter()
                    .withBitmapSize(120000)
                    .withHashFunctions(new HashFunctionDjb2(), new HashFunctionSdbm(), new HashFunctionMd5(0))
                    .build();
        } catch (NoSuchAlgorithmException e) {
            fail("Algorhtm MD5 not available");
        }

        filter.insert("word");

        assertTrue(filter.check("word"));
        assertFalse(filter.check("chair"));
    }

}
