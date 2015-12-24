TEST_CASES := \
	test/file_log_should \
	test/memory_log_should

OBJ_file_log_should = \
	test/file_log_should.o \
	src/file_log.o
	
OBJ_memory_log_should = \
	test/memory_log_should.o \
	src/memory_log.o
	
## Common rules
UNITY_ROOT := ../Unity
CFLAGS += -I$(UNITY_ROOT)/src

.SECONDEXPANSION:
test/%_runner.c: test/%.c
	@ruby $(UNITY_ROOT)/auto/generate_test_runner.rb $< $@ > /dev/null
test/%: $$(OBJ_%) $$@_runner.o $(UNITY_ROOT)/src/unity.o
	@$(CC) -o $@ $^ $(LDFLAGS)

unit_test: $(TEST_CASES)
	@for t in $(TEST_CASES); do ./$$t; done
	
clean::
	$(RM) -f $(TEST_CASES)

.PHONY: unit_test clean
