/**
 *  md5_hash_function.cc -- 
 *
 * Copyright (C) 2016 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#include <md5_hash_function.h>

#include <CommonCrypto/CommonDigest.h>

Md5HashFunction::Md5HashFunction(int offset)
{
    this->offset = offset;
}

size_t Md5HashFunction::Hash(const char *str)
{
    unsigned char md[128];
    size_t hash;

    CC_MD5(str, strlen(str), md);
    hash = md[this->offset]
              + (md[this->offset] << 8)
              + (md[this->offset] << 16)
              + (md[this->offset] << 24);

    return hash;
}
