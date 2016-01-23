/**
 * @file iterative_chop.cc
 *
 * @brief
 *  
 * @author: Jordi Sánchez, jorsanpe@gmail.com
 */
#include <iterative_chop.h>

int IterativeChop::chop(int value, int *array, int size)
{
    int middle, low, top;

    low = 0;
    top = size - 1;

    while (low <= top) {
        middle = (top + low) / 2;
        if (array[middle] < value) {
            low = middle + 1;
        } else if (array[middle] > value) {
            top = middle - 1;
        } else {
            return middle;
        }
    }

    return -1;
}
