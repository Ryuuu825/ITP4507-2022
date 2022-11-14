compiler:=javac 
compiler_flags:=-d ../bin 
run:=java
run_flags:=-cp ../bin

main:=com/BMS/Main.java

test:=com/BMS/Test/**.java

sources = $(shell find . -type f -name '*.java')

all : clean
	$(compiler) $(compiler_flags) $(sources)


build: clean
	$(compiler) $(compiler_flags) $(main)

clean:
	find ../bin -type f -name '*.class' -delete

exec:
	$(run) $(run_flags) com.BMS.Main

test:
	cat "test.txt" | java -cp ../bin com.BMS.Main > "output.txt"
