#define EXECLP_ERROR 42

#include<stdio.h>
#include<inttypes.h>
#include<unistd.h>
#include<sys/wait.h>
#include<sys/types.h>


int main(int argc, char *argv[])
{
	/* Forking process */
	pid_t pid = fork();

	/* Parent Code */
	if (pid > 0)
	{
		/* wait for child */
		int status = 0;
		wait(&status);

		/* Get exit status */
		status = WEXITSTATUS(status);

		if (status != 0) 
		{
			printf("Error: Child Process finished abnormally with status %d\n", status);

			if (status == EXECLP_ERROR)
			{
				printf("HINT: execlp() failed.");
				printf("Call in bin folder, or enter path for the binary");
			}
			else 
			{
				printf("Sucess: Child finished normally");
			}
			
			/* Child exit status */
			return status;
		}

		/* Child code */
		else if (pid == 0) 
		{
			execlp("./Copy" , argv[0], argv[1], argv[2], NULL);
			return EXECLP_ERROR;
		}
		else // pid < 0
		{
			printf("ERROR: Unable to fork process");
			return 1;
		}

	}
	return 0;
}
