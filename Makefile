SRC_DIR := .

OUT_DIR := build/classes

MAIN := dm.lox.Lox

.PHONY: all clean compile run

all: clean compile run

clean:
	rm -f $$(find $(OUT_DIR) -name *.class)

compile:
	mkdir -p $(OUT_DIR)
	javac -d $(OUT_DIR) $$(find $(SRC_DIR) -name *.java)

run:
	java -cp $(OUT_DIR) $(MAIN) 1var.lox
	java -cp $(OUT_DIR) $(MAIN)