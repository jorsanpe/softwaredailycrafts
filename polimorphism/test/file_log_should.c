#include <unistd.h>
#include <sys/stat.h>
#include <string.h>
#include <unity.h>
#include <file_log.h>

#define TEST_LOG_FILENAME   "messages.log"

static FileLog file_log;
static Log *msglog = (Log *)&file_log;

_Bool fileExists(const char *filename) {
    struct stat st;
    return stat(filename, &st) == 0;
}

void setUp(void)
{
}

void tearDown(void)
{
    unlink(TEST_LOG_FILENAME);
}

void shouldCreateFile(void)
{
    TEST_ASSERT_FALSE(fileExists(TEST_LOG_FILENAME));
    TEST_ASSERT_EQUAL(0, FileLogInit(&file_log, TEST_LOG_FILENAME));
    TEST_ASSERT_NOT_NULL(file_log.filp);
    LogDestroy(msglog);
    TEST_ASSERT_TRUE(fileExists(TEST_LOG_FILENAME));
}

void shouldWriteAndReadLogMessages(void)
{
    char msg[MAX_LINE_LENGTH];

    TEST_ASSERT_EQUAL(0, FileLogInit(&file_log, TEST_LOG_FILENAME));
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

void shouldSeekLogMessages(void)
{
    char msg[MAX_LINE_LENGTH];

    TEST_ASSERT_EQUAL(0, FileLogInit(&file_log, TEST_LOG_FILENAME));
    TEST_ASSERT_EQUAL(0, LogWrite(msglog, "First log message"));
    TEST_ASSERT_EQUAL(0, LogWrite(msglog, "Second log message"));
    TEST_ASSERT_EQUAL(0, LogSeek(msglog, 1));
    TEST_ASSERT_EQUAL(0, LogRead(msglog, msg, MAX_LINE_LENGTH));
    TEST_ASSERT_EQUAL(0, strcmp("Second log message", msg));
    TEST_ASSERT_EQUAL(0, LogSeek(msglog, 0));
    TEST_ASSERT_EQUAL(0, LogRead(msglog, msg, MAX_LINE_LENGTH));
    TEST_ASSERT_EQUAL(0, strcmp("First log message", msg));
    LogDestroy(msglog);
}
