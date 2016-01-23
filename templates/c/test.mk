TEST_CASES := 

OBJ_ = \
	

## Common rules
UNITY_ROOT := ../Unity

# Check Unity submodule when testing
ifeq ($(shell find $(UNITY_ROOT) -mindepth 1),) 
$(error please download Unity submodule: 'git submodule init && git submodule update')
endif

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
