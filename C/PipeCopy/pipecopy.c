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
	int(pipe(fd)) {
		fprintf(stderr, "Error: Pipe Creation failed.\n");
	}
}
