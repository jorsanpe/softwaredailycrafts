package com.company;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Created by jordi on 28/12/15.
 */
public class HashFunctionShould {
    @Test
    public void shouldConvertStringIntoNumberDjb2() {
        HashFunction hashfn = new HashFunctionDjb2();
        int hash;

        hash = hashfn.hash("Test");
        assertEquals(hash, hashfn.hash("Test"));
        assertEquals(hash, hashfn.hash("Test"));
        assertNotEquals(hash, hashfn.hash("OtherString"));
    }

    @Test
    public void shouldConvertStringIntoNumberSdbm() {
        HashFunction hashfn = new HashFunctionSdbm();
        int hash;

        hash = hashfn.hash("Test");
        assertEquals(hash, hashfn.hash("Test"));
        assertEquals(hash, hashfn.hash("Test"));
        assertNotEquals(hash, hashfn.hash("OtherString"));
    }

    @Test
    public void shouldConvertStringIntoNumberMd5() {
        HashFunction hashfn = null;
        try {
            hashfn = new HashFunctionMd5(0);
        } catch (NoSuchAlgorithmException e) {
            fail("Algorithm md5 not available");
        }
        int hash;

        hash = hashfn.hash("Test");
        assertEquals(hash, hashfn.hash("Test"));
        assertEquals(hash, hashfn.hash("Test"));
        assertNotEquals(hash, hashfn.hash("OtherString"));
    }
}
