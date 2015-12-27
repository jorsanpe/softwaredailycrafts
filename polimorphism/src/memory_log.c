/**
 * mem_log.c -- A logger where the messages are written to a circular buffer
 *              in RAM memory.
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#include <stdlib.h>
#include <string.h>
#include <memory_log.h>

static struct log_interface memory_log_ops = {
    .write = MemoryLogWrite,
    .read = MemoryLogRead,
    .seek = MemoryLogSeek,
    .destroy = MemoryLogDestroy,
};

static inline int min(int x, int y)
{
    return (x < y ? x : y);
}

int MemoryLogInit(MemoryLog *log, unsigned int max_msg)
{
    unsigned int i;

    log->cursor = log->head = log->tail = 0;
    log->max_msg = max_msg + 1;
    log->log.ops = &memory_log_ops;
    log->message_buffer = malloc(log->max_msg * sizeof(char *));
    log->message_area = malloc(log->max_msg * MAX_LINE_LENGTH);
    for (i=0; i<log->max_msg; ++i) {
        log->message_buffer[i] = log->message_area + i*MAX_LINE_LENGTH;
    }

    return 0;
}

void MemoryLogDestroy(Log *log)
{
    MemoryLog *memory_log = (MemoryLog *)log;

    free(memory_log->message_buffer);
    free(memory_log->message_area);
}

static void advanceHead(MemoryLog *memory_log)
{
    memory_log->head = (memory_log->head + 1) % memory_log->max_msg;
    if (memory_log->head == memory_log->tail) {
        memory_log->tail = (memory_log->tail + 1) % memory_log->max_msg;
        memory_log->cursor = (memory_log->cursor + 1) % memory_log->max_msg;
    }
}

int MemoryLogWrite(Log *log, const char *msg)
{
    MemoryLog *memory_log = (MemoryLog *)log;

    strncpy(memory_log->message_buffer[memory_log->head], msg, MAX_LINE_LENGTH);
    advanceHead(memory_log);

    return 0;
}

static inline _Bool hasMoreMessages(MemoryLog *memory_log)
{
    return memory_log->cursor != memory_log->head;
}

static void advanceCursor(MemoryLog *memory_log)
{
    if (memory_log->cursor != memory_log->head) {
        memory_log->cursor = (memory_log->cursor + 1) % memory_log->max_msg;
    }
}

int MemoryLogRead(Log *log, char *buffer, unsigned int size)
{
    MemoryLog *memory_log = (MemoryLog *)log;

    if (!hasMoreMessages(memory_log)) {
        return -1;
    }

    size = min(size, MAX_LINE_LENGTH);
    strncpy(buffer, memory_log->message_buffer[memory_log->cursor], size);
    advanceCursor(memory_log);

    return 0;
}

static inline int amountOfMessagesStored(MemoryLog *memory_log)
{
    return memory_log->head >= memory_log->tail ?
            memory_log->head - memory_log->tail :
            memory_log->head + memory_log->max_msg - memory_log->tail;
}

static inline _Bool isNotValidOffset(MemoryLog *memory_log, unsigned int offset)
{
    return offset >= amountOfMessagesStored(memory_log);
}

static inline void setCursorAtOffset(MemoryLog *memory_log, unsigned int offset)
{
    memory_log->cursor = (memory_log->tail + offset) % memory_log->max_msg;
}

int MemoryLogSeek(Log *log, unsigned int offset)
{
    MemoryLog *memory_log = (MemoryLog *)log;

    if (isNotValidOffset(memory_log, offset)) {
        return -1;
    }

    setCursorAtOffset(memory_log, offset);

    return 0;
}
