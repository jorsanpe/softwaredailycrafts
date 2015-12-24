SRCS += $(wildcard src/*.c)
OBJS = $(patsubst %.c,%.o,$(SRCS))
CFLAGS += -Iinclude
V=@

example: $(OBJS)
	$(V)$(CC) -g -o $@ $(LDFLAGS) $^

%.o: %.c
	$(V)$(CC) -g -c -o $@ $(CFLAGS) $<

clean::
	$(RM) -f src/*.o
	$(RM) -f example
	
include test.mk
