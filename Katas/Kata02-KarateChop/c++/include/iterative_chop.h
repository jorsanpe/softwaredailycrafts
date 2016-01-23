/**
 * @file iterative_chop.h
 *
 * @brief
 *  
 * @author: Jordi Sánchez, jorsanpe@gmail.com
 */
#pragma once

#include <chop.h>

class IterativeChop : public Chop {
public:
    int chop(int value, int *array, int size);
};
