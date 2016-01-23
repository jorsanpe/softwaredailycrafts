SRCS += $(wildcard src/*.c)
OBJS = $(patsubst %.c,%.o,$(SRCS))
CFLAGS += -Iinclude -g
#V=@

target: $(OBJS)
	$(V)$(CC) -o $@ $(LDFLAGS) $^

%.o: %.c
	$(V)$(CC) -c -o $@ $(CFLAGS) $<

clean::
	$(RM) -f src/*.o
	$(RM) -f target
	
include test.mk
