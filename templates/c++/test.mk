TEST_CASES := \
	test/chop_should

OBJ_chop_should = \
	test/chop_should.o \
	src/iterative_chop.o
	

## Common rules
GTEST_DIR := ../../../googletest/googletest

# Check Unity submodule when testing
ifeq ($(shell find $(GTEST_DIR) -mindepth 1),) 
$(error please download Unity submodule: 'git submodule init && git submodule update')
endif

CPPFLAGS += -I. -Igtest
LDFLAGS += -Lgtest -lpthread -lgtest

gtest/gtest-all.cc:
	@${GTEST_DIR}/scripts/fuse_gtest_files.py ${GTEST_DIR} .
gtest/libgtest.a: gtest/gtest-all.o
	@$(AR) -rv $@ $<

.SECONDEXPANSION:
test/%: $$(OBJ_%) $$@.o test/main.o
	@$(C++) -o $@ $^ $(LDFLAGS)

unit_test: gtest/libgtest.a $(TEST_CASES)
	@for t in $(TEST_CASES); do ./$$t; done
	
clean::
	$(RM) -f $(TEST_CASES)

.PHONY: unit_test clean
