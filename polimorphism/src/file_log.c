/**
 * file_log.c -- A logger where the messages are written to a file
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#include <string.h>
#include <file_log.h>

static struct log_interface file_log_ops = {
    .write = FileLogWrite,
    .read = FileLogRead,
    .seek = FileLogSeek,
    .destroy = FileLogDestroy,
};

int FileLogInit(FileLog *log, const char *filename)
{
    log->filp = fopen(filename, "w+");
    if (log->filp == NULL) {
        return -1;
    }
    log->log.ops = &file_log_ops;

    return 0;
}

void FileLogDestroy(Log *log)
{
    FileLog *file_log = (FileLog *)log;

    fclose(file_log->filp);
}

int FileLogWrite(Log *log, const char *msg)
{
    FileLog *file_log = (FileLog *)log;

    fseek(file_log->filp, 0, SEEK_END);
    fprintf(file_log->filp, "%s\n", msg);
    fflush(file_log->filp);

    return 0;
}

int FileLogRead(Log *log, char *buffer, unsigned int size)
{
    FileLog *file_log = (FileLog *)log;
    char *string;

    string = fgets(buffer, size, file_log->filp);

    return (string == NULL) ? -1 : 0;
}

int FileLogSeek(Log *log, unsigned int offset)
{
    FileLog *file_log = (FileLog *)log;
    unsigned int coff;
    char scratchpad[MAX_LINE_LENGTH];

    if (fseek(file_log->filp, 0, SEEK_SET) < 0) {
        return -1;
    }

    for (coff=0; coff<offset; ++coff) {
        if (fgets(scratchpad, sizeof(scratchpad), file_log->filp) == NULL) {
            fseek(file_log->filp, 0, SEEK_END);
        }
    }

    return 0;
}

