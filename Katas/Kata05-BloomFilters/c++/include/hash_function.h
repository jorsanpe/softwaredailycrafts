/**
 *  hash_function.h
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#pragma once

#include <string>

class HashFunction {
public:
    virtual ~HashFunction() {};
    virtual size_t Hash(const char *str) = 0;
};
