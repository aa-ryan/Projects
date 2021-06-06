## Flappy-Bird

- A java program for the infamous flappy bird classic arcade game.
- To run 
	* Clone this repository.
	* Run this
		```bash
		java -jar FlappyBird.jar
		```
- To play use SPACE BAR or MOUSE CLICK.

- Compiling jar file from source.
	```bash
	echo "Main-Class: src/flappyBird/FlappyBird" > manifest.txt
	jar cvmf manifest.txt FlappyBird.jar ./src/flappyBird/*.class ./render/*.class
	```
</br>  ![Demo](example.gif)
