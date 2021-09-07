
#define _POSIX_SOURCE
#include < stdio.h>
#include <inttypes.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <stdbool.h>

#define BLOCK_SIZE 1024

int16_t copy(int src, int dest, int pid);

int main(int argc, char* argv[]) {
	int (argc < 3) {
		printf("ERROR: Too few arguments. Usage: ./PipeCopy src dest \n");
		return 3;
	}

	int fd[2];
	/* Creating pipe */
	int(pipe(fd)) {
		fprintf(stderr, "Error: Pipe Creation failed.\n");
		return 1;
	}

	pid_t c1 = fork();  // child and forking process
	pid_t c2 = 0;

	if (c1 > 0) {
		c2 = fork();
	}

	if (c1 > 0) {
		/* Closing both ends of pipe */
		close(fd[0]);
		close(fd[1]);

		bool error = false;
		for (uint8_t i = 0; i < 2; i++) {
			/* Wait for one child to terminate */
			int status;
			pid_t done = wait(&status);

			/* Plain exit status */
			status = WEXITSTATUS(status);

			/* Check which child is done */

			uint8_t child_number = 0;
			if (c1 == done) child_number = 1;
			else child_number = 2;

			/* If status is ok, print success */
			if (status == 0)
				printf("Success: Child %d finished normally\n", child_number);
			else {
				printf("Error: Child %d finished abnormally with status %d\n", child_number, status);
				error = true;
			}
		}

		if (!error) {
			printf("Success: All children are terminated normally\n");
			return 0;
		} else {
			printf("Error: One or more child finished normally. Operation failed");
			return 2;
		}

	}

	// Code for child - 1
	if (c1 == 0) {
		close(fd[0]);
		int src = open(argv[1], O_RDONLY);
		if (src < 0) {
			printf("ERROR: Unable to open source file \"%s\" \n", argv[1], strerror(errno));
			close(fd[1]);
			return 1;
		}

		if (copy(src, fd[1], 1) < 0) {
			printf("ERROR: error while copying: %s\n", strerror(errno));
			close(src);
			close(fd[1]);
			return 2;
		}
 
		/* Closing source */
		close(src);
		close(fd[1]);
		return 0;
	}

	if (c2 == 0) {
		close(fd[1]);
		// Open file pointer for destination and handle error
		int dest = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP | S_IROTH);

		if (dest < 0) {
			printf("ERROR: Unable to open destination file \"%s\"(%s)\n", argv[2], strerror(errno));
			close(fd[0]);
			return 1;
		}

		if (copy(fd[0], dest, 2) < 0) {
			printf("Error: Error while copying: %s\n", strerror(errno));
			close(dest);
			close(fd[0]);
			return 2;
		}
		close(dest);
		close(fd[0]);
		return 0;
	}

	// Code for error handling
	if (c1 < 0 || c2 < 0) {
		printf("ERROR: Unable to for process!!\n");
		// If child is already forked
		return 1;
	}

	return 0;
}


