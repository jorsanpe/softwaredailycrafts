/**
 * @file iterative_chop.h
 *
 * @brief
 *  
 * @author: Jordi Sánchez, jorsanpe@gmail.com
 */
#pragma once

#include <chop.h>

class RecursiveChop : public Chop {
public:
    int chop(int value, int *array, int size);

private:
    int rechop(int value, int *array, int low, int top);
};
