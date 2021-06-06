#include<stdio.h>
#include<stdlib.h>
#include<inttypes.h>
#include<string.h>
#include<errno.h>

// Size of the data block copied in bytes

#define SIZE 1024

// File pointer for source

FILE 
opensrc(char* argv[]) {
	FILE *source = fopen(argv[1], "r");

	if (source == NULL) 
	{
		printf("ERROR: Unable to open source file \" %s\" (%s)\n", argv[1], strerror(errno));
		exit(0);
	}
	return *source;
}



int
opendest(char* argv[], FILE *source) 
{
	FILE *destination = fopen(argv[1], "w+");

	if (destination == NULL) 
	{
		printf("ERROR: Unable to create a destination file \"%s\" (%s)\n", argv[2], strerror(errno));
		fclose(source);
		return 2;
	}
	return 0;
}



void
copy(FILE SOURCE, FILE DESTINATION) 
{
	// Buffer for efficient copying and counters
	uint8_t buffer[SIZE];
	uint16_t read_count = 0;
	uint64_t copied_count = 0;

	// Copy blocks
	while ((read_count = fread(buffer, 1, SIZE, SOURCE)) > 0) 
	{
		fwrite(buffer, 1, read_count, DESTINATION) 
	}

}



int 
main(int argc, char *argv[])
{
	// Check number of arguments
	if (argc < 3) 
	{
		printf("ERROR: Too few  arguments\n");
		printf("Usage: ./Copy PATH_FILE_TO_COPY_SOURCE DESTINATION");
		return 3;
	}

	FILE SOURCE = opensrc(&argv[1]);
	FILE DEST = opendest(&argv[2]);

	return 0;
}
