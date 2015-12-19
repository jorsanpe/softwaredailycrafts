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

static inline int Min(int x, int y)
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

static void AdvanceHead(MemoryLog *memory_log)
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

    snprintf(memory_log->message_buffer[memory_log->head], MAX_LINE_LENGTH, "%s\n", msg);
    AdvanceHead(memory_log);

    return 0;
}

static inline _Bool HasMoreMessages(MemoryLog *memory_log)
{
    return memory_log->cursor != memory_log->head;
}

static void AdvanceCursor(MemoryLog *memory_log)
{
    if (memory_log->cursor != memory_log->head) {
        memory_log->cursor = (memory_log->cursor + 1) % memory_log->max_msg;
    }
}

int MemoryLogRead(Log *log, char *buffer, unsigned int size)
{
    MemoryLog *memory_log = (MemoryLog *)log;

    if (!HasMoreMessages(memory_log)) {
        return -1;
    }

    size = Min(size, MAX_LINE_LENGTH);
    strncpy(buffer, memory_log->message_buffer[memory_log->cursor], size);
    AdvanceCursor(memory_log);

    return 0;
}

static inline _Bool IsValidOffset(MemoryLog *memory_log, unsigned int offset)
{
    return offset < memory_log->max_msg;
}

int MemoryLogSeek(Log *log, unsigned int offset)
{
    MemoryLog *memory_log = (MemoryLog *)log;
    int top;

    if (!IsValidOffset(memory_log, offset)) {
        return -1;
    }

    if (memory_log->head < memory_log->tail) {
        top = memory_log->head + memory_log->max_msg;
    } else {
        top = memory_log->head;
    }
    memory_log->cursor = Min(top, memory_log->tail+offset) % memory_log->max_msg;

    return 0;
}
