#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<errno.h>
#include<inttypes.h>

// Size of the data block copied in bytes

#define SIZE 1024

FILE* openSource(char* argv[]);
FILE* openDestination(char* argv[], FILE* source);
int copy(FILE* SOURCE, FILE* DESTINATION);


int 
main(int argc, char *argv[])
{
	// Check number of arguments
	if (argc < 3) 
	{
		printf("ERROR: Too few  arguments\n");
		printf("Usage: ./Copy PATH_FILE_TO_COPY DESTINATION");
		return 1;
	}

	FILE* SOURCE = openSource(&argv[1]);
	FILE* DEST = openDestination(&argv[2], SOURCE);
	copy(SOURCE, DEST);

	return 0;
}



FILE* 
openSource(char* argv[]) {
	FILE *source = fopen(*argv, "r");

	if (source == NULL) 
	{
		printf("ERROR: Unable to open source file \" %s\" (%s)\n", argv[1], strerror(errno));
		exit(0);
	}
	return source;
}



FILE*
openDestination(char* argv[], FILE* source) 
{
	FILE *destination = fopen(*argv, "w+");

	if (destination == NULL) 
	{
		printf("ERROR: Unable to create a destination file \"%s\" (%s)\n", argv[2], strerror(errno));
		fclose(source);
		exit(0);
	}
	return destination;
}

int
copy(FILE* SOURCE, FILE* DESTINATION) 
{
	// Buffer for efficient copying and counters
	uint8_t buffer[SIZE];
	uint16_t read_count = 0;
	uint64_t copied_count = 0;

	// Copy blocks
	while ((read_count = fread(buffer, 1, SIZE, SOURCE)) > 0) 
	{
		fwrite(buffer, 1, read_count, DESTINATION);
		copied_count += read_count;
		printf("%" PRIu64 " bytes copying done...\n", copied_count);
	}

	if (ferror(SOURCE)) 
	{
		printf("ERROR: Failure while reading the SOURCE FILE!\n");
		return 1;
	}

	if (ferror(DESTINATION))
	{
		printf("ERROR: Failure while writing the destination file!\n");
		return 1;
	}

	printf("COPIED SUCESSFULLY!\n");
	return 0;
}


