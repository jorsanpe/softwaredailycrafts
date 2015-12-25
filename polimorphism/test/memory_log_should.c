#include <string.h>
#include <unity.h>
#include <memory_log.h>

static MemoryLog memory_log;
static Log *msglog = (Log *)&memory_log;

void setUp(void)
{
}

void tearDown(void)
{
}

void shouldInitializeProperlyMemoryLog(void)
{
    TEST_ASSERT_EQUAL(0, MemoryLogInit(&memory_log, 4));
    TEST_ASSERT_EQUAL(0, memory_log.cursor);
    TEST_ASSERT_EQUAL(0, memory_log.head);
    TEST_ASSERT_EQUAL(0, memory_log.tail);
    TEST_ASSERT_EQUAL(5, memory_log.max_msg);
    TEST_ASSERT_NOT_NULL(memory_log.message_area);
    TEST_ASSERT_NOT_NULL(memory_log.message_buffer);
}

void shouldWriteAndReadLogMessages(void)
{
    char msg[MAX_LINE_LENGTH];

    TEST_ASSERT_EQUAL(0, MemoryLogInit(&memory_log, 4));
    TEST_ASSERT_EQUAL(0, LogWrite(msglog, "First log message"));
    TEST_ASSERT_EQUAL(0, LogWrite(msglog, "Second log message"));
    TEST_ASSERT_EQUAL(0, LogSeek(msglog, 0));
    TEST_ASSERT_EQUAL(0, LogRead(msglog, msg, MAX_LINE_LENGTH));
    TEST_ASSERT_EQUAL(0, strcmp("First log message", msg));
    TEST_ASSERT_EQUAL(0, LogRead(msglog, msg, MAX_LINE_LENGTH));
    TEST_ASSERT_EQUAL(0, strcmp("Second log message", msg));
    TEST_ASSERT_EQUAL(-1, LogRead(msglog, msg, MAX_LINE_LENGTH));
    LogDestroy(msglog);
}

void shouldWrapIfWritingTooManyMessages(void)
{
    char msg[MAX_LINE_LENGTH];

    TEST_ASSERT_EQUAL(0, MemoryLogInit(&memory_log, 3));
    TEST_ASSERT_EQUAL(0, LogWrite(msglog, "First log message"));
    TEST_ASSERT_EQUAL(0, LogWrite(msglog, "Second log message"));
    TEST_ASSERT_EQUAL(0, LogWrite(msglog, "Third log message"));
    TEST_ASSERT_EQUAL(0, LogWrite(msglog, "Fourth log message"));
    TEST_ASSERT_EQUAL(0, LogSeek(msglog, 0));
    TEST_ASSERT_EQUAL(0, LogRead(msglog, msg, MAX_LINE_LENGTH));
    TEST_ASSERT_EQUAL(0, strcmp("Second log message", msg));
    TEST_ASSERT_EQUAL(0, LogRead(msglog, msg, MAX_LINE_LENGTH));
    TEST_ASSERT_EQUAL(0, strcmp("Third log message", msg));
    TEST_ASSERT_EQUAL(0, LogRead(msglog, msg, MAX_LINE_LENGTH));
    TEST_ASSERT_EQUAL(0, strcmp("Fourth log message", msg));
    TEST_ASSERT_EQUAL(-1, LogRead(msglog, msg, MAX_LINE_LENGTH));
    LogDestroy(msglog);
}
