/**
 * file_log.h -- A logger where the messages are written to a file
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#include <stdio.h>
#include <log.h>

typedef struct file_log_struct {
    Log log;
    FILE *filp;
} FileLog;

int FileLogInit(FileLog *log, const char *filename);

void FileLogDestroy(Log *log);

int FileLogWrite(Log *log, const char *msg);

int FileLogRead(Log *log, char *buffer, unsigned int size);

int FileLogSeek(Log *log, unsigned int offset);
