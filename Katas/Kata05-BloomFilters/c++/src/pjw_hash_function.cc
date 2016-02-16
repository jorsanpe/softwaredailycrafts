/**
 *  pjw_hash_function.cc -- A PJW hash implementation
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#include <pjw_hash_function.h>

size_t PjwHashFunction::Hash(const char *str)
{
    size_t h = 0, high;
    while (*str) {
        h = (h << 4) + *str++;
        high = h & 0xF0000000;
        if (high) {
            h ^= high >> 24;
        }
        h &= ~high;
    }
    return h;
}
