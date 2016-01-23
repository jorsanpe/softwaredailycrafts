/**
 * @file chop_should.cc
 *
 * @brief
 *  
 * @author: Jordi Sánchez, jorsanpe@gmail.com
 */
#include "gtest.h"
#include <iterative_chop.h>

TEST (ChopShould, findValuesOnArrayWithOddSize) {
    IterativeChop chopper;
    int testArray[3] = { 1, 3, 5 };

    EXPECT_EQ(0, chopper.chop(1, testArray, 3));
    EXPECT_EQ(1, chopper.chop(3, testArray, 3));
    EXPECT_EQ(2, chopper.chop(5, testArray, 3));
    EXPECT_EQ(-1, chopper.chop(0, testArray, 3));
    EXPECT_EQ(-1, chopper.chop(2, testArray, 3));
    EXPECT_EQ(-1, chopper.chop(4, testArray, 3));
    EXPECT_EQ(-1, chopper.chop(6, testArray, 3));
}

TEST (ChopShould, findValuesOnArrayWithEvenSize) {
    IterativeChop chopper;
    int testArray[4] = { 1, 3, 5, 7 };

    EXPECT_EQ(0, chopper.chop(1, testArray, 4));
    EXPECT_EQ(1, chopper.chop(3, testArray, 4));
    EXPECT_EQ(2, chopper.chop(5, testArray, 4));
    EXPECT_EQ(3, chopper.chop(7, testArray, 4));
    EXPECT_EQ(-1, chopper.chop(0, testArray, 4));
    EXPECT_EQ(-1, chopper.chop(2, testArray, 4));
    EXPECT_EQ(-1, chopper.chop(4, testArray, 4));
    EXPECT_EQ(-1, chopper.chop(6, testArray, 4));
    EXPECT_EQ(-1, chopper.chop(8, testArray, 4));
}
