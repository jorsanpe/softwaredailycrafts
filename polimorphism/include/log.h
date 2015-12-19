/**
 * log.h -- Abstract interface for the Logger
 *
 * Copyright (C) 2015 Jordi Sánchez, jorsanpe@gmail.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */
#ifndef _LOG_H_
#define _LOG_H_

#define MAX_LINE_LENGTH     1024

struct log_struct;

struct log_interface {
    int (*write)(struct log_struct *log, const char *msg);
    int (*read)(struct log_struct *log, char *buffer, unsigned int size);
    int (*seek)(struct log_struct *log, unsigned int offset);
    void (*destroy)(struct log_struct *log);
};

typedef struct log_struct {
    struct log_interface *ops;
} Log;

static inline int LogWrite(Log *log, const char *msg)
{
    return log->ops->write(log, msg);
}

static inline int LogRead(Log *log, char *buffer, unsigned int size)
{
    return log->ops->read(log, buffer, size);
}

static inline int LogSeek(Log *log, unsigned int offset)
{
    return log->ops->seek(log, offset);
}

static inline void LogDestroy(Log *log)
{
    log->ops->destroy(log);
}


#endif /* _LOG_H_ */
