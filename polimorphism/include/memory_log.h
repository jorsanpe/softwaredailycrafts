/**
 * mem_log.h -- A logger where the messages are written to a circular buffer
 *              in RAM memory.
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#include <stdio.h>
#include <log.h>

typedef struct memory_log_struct {
    Log log;
    char **message_buffer;
    char *message_area;
    int max_msg;
    int tail;
    int head;
    int cursor;
} MemoryLog;

int MemoryLogInit(MemoryLog *log, unsigned int max_msg);

void MemoryLogDestroy(Log *log);

int MemoryLogWrite(Log *log, const char *msg);

int MemoryLogRead(Log *log, char *buffer, unsigned int size);

int MemoryLogSeek(Log *log, unsigned int offset);

