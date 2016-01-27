/**
 * @file iterative_chop.cc
 *
 * @brief
 *  
 * @author: Jordi Sánchez, jorsanpe@gmail.com
 */
#include <recursive_chop.h>

int RecursiveChop::rechop(int value, int *array, int low, int top)
{
    int middle;

    if (low > top) {
        return -1;
    }
    middle = (top + low) / 2;
    if (value < array[middle]) {
        return this->rechop(value, array, low, middle-1);
    }
    if (value > array[middle]) {
        return this->rechop(value, array, middle+1, top);
    }
    return middle;
}

int RecursiveChop::chop(int value, int *array, int size)
{
    return this->rechop(value, array, 0, size-1);
}
