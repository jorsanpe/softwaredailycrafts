/**
 * @file chop.h
 *
 * @brief
 *  
 * @author: Jordi Sánchez, jorsanpe@gmail.com
 */
#pragma once

class Chop {
public:
    virtual ~Chop() {};

    virtual int chop(int value, int *array, int size) = 0;
};
