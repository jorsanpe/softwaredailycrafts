TEST_CASES := \
	test/pjw_hash_function_should

OBJ_pjw_hash_function_should = \
	test/pjw_hash_function_should.o \
	src/pjw_hash_function.o
	
## Common rules
CPPUTEST_DIR := ../../../cpputest

# Check Unity submodule when testing
ifeq ($(shell find $(CPPUTEST_DIR) -mindepth 1),)
$(error please download CppUTest submodule: 'git submodule init && git submodule update')
endif

CPPFLAGS += -I./include -I$(CPPUTEST_DIR)/include
LDFLAGS += -L$(CPPUTEST_DIR)/lib -lCppUTest -lpthread

.SECONDEXPANSION:
test/%: $$(OBJ_%) $$@.o test/main.o
	@$(C++) -o $@ $^ $(LDFLAGS)

unit_test: $(TEST_CASES)
	@for t in $(TEST_CASES); do ./$$t; done
	
clean::
	$(RM) -f $(TEST_CASES)

fullclean::
	$(RM) -r cpputest

.PHONY: unit_test clean
