TEST_CASES := \
	test/test_hello

OBJ_hello = \
	test/test_hello.o \
	
## Common rules
CPPUTEST_DIR := ../../../cpputest

# Check Unity submodule when testing
ifeq ($(shell find $(CPPUTEST_DIR) -mindepth 1),)
$(error please download CppUTest submodule: 'git submodule init && git submodule update')
endif

CPPFLAGS += -I./include -I$(CPPUTEST_DIR)/include
LDFLAGS += -Lcpputest -lcpputest -lpthread

CPPUTEST_SRCS = $(wildcard $(CPPUTEST_DIR)/src/CppUTest*/*.cpp)
CPPUTEST_OBJS = $(patsubst $(CPPUTEST_DIR)/src/%.cpp,cpputest/%.o,$(CPPUTEST_SRCS))

cpputest/CppUTest:
	@mkdir -p $@

cpputest/CppUTestExt:
	@mkdir -p $@

cpputest/%.o: $(CPPUTEST_DIR)/src/%.cpp
	$(C++) -c $(CPPFLAGS) -o $@ $<

cpputest/libcpputest.a: $(CPPUTEST_OBJS)
	@$(AR) -r $@ $^

.SECONDEXPANSION:
test/%: $$(OBJ_%) $$@.o
	@$(C++) -o $@ $^ $(LDFLAGS)

unit_test: | cpputest/CppUTest cpputest/CppUTestExt cpputest/libcpputest.a $(TEST_CASES)
	@for t in $(TEST_CASES); do ./$$t; done
	
clean::
	$(RM) -f $(TEST_CASES)

fullclean::
	$(RM) -r cpputest

.PHONY: unit_test clean
