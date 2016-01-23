SRCS += $(wildcard src/*.c)
OBJS = $(patsubst %.c,%.o,$(SRCS))
CPPFLAGS += -Iinclude -g
C++ := c++
#V=@

example: $(OBJS)
	$(V)$(CC) -o $@ $(LDFLAGS) $^

%.o: %.c
	$(V)$(CC) -c -o $@ $(CFLAGS) $<

%.o: %.cc
	$(V)$(C++) -c -o $@ $(CPPFLAGS) $<

clean::
	$(RM) -f src/*.o
	$(RM) -f example
	
include test.mk
