/**
 *  pjw_hash_function_should.cc
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#include <CppUTest/TestHarness.h>

#include <pjw_hash_function.h>

TEST_GROUP(HashFunctionGroup)
{
};

TEST(HashFunctionGroup, shouldProduceRepeatableHash)
{
    HashFunction *hashFunction = (HashFunction *)new PjwHashFunction();

    CHECK_EQUAL(hashFunction->Hash("aHash"), hashFunction->Hash("aHash"));

    delete hashFunction;
}

TEST(HashFunctionGroup, shouldProduceDifferentiableHash)
{
    HashFunction *hashFunction = (HashFunction *)new PjwHashFunction();

    CHECK_FALSE(hashFunction->Hash("aHash") == hashFunction->Hash("anotherHash"));

    delete hashFunction;
}
