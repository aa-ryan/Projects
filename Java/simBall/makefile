CC = javac   # compiler to use

compile:
	@echo "Compiling Using JavaC"
	${CC} src/main/Main.java  # Path to main file

run:
	@echo "Executing..."
	${CC} src/main/Main.java && java src.main.Main  # Path to main class

mjar:
	@echo "Creating jar"
	jar cvmf manifest.txt simBall.jar ./src/main/*.class ./src/render/*.class

clean:
	@echo "Cleaning *.class files"
	find . -name "*.class" -type f -delete
