/**
 * example.c -- Example of polimorphism for C
 *  
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <file_log.h>
#include <memory_log.h>


void PrintNextLogMessage(Log *log)
{
    char log_message[MAX_LINE_LENGTH];

    if (LogRead(log, log_message, MAX_LINE_LENGTH) != -1) {
        printf("%s", log_message);
    }
}

void DoTest(Log *log)
{
    unsigned int offset, i;
    char msg[128];

    for (i=0; i<6; ++i) {
        sprintf(msg, "Log message %d", i);
        LogWrite(log, msg);
    }
    printf("> Written %d log messages\n", i);

    for (offset=0; offset<3; ++offset) {
        printf("> Reading log from offset %d\n", offset);
        LogSeek(log, offset);
        for (i=0; i<3; ++i) {
            PrintNextLogMessage(log);
        }
    }

    printf("\n");

    LogDestroy(log);
}

int main(int argc, char **argv)
{
    MemoryLog memory_log;
    FileLog file_log;

    printf("> Testing with a memory log (max_msg=4)\n");
    MemoryLogInit(&memory_log, 4);
    DoTest((Log *)&memory_log);

    printf("> Testing with a file log\n");
    FileLogInit(&file_log, "messages.log");
    DoTest((Log *)&file_log);

    return 0;
}

