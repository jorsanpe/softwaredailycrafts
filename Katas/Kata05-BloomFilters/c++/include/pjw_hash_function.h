/**
 *  pjw_hash_function.h
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#pragma once

#include <string>
#include <hash_function.h>

class PjwHashFunction : public HashFunction {
public:
    size_t Hash(const char *str);
};
