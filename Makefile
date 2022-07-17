SRC_DIR := .

OUT_DIR := build/classes

MAIN := dm.lox.Lox

.PHONY: all clean compile run

all: clean compile run

clean:
	rm -f $$(find $(OUT_DIR) -name *.class)
	rm -rf $(OUT_DIR)/*

compile:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $$(find $(SRC_DIR) -name *.java)

run:
	java -cp $(OUT_DIR) $(MAIN) 1var.lox
	# java -cp $(OUT_DIR) $(MAIN)

makeexp:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $$(find dm/lox/tools/ -name *.java)
	java -cp build/classes/ dm.lox.tools.GenerateAst dm/lox/

jar: compile
	@echo "Manifest-Version: 1.0" > $(OUT_DIR)/manifest.txt
	@echo "Class-Path: ." >> $(OUT_DIR)/manifest.txt
	@echo "Main-Class: $(MAIN)" >> $(OUT_DIR)/manifest.txt
	@echo "" >> $(OUT_DIR)/manifest.txt
	jar -cmf $(OUT_DIR)/manifest.txt $(OUT_DIR)/build.jar $$(find $(OUT_DIR) -name *.class)
