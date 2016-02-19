/**
 *  md5_hash_function.h -- 
 *
 * Copyright (C) 2016 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#pragma once

#include <hash_function.h>

class Md5HashFunction : public HashFunction {
public:
    Md5HashFunction(int offset);

    size_t Hash(const char *str);
private:
    int offset;
};
