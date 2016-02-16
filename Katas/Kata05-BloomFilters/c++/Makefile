SRCS += $(wildcard src/*.c)
OBJS = $(patsubst %.c,%.o,$(SRCS))
CPPFLAGS += -Iinclude -g
C++ := c++
#V=@

all: unit_test

%.o: %.cc
	$(V)$(C++) -c -o $@ $(CPPFLAGS) $<

clean::
	$(RM) `find . -name "*.o"` `find . -name "*.a"`
	
include test.mk
