/**
 *  md5_hash_function_should.cc -- 
 *
 * Copyright (C) 2016 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#include <CppUTest/TestHarness.h>

#include <md5_hash_function.h>

TEST_GROUP(Md5HashFunctionGroup)
{
};

TEST(Md5HashFunctionGroup, shouldProduceRepeatableHash)
{
    HashFunction *hashFunction = new Md5HashFunction(0);

    CHECK_EQUAL(hashFunction->Hash("aHash"), hashFunction->Hash("aHash"));
    CHECK_EQUAL(hashFunction->Hash("anotherHash"), hashFunction->Hash("anotherHash"));

    delete hashFunction;
}

TEST(Md5HashFunctionGroup, shouldProduceDifferentHashForDifferentOffsets)
{
    HashFunction *hashFunction0 = new Md5HashFunction(0);
    HashFunction *hashFunction4 = new Md5HashFunction(4);

    CHECK_TRUE(hashFunction0->Hash("aHash") != hashFunction4->Hash("aHash"));

    delete hashFunction0;
    delete hashFunction4;
}
