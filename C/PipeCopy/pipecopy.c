
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
		}

	}
}
